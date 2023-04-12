import { configureStore } from '@reduxjs/toolkit'
import coursesReducer from '../features/courses/coursesSlice'
import userReducer from '../features/user/userSlice'
import { apiSlice } from '../features/api/apiSlice'
import userProfileReducer from '../features/userProfile/userProfileSlice'

export default configureStore({
    reducer: {
        courses: coursesReducer,
        user: userReducer,
        [apiSlice.reducerPath]: apiSlice.reducer,
        userProfile: userProfileReducer
    },
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware().concat(apiSlice.middleware)
})