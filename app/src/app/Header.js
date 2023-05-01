import { useDispatch, useSelector } from "react-redux";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { Navbar, Container, Nav, Image } from "react-bootstrap";
import { useEffect, useState } from "react";
import { googleLogout } from "@react-oauth/google";
import { selectUserProfile, setUserProfile } from "../features/userProfile/userProfileSlice";
import { faCircleUser } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function Header() {

    const user = useSelector(state => state.user);
    const userProfile = useSelector(selectUserProfile);
    const [loggedIn, setLoggedIn] = useState(Boolean(localStorage.getItem("access_token")));
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        setLoggedIn(Boolean(localStorage.getItem("access_token")));
    }, [localStorage])

    const logout = () => {
        googleLogout();
        dispatch(
            setUserProfile({
                type: "userProfile/userProfileSet",
                payload: {
                    given_name: null,
                    family_name: null,
                    email: null,
                },
            })
        )
        localStorage.setItem("userInfo", null);
        localStorage.setItem("access_token", null);
        navigate("/login")
    }

    return (
        <Navbar sticky="top" className="Header" expand="lg">
            <Container>
                <Navbar.Brand as={NavLink} to="/">
                    <Image alt="logo" src={require('../images/logo.png')} height="40" className="d-inline-block align-top " />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav" style={{ justifyContent: 'space-around', marginLeft: '5em' }}>
                    <Nav>
                        <Nav.Link as={NavLink} to="/" className="nav-link">Home</Nav.Link>
                        <Nav.Link hidden={!userProfile.email} as={NavLink} to={`/university/${user.universityId}/courses`} className="nav-link">Courses</Nav.Link>
                        <Nav.Link hidden={!userProfile.email} as={NavLink} to={`/university/${user.universityId}/mentor`} className="nav-link">Be a Mentor</Nav.Link>
                        <Nav.Link hidden={!userProfile.email} as={NavLink} to={`/university/${user.universityId}/mentee`} className="nav-link">Find my Mentor</Nav.Link>
                    </Nav>
                    <Nav style={{ marginRight: '-6em' }}>
                        <Nav.Link as={NavLink} hidden={userProfile.email} to={'/login'} className="nav-link button-login">Sign In</Nav.Link>
                        <Nav.Link as={NavLink} to={'/user/profile'} className="nav-link button-profile" hidden={!userProfile.email}>
                            <FontAwesomeIcon icon={faCircleUser} />
                        </Nav.Link>
                        <button hidden={!userProfile.email} onClick={logout} className="button-logout">Logout</button>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}
