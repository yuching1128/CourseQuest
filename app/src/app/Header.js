import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import {Nav} from "react-bootstrap";
import {Image} from "react-bootstrap";
import { NavLink } from 'react-router-dom';
import {useSelector} from "react-redux";

export default function Header() {

    const user = useSelector(state => state.user)
    console.log(user.universityId)

    return (
        <Navbar bg="light" sticky="top" className="Header" expand="lg" >
            <Container>
                <Navbar.Brand as={NavLink} to="/">
                    <Image alt="logo" src={require('../images/logo.png')}
                           height="40"
                           className="d-inline-block align-top " />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={NavLink} to="/" end>Home</Nav.Link>
                        <Nav.Link as={NavLink} to={`/university/${user.universityId}/courses`}>Courses</Nav.Link>
                        <Nav.Link as={NavLink} to="/">Be a Mentor</Nav.Link>
                        <Nav.Link as={NavLink} to="/">Find my Mentor</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link as={NavLink} to="/login">Login</Nav.Link>
                        <Nav.Link as={NavLink} to="/signup">Sign Up</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}