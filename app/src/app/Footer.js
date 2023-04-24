import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import React from "react";
import {NavLink} from "react-router-dom";
import {Nav, Stack} from "react-bootstrap";

export default function Footer() {
    return (
        <Navbar className="Footer" expand="lg" >
            <Container className="FooterColumns">
                <div className="FooterColumn">
                    <p className="FooterSubtitle">COURSEQUEST</p>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Home</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Courses</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Profile</Nav.Link>
                </div>
                <div className="FooterColumn">
                    <p className="FooterSubtitle">COMPANY</p>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">About</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Careers</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Help</Nav.Link>
                </div>
                <div className="FooterColumn">
                    <p className="FooterSubtitle">LEGAL</p>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Terms</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Privacy</Nav.Link>
                    <Nav.Link as={NavLink} to="/" className="FooterLink">Cookies</Nav.Link>
                </div>

            </Container>
        </Navbar>
    )
}