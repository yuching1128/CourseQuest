import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import {faCopyright} from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import {NavLink} from "react-router-dom";
import {Nav} from "react-bootstrap";

export default function Footer() {
    return (
        <Navbar className="Footer" expand="lg" >
            <Container>
                <div className="about">
                    <Nav.Link as={NavLink} to="/" className="about-us">About Us</Nav.Link>
                </div>
            </Container>
            <p className="copy-right"><FontAwesomeIcon icon={faCopyright} /> All Rights Reserved</p>
        </Navbar>
    )
}