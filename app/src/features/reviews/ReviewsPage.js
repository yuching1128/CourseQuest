import {Spinner} from "react-bootstrap";
import {useGetCourseReviewsQuery} from "../api/apiSlice";
import {useParams} from "react-router-dom";
import Container from "react-bootstrap/Container";
import React, {Component} from "react";
import { faCircleUser} from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import StarRatings from "react-star-ratings";

export const ReviewsPage = () => {

    const { universityId, courseId } = useParams()

    const {
        data: reviews,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseReviewsQuery({universityId:universityId, courseId: courseId})

    console.log(reviews)

    let ReviewExcerpt = ({ review }) => {
        class Bar extends Component {
            render() {
                return (
                    <StarRatings
                        rating={review.rating}
                        starDimension="1.5em"
                        starSpacing="0.1em"
                        starRatedColor ='rgb(237, 139, 0)'
                    />
                );
            }
        }

        return (
            <div className="reviews" key={review.id}>
                <div className="reviewBlock">
                    <div style={{display: "flex"}}>
                        <FontAwesomeIcon icon={faCircleUser} className="userIcon"/>
                        <h3 className="poster">{review.poster}</h3>
                        <Bar />

                    </div>
                    <p className="reviewContent">
                        {review.content}
                    </p>

                    <div>
                        <p className="reviewSubQues">Taught by:</p>
                        <p className="reviewSubAns">{review.professor}</p>
                        <p className="reviewSubQues">Delivery:</p>
                        <p className="reviewSubAns">{review.delivery}</p>
                        <p className="reviewSubQues">Workload:</p>
                        <p className="reviewSubAns">{review.workload}</p>
                        <p className="reviewDate">
                            {review.date_created}
                        </p>
                    </div>
                </div>
            </div>
        )

    }

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = reviews.map(review => <ReviewExcerpt key={review.id} review={review} rating= {review.rating}/>)
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="reviews">
            {content}
            <button className="reviews-Load">
                Load more
            </button>
        </Container>

    )
}
