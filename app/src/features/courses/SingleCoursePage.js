import React, {Component} from 'react'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";
import {Spinner} from "react-bootstrap";
import {useGetCourseQuery} from "../api/apiSlice";
import {ReviewsPage} from "../reviews/ReviewsPage";
import StarRatings from 'react-star-ratings';
import Accordion from 'react-bootstrap/Accordion';

export const SingleCoursePage = () => {

    const { universityId, courseId } = useParams()

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
                </div>
                <Bar/>
                <div className="functionButtons">
                    <button className="rate-review-but">Rate and Review</button>
                    <button className="rate-review-but">Ask Questions</button>
                </div>
                <Accordion>
                    <Accordion.Item eventKey="0">
                        <Accordion.Header>Reviews</Accordion.Header>
                        <Accordion.Body className="reviewsComponent">
                            <ReviewsPage courseId={course.id} />
                        </Accordion.Body>

                    </Accordion.Item>
                    <Accordion.Item eventKey="1">
                        <Accordion.Header>Rate and Review</Accordion.Header>
                        <Accordion.Body>
                            Rate and Review component
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