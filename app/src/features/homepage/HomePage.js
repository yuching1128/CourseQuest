import { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import { useSelector } from "react-redux";
import { selectUserProfile } from "../userProfile/userProfileSlice";

export default function HomePage() {
    const userProfile = useSelector(selectUserProfile)
    return (
        <Container className="HomePage">
            <h1>Homepage</h1>
            {Boolean(userProfile) ? (<p>Welcome {userProfile.given_name}!</p>) : <p>Welcome!</p>}
        </Container>
    );
}