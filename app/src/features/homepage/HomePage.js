import React, { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import { useSelector } from "react-redux";
import { selectUserProfile } from "../userProfile/userProfileSlice";
import { useGetRecommendedCoursesQuery } from "../api/apiSlice";

import searchingImg from '../../images/searching.png';
import reviewImg from '../../images/review.png';
import connectImg from '../../images/connect.png';
import { Button } from "react-bootstrap";
import backgroundImage from '../../images/hero.png'
import { Link } from "react-router-dom";
export default function HomePage() {
    const userProfile = useSelector(selectUserProfile)
    const { data: recommendedCourses = [] } = useGetRecommendedCoursesQuery();

    const CourseList = () => {
        console.log(recommendedCourses)
        let courses = [];
        if (recommendedCourses) {
            courses = recommendedCourses.map((course) =>
                <Link to={`/university/${course.university.id}/courses/${course.id}`} >
                    <li>{course.courseNum}: {course.name}</li>
                </Link>
            );
        }
        return <ul>{courses}</ul>
    }
    if (userProfile.email) {
        return (
            <Container className="HomePage" >
                <p className="welcome-back">Welcome back {userProfile.given_name}.</p>
                <p className="course-recommendation">Your course recommendations:
                    <CourseList />
                </p>
            </Container>
        );
    }
    else {
        return (
            <Container className="HomePage">
                <p className="welcome-text">Explore courses. Read reviews. Connect with students.</p>
                <p className="welcome-description">CourseQuest is a perfect platform for all your course selection needs. We understand the importance of choosing the right courses for your academic and professional growth. Start exploring courses, reading reviews, and connecting with other students to enhance your academic experience.</p>
                {/*<div className="welcomeColumns">*/}
                {/*    <div className="welcomeColumn">*/}
                {/*        <img className="homepageImage" src={searchingImg} alt="searching" />*/}
                {/*        <p className="homepageImageText">Search</p>*/}
                {/*    </div>*/}
                {/*    <div className="welcomeColumn">*/}
                {/*        <img className="homepageImage" src={reviewImg} alt="review" />*/}
                {/*        <p className="homepageImageText">Review</p>*/}
                {/*    </div>*/}
                {/*    <div className="welcomeColumn">*/}
                {/*        <img className="homepageImage" src={connectImg} alt=" connect" />*/}
                {/*        <p className="homepageImageText">Advise</p>*/}
                {/*    </div>*/}
                {/*</div>*/}
                <div className="buttonDiv">
                    <Link to="/login">
                        <button className="homepageButton">Get Started</button>
                    </Link>
                </div>

            </Container>
        );
    }
}