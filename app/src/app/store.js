import { configureStore } from '@reduxjs/toolkit'
import coursesReducer from '../features/courses/coursesSlice'
import userReducer from '../features/user/userSlice'
import { apiSlice } from '../features/api/apiSlice'

export default configureStore({
    reducer: {
        courses: coursesReducer,
        user: userReducer,
        [apiSlice.reducerPath]: apiSlice.reducer
    },
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware().concat(apiSlice.middleware)
})