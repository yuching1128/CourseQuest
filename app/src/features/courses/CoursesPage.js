import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import { Spinner } from 'react-bootstrap';

let CourseExcerpt = ({ course }) => {
    return (
        <article className="course-excerpt" key={course.id}>
            <Link to={`/courses/${course.id}`} className="course-button">
                <h3>{course.name}</h3>
            </Link>
            <div className="allInfo">
                <div className="ratingBox">
                    <p className="rating-text">Rating</p>
                    <div className="rating-point">{course.rating}</div>
                </div>
                <div className="courseInfo">
                    <p className="professorName">Professor: {course.instructor}</p>
                    <p className="des">{course.description.substring(0, 100)}...</p>
                </div>
            </div>
            <hr />
        </article>
    );
};

export const CoursesPage = () => {
    const { universityId } = useParams();

    const pageSize = 3;

    const [currentPage, setCurrentPage] = useState(
        localStorage.getItem('curpage') ? parseInt(localStorage.getItem('curpage')) : 0
    );

    const [courseList, setCourseList] = useState([]);

    const [isLoading, setIsLoading] = useState(false);

    const [error, setError] = useState(null);

    const [noMoreCourses, setNoMoreCourses] = useState(false);

    useEffect(() => {
        const fetchCourses = async () => {
            setIsLoading(true);

            try {
                const response = await fetch(`/api/university/${universityId}/courses/${currentPage}/${pageSize}`);
                const data = await response.json();

                if (data.length === 0) {
                    setNoMoreCourses(true);
                } else {
                    setCourseList((prevCourseList) => [...prevCourseList, ...data]);
                }
            } catch (error) {
                setError(error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchCourses();
    }, [universityId, currentPage, pageSize]);

    let content;

    if (isLoading) {
        content = <Spinner text="Loading..." />;
    } else if (courseList.length > 0) {
        content = courseList.map((course) => <CourseExcerpt key={course.id} course={course} />);
    } else if (error) {
        content = <div>{error.toString()}</div>;
    }

    useEffect(() => {
        localStorage.setItem('curpage', currentPage.toString());
    }, [currentPage]);

    const handleLoadMore = () => {
        setCurrentPage(currentPage + 1);
    };

    return (
        <Container>
            <div className="courses-list">{content}</div>
            {!noMoreCourses && (
                <button className="courseList-Load" onClick={handleLoadMore}>
                    Load more
                </button>
            )}
            {noMoreCourses && <div className="noCourse">No more to load</div>}
        </Container>
    );
};
