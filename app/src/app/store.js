import { configureStore } from '@reduxjs/toolkit'
import coursesReducer from '../features/courses/coursesSlice'
import { apiSlice } from '../features/api/apiSlice'

export default configureStore({
    reducer: {
        courses: coursesReducer,
        [apiSlice.reducerPath]: apiSlice.reducer
    },
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware().concat(apiSlice.middleware)
})