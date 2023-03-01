import React from 'react'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";
import {Spinner} from "react-bootstrap";
import {useGetCourseQuery} from "../api/apiSlice";
import ProgressBar from 'react-bootstrap/ProgressBar';

export const SingleCoursePage = () => {
    const { courseId } = useParams()

    const {
        data: course,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseQuery(courseId)

    // const ReviewList = () => {
    //     return (
    //         <div className="reviews">
    //             {course.reviews.map(review =>(
    //                 <div className="reviewBlock">{review}</div>
    //             ))}
    //         </div>
    //     );
    // }

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
                    <p className="rating">{course.rating}</p>
                    <p className="rateOutof">/ 5</p>
                    <button className="rate-review-but">Rate and Review</button>
                </div>
                <div className="ratingBar">
                    <ProgressBar max = "5" now={course.rating} />
                    <p className="counts">(Counts of total ratings)</p>
                </div>
                <h3 className="reviews-text">Reviews</h3>
                <hr/>
                {/*<ReviewList/>*/}

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