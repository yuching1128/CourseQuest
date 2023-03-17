import Container from "react-bootstrap/Container";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useForm } from "react-hook-form";
import{faAnglesRight} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

export default function LoginPage() {

    const { register, handleSubmit, formState: { errors } } = useForm();

    const onSubmit = (data) => {
        // TODO SUBMIT LOGIN DATA TO API FOR PROCESSING
        console.log(data)
    }

    return (
        <Container className="LoginPage">
            <div className="welcomeBlock">
                <p className="welcomeText">Welcome to CourseQuest!</p>
                <div className="boxOrange">
                    <div className="boxGreen">
                        <div className="boxRed">
                            <p className="loginText">Login to Start Explore Your Courses <FontAwesomeIcon icon={faAnglesRight} /></p>
                            <p className="loginTextSmall">Connect with fellow students and get the inside scoop on courses to find your perfect course match with CourseQuest</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className="LoginForm">
                <Form onSubmit={handleSubmit(onSubmit)}>
                    <p className="loginFromTitle">Login</p>
                    <Form.Group className="mb-3" controlId="loginEmail">
                        <Form.Label className="login-info">Email</Form.Label>
                        <Form.Control className="login-component" placeholder="Enter email" {...register("email", { required: 'Email is required' })} />
                        {errors.email && <p className="errorMessage">{errors.email?.message}</p>}
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="loginPassword" >
                        <Form.Label className="login-info">Password</Form.Label>
                        <Form.Control className="login-component" type="password" placeholder="Password"  {...register("password", { required: 'Password is required' })} />
                        {errors.password && <p className="errorMessage">{errors.password?.message}</p>}
                    </Form.Group>
                    <button variant="primary" type="submit" className="main-login-button">Login</button>
                </Form>
            </div>
        </Container>
    );
}