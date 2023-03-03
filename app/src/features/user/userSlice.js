import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    'id': 1,
    'firstName': 'Joe',
    'lastName': 'Smith',
    'email': 'joe.smith123@vt.edu',
    'universityId': 1
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {}
})

export default userSlice.reducer
