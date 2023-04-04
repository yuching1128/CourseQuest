import Container from "react-bootstrap/Container";
import Form from 'react-bootstrap/Form';
import { useForm } from "react-hook-form";
import { faAnglesRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import { GoogleLogin, googleLogout, useGoogleLogin } from '@react-oauth/google';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";

const CLIENT_ID = "310536116903-4oolc727rmg62b4qsf58p8a3i76o4pfq.apps.googleusercontent.com";
const CLIENT_SECRET = "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";

export default function LoginPage() {

    const [user, setUser] = useState({});
    const [profile, setprofile] = useState([]);
    const [open, setOpen] = useState(false);
    const navigate = useNavigate();

    const handleClose = () => {
        setOpen(false);
    };

    const handleUniIDCheck = (email) => {
        if (!email.endsWith("@vt.edu")) {
            setOpen(true);
            setUser({});
        } else {
            navigate("/");
        }
    }

    useEffect(
        () => {
            if (user.email) {
                console.log("triggered use effect");
                handleUniIDCheck(user.email);
            }
        },
        [user]
    );

    const logOut = () => {
        googleLogout();
        setprofile(null);
    }

    const responseMessage = (response) => {
        console.log(response);
        let userProfile = jwt_decode(response.credential);
        console.log("decoded token", userProfile);
        setUser(userProfile);
    };

    const errorMessage = (error) => {
        console.log(error);
    };

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
                <p className="loginFromTitle">Login</p>
                <div className="google-login">
                    <GoogleLogin onSuccess={responseMessage} onError={errorMessage} />
                </div>
                <hr className="login-seperate-hr" />
                <Form onSubmit={handleSubmit(onSubmit)}>
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
                    <div className="login">
                        <button variant="primary" type="submit" className="main-login-button">Login</button>
                    </div>
                </Form>
            </div>

            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Not a Virginia Tech Email ID"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        This email is not associated with Virginia Tech. Please login using your Virginia Tech Email ID.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} autoFocus>Ok</Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
}