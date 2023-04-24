import {Spinner} from "react-bootstrap";
import {useGetCourseReviewsQuery} from "../api/apiSlice";
import {useParams} from "react-router-dom";
import Container from "react-bootstrap/Container";
import React, {Component, useMemo} from "react";
import { faCircleUser} from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import StarRatings from "react-star-ratings";
import {TimeAgo} from "./TimeAgo";

export const ReviewsPage = () => {

    const { universityId, courseId } = useParams()

    const {
        data: reviews = [],
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseReviewsQuery({universityId:universityId, courseId: courseId})

    // Sort in descending chronological order
    const sortedReviews = useMemo(() => {
        const sortedReviews = reviews.slice()
        sortedReviews.sort((a, b) => b.createdAt.localeCompare(a.createdAt))
        return sortedReviews
    }, [reviews])

    let ReviewExcerpt = ({ review }) => {
        class Bar extends Component {
            render() {
                return (
                    <div className="reviewRate">
                        <StarRatings
                            rating={review.rating}
                            starDimension="1.2em"
                            starSpacing="0.1em"
                            starRatedColor ='rgb(237, 139, 0)'
                        />
                    </div>
                );
            }
        }

        return (
            <div className="reviews" key={review.id}>
                <div className="reviewBlock">
                    <div >
                        <FontAwesomeIcon icon={faCircleUser} className="userIcon" />
                        <h3 className="poster">{review.anonymous ? "Anonymous user" : review.user.firstName}</h3>
                        <Bar />
                    </div>
                    <p className="reviewContent">
                        {review.content}
                    </p>
                    <div>
                        <p className="reviewSubQues">Taught by:</p>
                        <p className="reviewSubAns">{review.instructor.name}</p>
                        <p className="reviewSubQues">Delivery:</p>
                        <p className="reviewSubAns">{review.delivery}</p>
                        <p className="reviewSubQues">Workload:</p>
                        <p className="reviewSubAns">{review.workload}</p>
                    </div>
                    <p className="reviewDate"><TimeAgo timestamp={review.createdAt} /></p>
                </div>
            </div>
        );
    }

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        if (sortedReviews) {
            content = sortedReviews.map(review => <ReviewExcerpt key={review.id} review={review} rating= {review.rating}/>)
        } else {
            content = <p>No Reviews yet.</p>
        }
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="reviews">
            {content}
        </Container>
    )
}
