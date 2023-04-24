import React, { useState } from 'react'
import { Col, Row, Spinner, Stack } from "react-bootstrap";
import { useDeleteReviewMutation, useEditReviewMutation } from '../api/apiSlice'
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import { useNavigate } from "react-router-dom";
import StarRatings from "react-star-ratings";

export const EditReviewForm = ({ reviewDetails, courseInstructors }) => {
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

    // generate list of instructors for reviewer to select
    const instructorOptions = courseInstructors.map((instructor) => (
        <option key={instructor.id} value={instructor.id}>
            {instructor.name}
        </option>
    ))

    const onEditReviewClicked = async () => {
        try {
            const editReviewDetails = {
                anonymous: anonymous,
                content: content,
                delivery: delivery,
                id: reviewDetails.id,
                instructor: {
                    id: professor,
                },
                rating: rating,
                university: {
                    id: reviewDetails.university.id
                },
                workload: workload
            }

            await updateReview({
                universityId: reviewDetails.university.id, courseId: reviewDetails.course.id, reviewId: reviewDetails.id, editedReview: editReviewDetails
            })
            navigate(0)
        } catch (err) {
            console.error('Failed to edit the review: ', err)
        }
    }

    const onDeleteReviewClicked = async () => {
        try {
            await deleteReview({ universityId: reviewDetails.university.id, courseId: reviewDetails.course.id, reviewId: reviewDetails.id })
            navigate(0)
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
                    <div className="reviewBoxes">
                        <div className="reviewBoxes">
                            <p className="courseTaken">Edit Your Rating</p>
                            <StarRatings
                                rating={rating}
                                changeRating={(newRating) => {
                                    setRating(newRating);
                                }}
                                starDimension="3em"
                                starSpacing="2em"
                                starRatedColor='rgb(237, 139, 0)'
                                onChange={onRatingChanged}
                            />
                        </div>
                        <div className="reviewBoxes">
                            <p className="courseTaken">Edit Your Review</p>
                            <Form.Group as={Row} className="mb-3" controlId="taughtBy">
                                <Form.Label column sm={2}>Taught by</Form.Label>
                                <Col sm={10} style={{ maxWidth: '400px' }}>
                                    <Form.Select onChange={onProfessorChanged} defaultValue={professor}>
                                        <option disabled selected value> -- select an option -- </option>
                                        {instructorOptions}
                                    </Form.Select>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="delivery">
                                <Form.Label column sm={2}>Delivery</Form.Label>
                                <Col sm={10} style={{ maxWidth: '400px' }}>
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
                                <Col sm={10} style={{ maxWidth: '400px' }}>
                                    <Form.Select onChange={onWorkloadChanged} value={workload}>
                                        <option disabled selected value> -- select an option -- </option>
                                        <option>LIGHT</option>
                                        <option>MODERATE</option>
                                        <option>HEAVY</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="workload">
                                <Form.Control as="textarea" aria-label="With textarea" onChange={onContentChanged} value={content} style={{ width: '90%', marginLeft: '10px' }} />
                            </Form.Group>

                            <Form.Check
                                type="checkbox"
                                label="Anonymous"
                                checked={anonymous}
                                onChange={onAnonymousChanged}
                            />
                        </div>
                    </div>

                    <button type="button" className="reviewAddBtn" onClick={onEditReviewClicked}>
                        Edit Post
                    </button>

                    <button type="button" className="reviewDeleteBtn" onClick={onDeleteReviewClicked}>
                        Delete Post
                    </button>
                    {spinner}
                </Form>
            </Container>
        </Container>)
}