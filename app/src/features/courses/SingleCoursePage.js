import React from 'react'
import { useSelector } from 'react-redux'
import Container from "react-bootstrap/Container";
import {useParams} from "react-router-dom";

export const SingleCoursePage = () => {
    const { university_id, course_id } = useParams()
    const course_id_int = parseInt(course_id)
    const university_id_int = parseInt(university_id)


    const course = useSelector(state =>
        // TODO need to add universityId into DB schema
        state.courses.find(course => course.id === course_id_int && course.university_id === university_id_int)
    )

    if (!course) {
        return (
            <Container>
                <h2>Course not found!</h2>
            </Container>
        )
    }

    return (
        <Container>
            <h3>{course.name}</h3>
            <p>{course.description} : Rating ({course.rating})</p>
        </Container>
    )
}