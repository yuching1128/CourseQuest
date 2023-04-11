import React from 'react';
import { useState } from 'react';
import { useParams } from "react-router-dom";
import {Stack, Form, Button, Row, Col, Spinner} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import StarRatings from 'react-star-ratings';
import '../../css/rateReviewPage.css';
import {useAddNewReviewMutation} from "../api/apiSlice";
import {useSelector} from "react-redux";

export const RateReviewForm = ({courseInstructors}) => {

    const user = useSelector(state => state.user)
    const { universityId, courseId } = useParams()

    const [anonymous, setAnonymous] = useState(false)
    const [rating, setRating] = useState(0)
    const [professor, setProfessor] = useState(<option disabled selected value> -- select an option -- </option>)
    const [delivery, setDelivery] = useState(<option disabled selected value> -- select an option -- </option>)
    const [workload, setWorkload] = useState(<option disabled selected value> -- select an option -- </option>)
    const [content, setContent] = useState(<option disabled selected value> -- select an option -- </option>)
    const [takenCourse, setTakenCourse] = useState(false)

    const [addNewReview, { isLoading }] = useAddNewReviewMutation()

    const onAnonymousChanged = (e) => setAnonymous(anonymous => !anonymous)
    const onRatingChanged = (e) => setRating(e.target.value)
    const onProfessorChanged = (e) => setProfessor(e.target.value)
    const onDeliveryChanged = (e) => setDelivery(e.target.value)
    const onWorkloadChanged = (e) => setWorkload(e.target.value)
    const onContentChanged = (e) => setContent(e.target.value)
    const onTakenCourseChanged = () => setTakenCourse(takenCourse => !takenCourse)

    const canSave = [rating, professor, delivery, workload, content, takenCourse].every(Boolean) && !isLoading

    const onSaveReviewClicked = async () => {
        if (canSave) {
            try {
                const reviewDetails = {
                    anonymous: anonymous,
                    content:content,
                    delivery: delivery,
                    university: {
                        id: universityId
                    },
                    course: {
                        id: courseId
                    },
                    instructor: {
                        id: professor,
                    },
                    rating: rating,
                    user: {
                        id: user.id,
                    },
                    workload: workload
                }

                await addNewReview({ universityId: universityId, courseId: courseId, newReview: reviewDetails}).unwrap()
            } catch (err) {
                console.error('Failed to save the review: ', err)
            }
        }
    }

    // generate list of instructors for reviewer to select
    const instructorOptions = courseInstructors.map((instructor) => (
        <option key={instructor.id} value={instructor.id}>
            {instructor.name}
        </option>
    ))

    const spinner = isLoading ? <Spinner size="30px" /> : null
    
    return (
    <Container className="reviews">
        <Container className="RateReviewPage">
            <Stack direction="horizontal" gap={3} className="RateReviewContent"></Stack>
            <Form className="RateReviewForm">
                <div className="reviewFormBox">
                    <div className="reviewBoxes">
                        <p className="courseTaken">Course Taken</p>
                        <Form.Check
                            type="checkbox"
                            label="I have taken this course."
                            value="yes"
                            onChange={onTakenCourseChanged}
                        />
                    </div>
                    <div className="reviewBoxes">
                        <p className="courseTaken">Rate the Course</p>
                        <StarRatings
                            rating={rating}
                            changeRating={(newRating)=>{
                                setRating(newRating);
                            }}
                            starDimension="3em"
                            starSpacing="2em"
                            starRatedColor ='rgb(237, 139, 0)'
                            starHoverColor='rgb(99, 0, 49)'
                            onChange={onRatingChanged}
                        />
                    </div>
                    <div className="reviewBoxes">
                        <p className="courseTaken">Write a Review</p>
                        <Form.Group as={Row} className="mb-3" controlId="taughtBy">
                            <Form.Label column sm={2}>Taught by</Form.Label>
                            <Col sm={10} style={{ maxWidth: '400px' }}>
                                <Form.Select onChange={onProfessorChanged}>
                                    <option disabled selected value> -- select an option -- </option>
                                    {instructorOptions}
                                </Form.Select>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="delivery">
                            <Form.Label column sm={2}>Delivery</Form.Label>
                            <Col sm={10} style={{ maxWidth: '400px' }}>
                                <Form.Select onChange={onDeliveryChanged}>
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
                                <Form.Select onChange={onWorkloadChanged}>
                                    <option disabled selected value> -- select an option -- </option>
                                    <option>LIGHT</option>
                                    <option>MODERATE</option>
                                    <option>HEAVY</option>
                                </Form.Select>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="workload">
                            <Form.Control as="textarea" aria-label="With textarea" placeholder='Things that you want share.' onChange={onContentChanged} style={{width: '90%', marginLeft: '10px'}} />
                        </Form.Group>

                        <Form.Check
                            type="checkbox"
                            label="Anonymous"
                            onChange={onAnonymousChanged}
                        />
                    </div>
                </div>
                <button type="button" className="reviewAddBtn" onClick={onSaveReviewClicked} disabled={!canSave}>
                    Add Post
                </button>
                {spinner}
                <button variant="secondary" type="cancel" className="reviewCancelBut">Cancel</button>
            </Form>
        </Container>
    </Container>)
}