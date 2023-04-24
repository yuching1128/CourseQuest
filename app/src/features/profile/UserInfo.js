import React from 'react';
import {useGetUserInfoQuery} from "../api/apiSlice";


export const UserInfo = ({userProfileData}) => {


    let userFirstName, userLastName, userEmail, userUniversity

    if (userProfileData !== undefined) {
        userFirstName = userProfileData.firstName;
        userLastName = userProfileData.lastName;
        userEmail = userProfileData.email;
        userUniversity = userProfileData.university.name;
    }

    return (
        <div className="profileBlock">
            <div>
                <p className="profileText">First Name: </p>
                <p className="userFirstName">{userFirstName}</p>
            </div>
            <div>
                <p className="profileText">Last Name: </p>
                <p className="userLastName">{userLastName}</p>
            </div>
            <div>
                <p className="profileText">E-mail: </p>
                <p className="userEmail">{userEmail}</p>
            </div>
            <div>
                <p className="profileText">University: </p>
                {/*<p className="userUniversity">Virginia Tech</p>*/}
                <p className="userUniversity">{userUniversity}</p>
            </div>
        </div>
    );
}
