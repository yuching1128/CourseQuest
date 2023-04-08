import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { Navbar, Container, Nav, Image } from "react-bootstrap";
import { useEffect, useState } from "react";
import { googleLogout } from "@react-oauth/google";

export default function Header() {

    const user = useSelector(state => state.user)
    const [userInfo, setUserInfo] = useState({})
    const [loggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        setUserInfo(JSON.parse(sessionStorage.getItem("userInfo")));
        setLoggedIn(Boolean(userInfo.name));
    }, [])

    const logout = () => {
        googleLogout();
        setUserInfo(null);
        setLoggedIn(null);
        sessionStorage.setItem("userInfo", null);
        sessionStorage.setItem("access_token", null);
    }

    return (
        <Navbar sticky="top" className="Header" expand="lg">
            <Container>
                <Navbar.Brand as={NavLink} to="/">
                    <Image alt="logo" src={require('../images/logo.png')}
                        height="40"
                        className="d-inline-block align-top " />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={NavLink} to="/" className="nav-button">Home</Nav.Link>
                        <Nav.Link as={NavLink} to={`/university/${user.universityId}/courses`} className="nav-button">Courses</Nav.Link>
                        <Nav.Link as={NavLink} to={`/university/${user.universityId}/mentor`} className="nav-button">Be a Mentor</Nav.Link>
                        <Nav.Link as={NavLink} to="/" className="nav-button">Find my Mentor</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link hidden={loggedIn} as={NavLink} to="/login" className="button-login">Sign In</Nav.Link>
                        <button hidden={!loggedIn} onClick={logout} className="button-signup">Logout</button>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}
