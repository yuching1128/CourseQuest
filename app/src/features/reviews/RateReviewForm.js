import React from 'react';
import { useState } from 'react';
import { useParams } from "react-router-dom";
import {Stack, Form, Button, Row, Col, Spinner} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import StarRatings from 'react-star-ratings';
import '../../css/rateReviewPage.css';
import {useAddNewReviewMutation} from "../api/apiSlice";
import {useSelector} from "react-redux";

export const RateReviewForm = () => {

    const user = useSelector(state => state.user)
    const { universityId, courseId } = useParams()

    const [rating, setRating] = useState(0)
    const [professor, setProfessor] = useState('')
    const [delivery, setDelivery] = useState('')
    const [workload, setWorkload] = useState('')
    const [content, setContent] = useState('')
    const [takenCourse, setTakenCourse] = useState(false)

    const [addNewReview, { isLoading }] = useAddNewReviewMutation()

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
                    anonymous: false,
                    content:content,
                    delivery: delivery,
                    university: {
                        id: universityId
                    },
                    course: {
                        id: courseId
                    },
                    instructor: {
                        id: 1,
                    },
                    rating: rating,
                    user: {
                        id: user.id,
                    },
                    workload: workload
                }

                await addNewReview({ universityId: universityId, courseId: courseId, newReview: reviewDetails}).unwrap()
                setRating(0)
                setProfessor('')
                setDelivery('')
                setWorkload('')
                setContent('')
            } catch (err) {
                console.error('Failed to save the review: ', err)
            }
        }
    }

    const spinner = isLoading ? <Spinner size="30px" /> : null
    
    return (
    <Container className="reviews">
        <Container className="RateReviewPage">
            <Stack direction="horizontal" gap={3} className="RateReviewContent"></Stack>
            <Form className="RateReviewForm">
                <div className="stars-rating">
                    <h3>Rate the Course</h3>
                    <Form.Check
                        type="checkbox"
                        label="Taken Course?"
                        value="yes"
                        onChange={onTakenCourseChanged}
                    />
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
                <h3>Write a Review</h3>
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
                    <Col sm={10}>
                        <Form.Select onChange={onWorkloadChanged}>
                            <option disabled selected value> -- select an option -- </option>
                            <option>LIGHT</option>
                            <option>MODERATE</option>
                            <option>HEAVY</option>
                        </Form.Select>
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="workload">
                <Form.Control as="textarea" aria-label="With textarea" placeholder='Things that you want share.' onChange={onContentChanged} />
                </Form.Group>
                </div>

                <Button type="button" onClick={onSaveReviewClicked} disabled={!canSave}>
                    Save Post
                </Button>
                {spinner}
                <Button variant="secondary" type="cancel" className="submit">Cancel</Button>
            </Form>
        </Container>
    </Container>)
}