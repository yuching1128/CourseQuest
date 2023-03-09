import {Spinner} from "react-bootstrap";
import {useGetCourseReviewsQuery} from "../api/apiSlice";
import {useParams} from "react-router-dom";
import Container from "react-bootstrap/Container";
import React from "react";

export const ReviewsPage = () => {
    const { courseId } = useParams()

    const {
        data: reviews,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseReviewsQuery(courseId)

    let ReviewExcerpt = ({ review }) => {
        return (
            <div className="reviews" key={review.id}>
                <h3>{review.poster}</h3> {review.date_created}
                <p className="reviewBlock">
                    {review.content}
                </p>
            </div>
        )
    }

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = reviews.map(review => <ReviewExcerpt key={review.id} review={review} />)
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="reviews">
            {content}
        </Container>
    )
}
