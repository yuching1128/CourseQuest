import React from 'react'
import { useParams } from "react-router-dom";
import { Stack, Form, Button, Row, Col } from "react-bootstrap"
import Container from "react-bootstrap/Container";
import { useForm } from "react-hook-form";


export const RateReview = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const { courseId } = useParams()
    const onSubmit = (data) => {
        // TODO SUBMIT DATA TO API FOR PROCESSING
        console.log(data)
    }
    return (<Container className="reviews">
        <Container className="RateReviewPage">
            <Stack direction="horizontal" gap={3} className="RateReviewContent"></Stack>
            <Form onSubmit={handleSubmit(onSubmit)} className="RateReviewForm">
                <h3>Write a Review</h3>
                <Form.Group as={Row} className="mb-3" controlId="taughtBy">
                    <Form.Label column sm={2}>Taught by</Form.Label>
                    <Col sm={10}><Form.Select>
                        <option>Default select</option>
                    </Form.Select></Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="delivery">
                    <Form.Label column sm={2}>Delivery</Form.Label>
                    <Col sm={10}><Form.Select>
                        <option>Default select</option>
                    </Form.Select></Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="workload">
                    <Form.Label column sm={2}>Workload</Form.Label>
                    <Col sm={10}><Form.Select>
                        <option>Default select</option>
                    </Form.Select></Col>
                </Form.Group>

                <Button variant="primary" type="submit" className="submit">Submit</Button>
                <Button variant="secondary" type="cancel" className="submit">Cancel</Button>
            </Form>
        </Container>
    </Container>)
}