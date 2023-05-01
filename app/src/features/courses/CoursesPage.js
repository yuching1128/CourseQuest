import React, {useState} from 'react';
import { Link, useParams } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import {Form, Spinner} from 'react-bootstrap';
import ButtonGroup from '@mui/material/ButtonGroup';
import Pagination from '@mui/material/Pagination';
import {useGetDepartmentsQuery, useGetLevelsQuery, useSearchCoursesQuery} from "../api/apiSlice";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";

let CourseExcerpt = ({ course }) => {
    return (
        <Link to={`/university/${course.university.id}/courses/${course.id}`} className="course-button">
            <article className="course-excerpt" key={course.id}>
                <h3>{course.dept.name}-{course.courseNum}: {course.name}</h3>
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
    const pageSize = 10;

    // state
    const [currentPage, setCurrentPage] = useState(1);
    const [searchText, setSearchText] = useState("");
    const [searchDept, setSearchDept] = useState("");
    const [searchLevel, setSearchLevel] = useState("");

    // API calls
    const {
        data: deptList=[]
    } = useGetDepartmentsQuery(universityId);
    const depthListSorted = deptList.slice();
    depthListSorted.sort(function (a,b) {
        return a.name.localeCompare(b.name)
    })

    const {
        data: levelsList=[]
    } = useGetLevelsQuery(universityId);
    const levelsListSorted = levelsList.slice();
    levelsListSorted.sort(function (a,b) {
        return a.name.localeCompare(b.name)
    })

    const {
        data: courseSearch=[],
        isLoading,
        isSuccess,
        isError,
        error,
    } = useSearchCoursesQuery({ dept: searchDept, searchText: searchText, level: searchLevel, universityId: universityId, page: currentPage, size: pageSize });

    const maxPages = Math.ceil(courseSearch.totalCourses/pageSize);

    // generate list of departments and levels for search component
    const deptOptions = depthListSorted.map((dept) => (
        <option key={dept.id} value={dept.name}>
            {dept.name}
        </option>
    ))
    const levelOptions = levelsListSorted.map((level) => (
        <option key={level.id} value={level.name}>
            {level.name}
        </option>
    ))

    // Handlers
    const onSearchClicked = (event) => {
        event.preventDefault();
        console.log(event.target.searchText.value)
        console.log(event.target.department.value)
        console.log(event.target.level.value)
        setSearchText(event.target.searchText.value)
        setSearchDept(event.target.department.value)
        setSearchLevel(event.target.level.value)
    }

    // Display Content
    let content;

    if (isLoading) {
        content = <Spinner text="Loading..." />;
    } else if (isSuccess) {
        const renderedCourses = courseSearch.courses.map((course) => <CourseExcerpt key={course.id} universityId={universityId} course={course} />);
        content = <div className="posts-container">{renderedCourses}</div>
    } else if (isError) {
        content = <div>{error.toString()}</div>;
    }

    return (
        <Container>
            <div className="searchComponent">
                <Form className="searchForm" onSubmit={onSearchClicked}>
                    <div className="searchBarContainer">
                        <input className="searchBar"
                               type="text"
                               placeholder="Search for keywords"
                               name="searchText"
                        />
                        <button className="searchButton" type="submit"><FontAwesomeIcon icon={faMagnifyingGlass}/></button>
                    </div>
                    <div className="filterContainer">
                        <Form.Group controlId="department" className="filterItem">
                            <Form.Label className="filterLabel">Department:</Form.Label>
                            <Form.Select className="department" name="department">
                                <option selected value="">All</option>
                                {deptOptions}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group controlId="level" className="filterItem">
                            <Form.Label className="filterLabel">Level:</Form.Label>
                            <Form.Select className="level" name="level">
                                <option selected value="">All</option>
                                {levelOptions}
                            </Form.Select>
                        </Form.Group>
                    </div>
                </Form>
            </div>
            <div className="courses-list">
                {content}
            </div>
            <ButtonGroup className="pagination-buttons" style={{ display: 'flex', justifyContent: 'center', marginTop: '2em', marginBottom: '50px' }}>
                <Pagination
                    count={maxPages}
                    page={currentPage}
                    onChange={(e, value) => setCurrentPage(value)}
                    color="primary"
                    size="large"
                />
            </ButtonGroup>
        </Container>
    );
};