import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    email: "",
    given_name: "",
    family_name: "",

}

const userProfileSlice = createSlice({
    name: "userProfile",
    initialState,
    reducers: {
        setUserProfile: (state, action) => {
            return {
                email: action.payload.payload.email,
                given_name: action.payload.payload.given_name,
                family_name: action.payload.payload.family_name,
                access_token: action.payload.payload.access_token
            };
        },
    },
});

export const selectUserProfile = (state) => state.userProfile;

export const { setUserProfile } = userProfileSlice.actions;
export default userProfileSlice.reducer;