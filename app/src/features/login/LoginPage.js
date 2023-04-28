import Container from "react-bootstrap/Container";
import Form from 'react-bootstrap/Form';
import { useForm } from "react-hook-form";
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
import { useDispatch, useSelector } from "react-redux";
import { selectUserProfile, setUserProfile } from "../userProfile/userProfileSlice";
import googleImg from '../../images/google.png';
import reviewImg from "../../images/review.png";

const CLIENT_ID = "310536116903-4oolc727rmg62b4qsf58p8a3i76o4pfq.apps.googleusercontent.com";
const CLIENT_SECRET = "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";

export default function LoginPage() {

    const [user, setUser] = useState({});
    const userProfile = useSelector(selectUserProfile);
    // const [profile, setprofile] = useState([]);
    const [open, setOpen] = useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleClose = () => {
        setOpen(false);
    };

    const handleUniIDCheck = (userInfo, access_token) => {
        if (!userInfo.email.endsWith("@vt.edu")) {
            setOpen(true);
            setUser({});
        } else {
            localStorage.setItem("userInfo", JSON.stringify(userInfo));
            dispatch(
                setUserProfile({
                    type: "userProfile/userProfileSet",
                    payload: userInfo,
                })
            )
            localStorage.setItem("access_token", access_token);
            navigate("/");
        }
    }

    useEffect(
        () => {
            if (user.email) {
                handleUniIDCheck(user.email);
            }
        },
        [user, localStorage.getItem("userInfo")]
    );

    const logOut = () => {
        googleLogout();
        // setprofile(null);
    }

    const responseMessage = (response) => {
        console.log(response);
        let userProfile = jwt_decode(response.credential);
        console.log("decoded token", userProfile);
        setUser(userProfile);
    };

    const login = useGoogleLogin({
        onSuccess: async tokenResponse => {
            console.log(tokenResponse);
            const userInfo = await axios.get(
                'https://www.googleapis.com/oauth2/v3/userinfo',
                { headers: { Authorization: `Bearer ${tokenResponse.access_token}` } }
            );
            handleUniIDCheck(userInfo.data, tokenResponse.access_token);
        },
        onError: errorResponse => console.log(errorResponse)
    })

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

            <div className="LoginForm">
                <p className="loginFromTitle">Login</p>
                <div className="google-login">
                    {/* <GoogleLogin onSuccess={responseMessage} onError={errorMessage} /> */}
                    <button className="loginBtn" hidden={userProfile.email} onClick={login} >
                        <img className="googleImg" src={googleImg} alt="Google logo" />
                        Login with Google
                    </button>
                    <p hidden={!userProfile.email}>Already loggedin as {userProfile.given_name} {userProfile.family_name}</p>
                </div>
            </div>

            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description">
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