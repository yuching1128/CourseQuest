import React, { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import { useSelector } from "react-redux";
import { selectUserProfile } from "../userProfile/userProfileSlice";

import searchingImg from '../../images/searching.png';
import reviewImg from '../../images/review.png';
import connectImg from '../../images/connect.png';
import {Button} from "react-bootstrap";
export default function HomePage() {
    const userProfile = useSelector(selectUserProfile)
    if (userProfile.email) {
        return (
            <Container className="HomePage">
                <p className="welcome-text">Welcome back {userProfile.given_name}.</p>
                <p>Your course recommendations: </p>
            </Container>
        );
    }
    else {
        return (
            <Container className="HomePage">
                <p className="welcome-text">Explore courses. Read reviews. Connect with students.</p>
                <div className="welcomeColumns">
                    <div className="welcomeColumn">
                        <img className="homepageImage" src={searchingImg} alt="searching" />
                        <p className="homepageImageText">Search</p>
                    </div>
                    <div className="welcomeColumn">
                        <img className="homepageImage" src={reviewImg} alt="review" />
                        <p className="homepageImageText">Review</p>
                    </div>
                    <div className="welcomeColumn">
                        <img className="homepageImage" src={connectImg} alt=" connect" />
                        <p className="homepageImageText">Advise</p>
                    </div>
                </div>
                <div className="buttonDiv"><Button className="homepageButton">Get Started</Button></div>

            </Container>
        );
    }
}