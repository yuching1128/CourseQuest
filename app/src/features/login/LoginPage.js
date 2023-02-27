import Container from "react-bootstrap/Container";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {useEffect, useRef, useState} from "react";
import {Image, Stack} from "react-bootstrap";

export default function LoginPage() {
    const [loginErrors, setLoginErrors] = useState({})
    const emailField = useRef()
    const passwordField = useRef()

    useEffect(() => {
        emailField.current.focus()
    }, []);

    const onSubmit = (ev) => {
        ev.preventDefault()
        const email = emailField.current.value
        const password = passwordField.current.value

        // error handling
        const errors = {}
        if (!email) {
            errors.email = 'Email cannot be empty'
        }
        if (!password) {
            errors.password = 'Password cannot be empty'
        }
        setLoginErrors(errors)
        if (Object.keys(errors).length > 0 ) {
            return
        }

        // success
        // TODO - DISPATCH TO REDUX AND SEND TO BACKEND API FOR PROCESSING
        console.log(`Email: ${email} | Password: ${password}`)
    }

    return (
        <Container className="LoginPage">
            <Stack direction="horizontal" gap={3} className="LoginContent">
                <Image alt="Logo" src={require('../../images/login-page-image.png') }
                       width="400px"
                       className="d-inline-block align-top " />
                <Form onSubmit={onSubmit} className="LoginForm">
                    <h1>Login</h1>
                    <Form.Group className="mb-3" controlId="loginEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" ref={emailField} />
                        {loginErrors.email && <Form.Text className="text-danger">{loginErrors.email}</Form.Text>}
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="loginPassword" >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" ref={passwordField} />
                        {loginErrors.password && <Form.Text className="text-danger">{loginErrors.password}</Form.Text>}
                    </Form.Group>

                    <Button variant="primary" type="submit" className="LoginButton">Login</Button>
                </Form>
            </Stack>
        </Container>
    );
}