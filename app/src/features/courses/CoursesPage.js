import React from 'react'
import { useSelector } from 'react-redux'
import {Link} from "react-router-dom";
import Container from "react-bootstrap/Container";

export const CoursesPage = () => {
    const courses = useSelector(state => state.courses)

    const renderedCourses = courses.map(course => (
        <article className="course-excerpt" key={course.id}>
            <h4>{course.name}</h4>
            <p className="course-content">{course.description.substring(0, 100)}</p>
            <Link to={`/university/${course.university_id}/courses/${course.id}`} className="button">
                View Course
            </Link>
            <hr />
        </article>
    ))

    return (
        <Container className="course-list">
            <h2>Courses:</h2>
            {renderedCourses}
        </Container>
    )
}