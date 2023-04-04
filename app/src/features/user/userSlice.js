import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    'id': 1,
    'firstName': 'Eugene',
    'lastName': 'Feng',
    'universityId': 1
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {}
})

export default userSlice.reducer
