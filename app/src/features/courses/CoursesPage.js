import React from 'react'
import {Link} from "react-router-dom";
import Container from "react-bootstrap/Container";
import {Spinner} from "react-bootstrap";
import {useGetCoursesQuery} from "../api/apiSlice";

let CourseExcerpt = ({ course }) => {
    return (
        <article className="course-excerpt" key={course.id}>
            <h3>{course.name}</h3>
            <p className="description">{course.description.substring(0, 100)}...</p>
            <Link to={`/courses/${course.id}`} className="button">
                View Course
            </Link>
            <hr />
        </article>
    )
}

export const CoursesPage = () => {

    const {
        data: courses,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetCoursesQuery()

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = courses.map(course => <CourseExcerpt key={course.id} course={course} />)
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="courses-list">
            <h2>Courses:</h2>
            {content}
        </Container>
    )
}