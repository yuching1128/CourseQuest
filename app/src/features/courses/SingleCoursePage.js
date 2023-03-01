import React from 'react'
import { useSelector } from 'react-redux'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";
import ProgressBar from 'react-bootstrap/ProgressBar';

export const SingleCoursePage = () => {
    const { university_id, course_id } = useParams()
    const course_id_int = parseInt(course_id)
    const university_id_int = parseInt(university_id)


    const course = useSelector(state =>
        // TODO need to add universityId into DB schema
        state.courses.find(course => course.id === course_id_int && course.university_id === university_id_int)
    )

    const ReviewList = () => {
        return (
            <div className="reviews">
                {course.reviews.map(review =>(
                    <div className="reviewBlock">{review}</div>
                ))}
            </div>
        );
    }

    if (!course) {
        return (
            <Container>
                <h2>Course not found!</h2>
            </Container>
        )
    }


    return (
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
                <p class="counts">(Counts of total ratings)</p>
            </div>
            <h3 className="reviews-text">Reviews</h3>
            <hr/>
            <ReviewList/>

        </Container>
    )
}