import React from 'react'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";
import {Spinner} from "react-bootstrap";
import {useGetCourseQuery} from "../api/apiSlice";

export const SingleCoursePage = () => {
    const { courseId } = useParams()

    const {
        data: course,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCourseQuery(courseId)

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = (
            <article className="course-excerpt" key={course.id}>
            <h3>{course.name}</h3>
            <p className="description">{course.description}</p>
            </article>
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