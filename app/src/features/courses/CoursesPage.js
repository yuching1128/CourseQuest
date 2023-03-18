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

    const pageSize = 2;

    const [currentPage, setCurrentPage] = useState(
        localStorage.getItem('curpage') ? parseInt(localStorage.getItem('curpage')) : 0
    );

    const [courseList, setCourseList] = useState(
        localStorage.getItem('courseList') ? JSON.parse(localStorage.getItem('courseList')) : []
    );

    const [isLoading, setIsLoading] = useState(false);

    const [error, setError] = useState(null);

    const [noMoreCourses, setNoMoreCourses] = useState(
        localStorage.getItem('noMoreCourses') ? localStorage.getItem('noMoreCourses') === 'true' : false
    );

    const [shouldLoadMore, setShouldLoadMore] = useState(false);

    useEffect(() => {
        const fetchCourses = async () => {
            setIsLoading(true);

            try {
                const response = await fetch(`/api/university/${universityId}/courses/${currentPage}/${pageSize}`);
                const data = await response.json();

                if (data.length === 0) {
                    setNoMoreCourses(true);
                    localStorage.setItem('noMoreCourses', true);
                } else {
                    setCourseList((prevCourseList) => [...prevCourseList, ...data]);
                }
            } catch (error) {
                setError(error);
            } finally {
                setIsLoading(false);
                setShouldLoadMore(false);
            }
        };

        if (shouldLoadMore) {
            fetchCourses();
        } else if (courseList.length === 0) {
            fetchCourses();
        }
    }, [universityId, currentPage, pageSize, shouldLoadMore, courseList]);

    let content;

    if (isLoading && courseList.length === 0) {
        content = <Spinner text="Loading..." />;
    } else if (courseList.length > 0) {
        content = courseList.map((course) => <CourseExcerpt key={course.id} course={course} />);
    } else if (error) {
        content = <div>{error.toString()}</div>;
    }

    useEffect(() => {
        localStorage.setItem('curpage', currentPage.toString());
        localStorage.setItem('courseList', JSON.stringify(courseList));
        localStorage.setItem('noMoreCourses', noMoreCourses.toString());
    }, [currentPage, courseList, noMoreCourses]);

    const handleLoadMore = () => {
        setShouldLoadMore(true);
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