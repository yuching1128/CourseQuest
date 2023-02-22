import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import {Nav} from "react-bootstrap";
import {Image} from "react-bootstrap";

export default function Header() {
    return (
        <Navbar bg="light" sticky="top" className="Header" expand="lg" >
            <Container>
                <Navbar.Brand href="/">
                    <Image alt="logo" src={require('../images/logo.png')}
                           height="40"
                           className="d-inline-block align-top " />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/" end>Home</Nav.Link>
                        <Nav.Link href="/">Course</Nav.Link>
                        <Nav.Link href="/">Be a Mentor</Nav.Link>
                        <Nav.Link href="/">Find my Mentor</Nav.Link>

                    </Nav>
                    <Nav>
                        <Nav.Link href="/login">Login</Nav.Link>
                        <Nav.Link href="/">Sign Up</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}