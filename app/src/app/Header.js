import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { Navbar, Container, Nav, Image } from "react-bootstrap";

export default function Header() {

    const user = useSelector(state => state.user)

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
                        <Nav.Link as={NavLink} to="/" className="nav-button">Be a Mentor</Nav.Link>
                        <Nav.Link as={NavLink} to="/" className="nav-button">Find my Mentor</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link as={NavLink} to="/login" className="button-login">Login</Nav.Link>
                        <Nav.Link as={NavLink} to="/signup" className="button-signup">Sign Up</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}
