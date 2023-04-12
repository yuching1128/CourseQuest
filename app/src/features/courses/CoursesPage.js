import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import { Spinner } from 'react-bootstrap';
import { useGetCoursesQuery } from "../api/apiSlice";
import { SearchComponent } from "../search/search";
import classnames from 'classnames';

let CourseExcerpt = ({ course }) => {
    return (
        <article className="course-excerpt" key={course.id} >
            <Link to={`/university/${course.university.id}/courses/${course.id}`} className="course-button">
                <h3>{course.name}</h3>
            </Link>
            <div className="allInfo">
                <div className="ratingBox">
                    <p className="rating-text">Rating</p>
                    <div className="rating-point">{course.rating ? course.rating : "N/A"}</div>
                </div>
                <div className="courseInfo">
                    <p className="des">{course.description ? course.description.substring(0, 100) + "..." : "No Description Available"}</p>
                </div>
            </div>
            <hr />
        </article>
    );
};

export const CoursesPage = () => {
    const { universityId } = useParams();

    const size = 10;

    const [page, setPage] = useState(
        localStorage.getItem('curpage') ? parseInt(localStorage.getItem('curpage')) : 0
    );

    const [List, setList] = useState(
        localStorage.getItem('list') ? JSON.parse(localStorage.getItem('list')) : []
    );

    const [noMoreCourses, setNoMoreCourses] = useState(
        localStorage.getItem('noMoreCourses') ? localStorage.getItem('noMoreCourses') === 'true' : false
    );

    const [shouldLoadMore, setShouldLoadMore] = useState(false);

    const {
        data: courseList,
        isLoading,
        isFetching,
        isSuccess,
        isError,
        error,
    } = useGetCoursesQuery({ universityId: universityId, page: page, size: size });

    useEffect(() => {
        if (isSuccess && List.length === 0) {
            setList(courseList);
            setPage(1);
        }
        if (isSuccess && !noMoreCourses && shouldLoadMore) {
            if (courseList && courseList.length > 0) {
                setList((prevList) => [...new Set([...prevList, ...courseList])]);
            }
            if (!courseList || courseList.length < size) {
                setNoMoreCourses(true);
            }
            setShouldLoadMore(false);
        }
    }, [isSuccess, noMoreCourses, courseList]);

    let content;

    if (isLoading) {
        content = <Spinner text="Loading..." />;
    } else if (isSuccess) {
        const renderedCourses = List.map((course) =>
            <CourseExcerpt key={course.id} universityId={universityId} course={course} />);
        const containerClassname = classnames('posts-container', {
            disabled: isFetching
        })
        content = <div className={containerClassname}>{renderedCourses}</div>
    } else if (isError) {
        content = <div>{error.toString()}</div>;
    }

    useEffect(() => {
        localStorage.setItem('curpage', page);
        localStorage.setItem('list', JSON.stringify(List));
        localStorage.setItem('noMoreCourses', noMoreCourses.toString());
    }, [page, List, noMoreCourses]);

    const handleLoadMoreClick = async () => {
        setPage(page + 1);
        setShouldLoadMore(true);
    }

    return (
        <Container>
            <SearchComponent />
            <div className="courses-list">{content}</div>
            {!noMoreCourses && (
                <button className="courseList-Load"
                    onClick={handleLoadMoreClick}
                    disabled={isFetching}
                >
                    {isFetching ? 'Loading' : 'Load more'}
                </button>
            )}
            {noMoreCourses && <div className="noCourse">No more to load</div>}
        </Container>
    );
};