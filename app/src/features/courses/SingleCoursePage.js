import React, {Component, useRef } from 'react'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";
import {Spinner} from "react-bootstrap";
import {useGetCourseQuery, useGetUserReviewsQuery} from "../api/apiSlice";
import {ReviewsPage} from "../reviews/ReviewsPage";
import StarRatings from 'react-star-ratings';
import Accordion from 'react-bootstrap/Accordion';
import { RateReviewForm } from '../reviews/RateReviewForm';
import {useSelector} from "react-redux";
import {EditReviewForm} from "../reviews/EditReviewForm";

export const SingleCoursePage = () => {

    const user = useSelector(state => state.user)
    const { universityId, courseId } = useParams()

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
    } = useGetCourseQuery({universityId: universityId, courseId: courseId})

    class Bar extends Component {
        render() {
            return (
                <StarRatings
                    rating={course.rating}
                    starDimension="2em"
                    starSpacing="0.2em"
                    starRatedColor ='rgb(237, 139, 0)'
                />
            );
        }
    }

    const rateReviewRef = useRef(null);

    const handleRateReviewClick = () => {
        rateReviewRef.current.scrollIntoView({ behavior: 'smooth', block: 'center', inline: "center"});
    };

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = (
            <Container className="singleCoursePage">
                <h2 className="courseName">{course.name}</h2>
                <h3>{course.instructor} </h3>
                <p className="courseDescription">{course.description}</p>
                <div>
                    {course.rating && <p className="rating">{course.rating}</p> }
                    {course.rating && <p className="rateOutof">/ 5</p> }
                    {!course.rating && <p>No Ratings Yet</p> }
                </div>
                {course.rating && <Bar/>}
                <div className="functionButtons">
                    <button onClick={handleRateReviewClick} className="rate-review-but">Rate and Review</button>
                    <button className="rate-review-but">Ask Questions</button>
                </div>
                <Accordion alwaysOpen>
                    <Accordion.Item eventKey="0">
                        <Accordion.Header>Reviews</Accordion.Header>
                        <Accordion.Body className="reviewsComponent">
                            <ReviewsPage courseId={course.id} />
                        </Accordion.Body>
                    </Accordion.Item>
                    <Accordion.Item eventKey="1">
                        <Accordion.Header>{userWrittenReview ? "Your Review" : "Write Review"}</Accordion.Header>
                        <Accordion.Body>
                            {/* If user written review, show EditForm. Else show RateReviewForm*/}
                            {userWrittenReview ? <EditReviewForm reviewDetails={userReviewInfo}/> : <RateReviewForm universityId={universityId} courseId={courseId} /> }
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