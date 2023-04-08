import React from 'react';
import {useGetUserInfoQuery} from "../api/apiSlice";


export const UserInfo = ({userProfileData}) => {


    let userFirstName, userLastName, userEmail

    if (userProfileData !== undefined) {
        userFirstName = userProfileData.firstName;
        userLastName = userProfileData.lastName;
        userEmail = userProfileData.email;
    }

    return (
        <div className="profileBlock">
            <div>
                <p className="profileText">First Name: </p>
                <p className="userName">{userFirstName}</p>
            </div>
            <div>
                <p className="profileText">Last Name: </p>
                <p className="userName">{userLastName}</p>
            </div>
            <div>
                <p className="profileText">E-mail: </p>
                <p className="userEmail">{userEmail}</p>
            </div>
        </div>
    );
}
