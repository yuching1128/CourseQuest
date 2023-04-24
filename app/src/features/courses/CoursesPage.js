import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import { Spinner } from 'react-bootstrap';
import { useGetCoursesQuery } from "../api/apiSlice";
import { SearchComponent } from "../search/search";
import classnames from 'classnames';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Pagination from '@mui/material/Pagination';

let CourseExcerpt = ({ course }) => {
    return (
        <Link to={`/university/${course.university.id}/courses/${course.id}`} className="course-button">
            <article className="course-excerpt" key={course.id}>
                <h3>{course.name}</h3>
                <div className="all-info">
                    <div className="rating-box">
                        <p className="rating-text">Rating</p>
                        <div className="rating-point">{course.rating ? course.rating : "N/A"}</div>
                    </div>
                    <div className="course-info">
                        <p className="description">{course.description ? course.description.substring(0, 100) + "..." : "No Description Available"}</p>
                    </div>
                </div>
                <hr className="divider" />
            </article>
        </Link>

    );
};

export const CoursesPage = () => {
    const { universityId } = useParams();
    const [currentPage, setCurrentPage] = useState(0); // Current page state

    const size = 10;

    const {
        data: courseList,
        isLoading,
        isFetching,
        isSuccess,
        isError,
        error,
    } = useGetCoursesQuery({ universityId: universityId, page: currentPage, size: size });

    // Update current page when pagination is changed
    const handlePageChange = (event, page) => {
        setCurrentPage(page-1);
    };

    let content;

    if (isLoading) {
        content = <Spinner text="Loading..." />;
    } else if (isSuccess) {
        const renderedCourses = courseList.map((course) =>
            <CourseExcerpt key={course.id} universityId={universityId} course={course} />);
        const containerClassname = classnames('posts-container', {
            disabled: isFetching
        })
        content = <div className={containerClassname}>{renderedCourses}</div>
    } else if (isError) {
        content = <div>{error.toString()}</div>;
    }

    return (
        <Container>
            <div className="searchComponent">
                <SearchComponent />
            </div>
            <div className="courses-list">{content}</div>
                <ButtonGroup className="pagination-buttons" style={{ display: 'flex', justifyContent: 'center', marginTop: '1em' }}>
                    <Pagination
                        count={20} // Set the total number of pages
                        page={currentPage+1}
                        onChange={handlePageChange}
                        color="primary"
                        size="large"
                    />
                </ButtonGroup>
        </Container>
    );
};