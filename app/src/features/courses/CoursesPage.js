import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import { Spinner } from 'react-bootstrap';
import {useGetCoursesQuery} from "../api/apiSlice";

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

    const size = 2;

    const [page, setPage] = useState(
        localStorage.getItem('curpage') ? parseInt(localStorage.getItem('curpage')) : 0
    );

    const [List, setList] = useState(
        localStorage.getItem('list') ? JSON.parse(localStorage.getItem('list')) : []
    );

    const [noMoreCourses, setNoMoreCourses] = useState(
        localStorage.getItem('noMoreCourses') ? localStorage.getItem('noMoreCourses') === 'true' : false
    );

    const {
        data: courseList,
        isLoading,
        isFetching,
        isSuccess,
        isError,
        error,
    } = useGetCoursesQuery({universityId: universityId, page: page, size: size});
    console.log(courseList);

    useEffect(()=>{
        if(isSuccess){
            if(courseList.length > 0){
                setList((prevList) => [...new Set([...prevList, ...courseList])]);
            }
            if(courseList.length < size){
                setNoMoreCourses(true);
            }
        }
    }, [isSuccess, courseList]);

    let content;

    if (isLoading) {
        content = <Spinner text="Loading..."/>;
    } else if (isSuccess) {
        content = List.map((course) => <CourseExcerpt key={course.id} course={course}/>);
    } else if (isError) {
        content = <div>{error.toString()}</div>;
    }
    useEffect(() => {
        localStorage.setItem('curpage', page);
        localStorage.setItem('list', JSON.stringify(List));
        localStorage.setItem('noMoreCourses', noMoreCourses.toString());
    }, [page, List, noMoreCourses]);

    const handleLordMoreClick = () => {
        setPage(prevPage => prevPage+1);
    }
    return (
        <Container>
            <div className="courses-list">{content}</div>
            {!noMoreCourses && (
                <button className="courseList-Load"
                    onClick={handleLordMoreClick}
                    disabled={isFetching}
                >
                    {isFetching? 'Loading':'Load more'}
                </button>
            )}
            {noMoreCourses && <div className="noCourse">No more to load</div>}
        </Container>
    );
};