import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import {Nav} from "react-bootstrap";
import { NavLink } from 'react-router-dom';

export default function Footer() {
    return (
        <Navbar bg="light" className="Footer" expand="lg" >
            <Container>
                <Nav>
                    <Nav.Link as={NavLink} to="/">About Us</Nav.Link>
                    <Nav.Link as={NavLink} to="/">Privacy Notice</Nav.Link>
                </Nav>
                All Rights Reserved
            </Container>
        </Navbar>
    )



}