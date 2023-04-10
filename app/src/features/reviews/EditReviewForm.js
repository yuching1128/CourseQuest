import React, { useState } from 'react'
import {Button, Col, Row, Spinner, Stack} from "react-bootstrap";
import {useDeleteReviewMutation, useEditReviewMutation} from '../api/apiSlice'
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import StarRatings from "react-star-ratings";

export const EditReviewForm = ({reviewDetails, courseInstructors}) => {

    console.log(courseInstructors)

    const user = useSelector(state => state.user)
    const [updateReview, { isLoadingUpdateReview }] = useEditReviewMutation()
    const [deleteReview, { isLoadingDeleteReview }] = useDeleteReviewMutation()

    const navigate = useNavigate()

    const [anonymous, setAnonymous] = useState(reviewDetails.anonymous)
    const [rating, setRating] = useState(reviewDetails.rating)
    const [professor, setProfessor] = useState(reviewDetails.instructor.id)
    const [delivery, setDelivery] = useState(reviewDetails.delivery)
    const [workload, setWorkload] = useState(reviewDetails.workload)
    const [content, setContent] = useState(reviewDetails.content)
    const onAnonymousChanged = (e) => setAnonymous(anonymous => !anonymous)
    const onRatingChanged = (e) => setRating(e.target.value)
    const onProfessorChanged = (e) => setProfessor(e.target.value)
    const onDeliveryChanged = (e) => setDelivery(e.target.value)
    const onWorkloadChanged = (e) => setWorkload(e.target.value)
    const onContentChanged = (e) => setContent(e.target.value)


    const onEditReviewClicked = async () => {
        if (content) {
            try {
                const editReviewDetails = {
                    anonymous: anonymous,
                    content:content,
                    delivery: delivery,
                    id: reviewDetails.id,
                    instructor: {
                        id: reviewDetails.instructor.id,
                    },
                    rating: rating,
                    university: {
                        id: reviewDetails.university.id
                    },
                    user: {
                        id: user.id,
                    },
                    workload: workload
                }

                await updateReview({ universityId: reviewDetails.university.id, courseId: reviewDetails.course.id, reviewId: reviewDetails.id, editedReview: editReviewDetails})
                navigate(`/university/${reviewDetails.university.id}/courses/${reviewDetails.course.id}`)
            } catch (err) {
                console.error('Failed to edit the review: ', err)
            }
        }
    }

    const onDeleteReviewClicked = async () => {
        try {
            await deleteReview({universityId: reviewDetails.university.id, courseId: reviewDetails.course.id, reviewId: reviewDetails.id})
            navigate(`/university/${reviewDetails.university.id}/courses/${reviewDetails.course.id}`)
        } catch (err) {
            console.error('Failed to delete the review: ', err)
        }
    }

    const spinner = isLoadingUpdateReview ? <Spinner text="Saving..." /> : null

    return (
        <Container className="reviews">
            <Container className="RateReviewPage">
                <Stack direction="horizontal" gap={3} className="RateReviewContent"></Stack>
                <Form className="RateReviewForm">
                    <div className="stars-rating">
                        <h3>Edit Your Rating</h3>
                        <StarRatings
                            rating={rating}
                            changeRating={(newRating)=>{
                                setRating(newRating);
                            }}
                            starDimension="3em"
                            starSpacing="2em"
                            starRatedColor ='rgb(237, 139, 0)'
                            onChange={onRatingChanged}
                        />
                    </div>
                    <br/>
                    <div>
                        <h3>Edit Your Review</h3>
                        <Form.Group as={Row} className="mb-3" controlId="taughtBy">
                            <Form.Label column sm={2}>Taught by</Form.Label>
                            <Col sm={10}>
                                <Form.Select onChange={onProfessorChanged}>
                                    <option disabled selected value> -- select an option -- </option>
                                    <option>[TEST] Dr. K</option>
                                </Form.Select>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="delivery">
                            <Form.Label column sm={2}>Delivery</Form.Label>
                            <Col sm={10}>
                                <Form.Select onChange={onDeliveryChanged} value={delivery}>
                                    <option disabled selected value> -- select an option -- </option>
                                    <option>INPERSON</option>
                                    <option>ONLINE</option>
                                    <option>HYBRID</option>
                                </Form.Select>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="workload">
                            <Form.Label column sm={2}>Workload</Form.Label>
                            <Col sm={10}>
                                <Form.Select onChange={onWorkloadChanged} value={workload}>
                                    <option disabled selected value> -- select an option -- </option>
                                    <option>LIGHT</option>
                                    <option>MODERATE</option>
                                    <option>HEAVY</option>
                                </Form.Select>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="workload">
                            <Form.Control as="textarea" aria-label="With textarea" onChange={onContentChanged} value={content} />
                        </Form.Group>

                        <Form.Check
                            type="checkbox"
                            label="Anonymous?"
                            checked={anonymous}
                            onChange={onAnonymousChanged}
                        />
                        <br/>
                    </div>

                    <Button type="button" onClick={onEditReviewClicked}>
                        Edit Post
                    </Button>

                    <Button type="button" onClick={onDeleteReviewClicked}>
                        Delete Post
                    </Button>
                    {spinner}
                </Form>
            </Container>
        </Container>)
}