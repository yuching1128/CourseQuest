import React, { Component, useEffect, useRef, useState } from 'react'
import Container from "react-bootstrap/Container";
import { useParams } from "react-router-dom";
import { Spinner } from "react-bootstrap";
import {
    useAddFollowCourseMutation,
    useDeleteFollowCourseMutation,
    useGetCourseQuery,
    useGetUserInfoQuery,
    useGetUserReviewsQuery
} from "../api/apiSlice";
import { ReviewsPage } from "../reviews/ReviewsPage";
import StarRatings from 'react-star-ratings';
import Accordion from 'react-bootstrap/Accordion';
import { RateReviewForm } from '../reviews/RateReviewForm';
import { useSelector } from "react-redux";
import { EditReviewForm } from "../reviews/EditReviewForm";

export const CoursePage = () => {

    const user = useSelector(state => state.user)
    const { universityId, courseId } = useParams()

    const [followedCourseId, setFollowedCourseId] = useState([]);
    const [addUserCourseInterested, { isLoading: addCourseInterestedIsLoading }] = useAddFollowCourseMutation();
    const [deleteCourseInterested, { isLoading: deleteCourseInterestedIsLoading }] = useDeleteFollowCourseMutation();

    // determine if user has written a review for this course
    const {
        data: userReviews = [],
        isLoading: userReviewsIsLoading,
        isFetching: userReviewsIsFetching,
        isSuccess: userReviewsIsSuccess,
        isError: userReviewsIsError,
        error: userReviewsError
    } = useGetUserReviewsQuery(user.id);

    let userWrittenReview = false;
    let userReviewInfo = null;
    for (let key = 0; key < userReviews.length; ++key) {
        if (parseInt(userReviews[key].course.id) === parseInt(courseId)) {
            userReviewInfo = userReviews[key]
            userWrittenReview = true
            break;
        }
    }

    const {
        data: course,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseQuery({ universityId: universityId, courseId: courseId })

    const {
        data: userProfileData,
        isSuccess: userProfileSuccess,
        refetch: refetchUserProfileData,
    } = useGetUserInfoQuery();

    useEffect(() => {
        refetchUserProfileData();
    }, [courseId, refetchUserProfileData]);

    useEffect(() => {
        if(userProfileData && userProfileData.interestedCourse){
            const selectedCoursesInterestId = userProfileData.interestedCourse
                .map((course) => ({ id: parseInt(course.id) }));
            setFollowedCourseId(selectedCoursesInterestId);
        }
    }, [userProfileData])

    console.log(followedCourseId)

    class Bar extends Component {
        render() {
            return (
                <StarRatings
                    rating={course.rating}
                    starDimension="1em"
                    starSpacing="0.1em"
                    starRatedColor='rgb(237, 139, 0)'
                />
            );
        }
    }

    const rateReviewRef = useRef(null);

    const handleFollowClick = async () => {
        // check if courseId is already in followedCourseId
        if (followedCourseId.some(item => item.id === parseInt(courseId))) {
            console.log("good")
            // if it is, remove courseId from followedCourseId
            await deleteCourseInterested({ universityId: universityId, courseId: courseId });
        } else {
            // if it is not, add courseId to followedCourseId
            await addUserCourseInterested({ universityId: universityId, courseId: courseId });
        }
    };


    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = (
            <Container className="singleCoursePage">
                <div className="course-container">
                    <p className="courseName">{course.name}</p>
                    <p className="courseNumber">{course.dept.name}-{course.courseNum}</p>
                    <div className="rating-container">
                        <div className="ratingPoint">
                            {course.rating && (
                                <div className="star-ratings">
                                    <p className="rating">{course.rating}</p>
                                    <p className="rateOutof">/ 5</p>
                                </div>
                            )}
                        </div>
                        <div className="ratingBar">
                            {course.rating && <Bar />}
                        </div>
                        {!course.rating && <p>No Ratings Yet</p>}
                    </div>
                    <div className="courseDescription">
                        {course.description}
                    </div>
                    <div className="functionButtons">
                        <button onClick={handleFollowClick} className="rate-review-but">
                            {followedCourseId.some(item => item.id === parseInt(courseId)) ? "Unfollow" : "Follow"}
                        </button>
                    </div>
                    {/*<div className="functionButtons">*/}
                    {/*    <button className="rate-review-but">*/}
                    {/*        Follow*/}
                    {/*    </button>*/}
                    {/*</div>*/}
                </div>
                <Accordion alwaysOpen>
                    <Accordion.Item eventKey="0">
                        <Accordion.Header>Reviews</Accordion.Header>
                        <Accordion.Body className="reviewsComponent">
                            <ReviewsPage courseId={course.id} />
                        </Accordion.Body>
                    </Accordion.Item>
                    <Accordion.Item eventKey="1">
                        <Accordion.Header style={{ marginBottom: '50px' }}>{userWrittenReview ? "Your Review" : "Write Review"}</Accordion.Header>
                        <Accordion.Body>
                            {/* If user written review, show EditForm. Else show RateReviewForm*/}
                            {userWrittenReview ? <EditReviewForm reviewDetails={userReviewInfo} courseInstructors={course.instructor} /> : <RateReviewForm universityId={universityId} courseId={courseId} courseInstructors={course.instructor} />}
                        </Accordion.Body>
                    </Accordion.Item>
                </Accordion>
            </Container>
        )
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="course-detail">
            {content}
        </Container>
    )
}