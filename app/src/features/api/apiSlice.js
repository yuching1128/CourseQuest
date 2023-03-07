import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    // todo update mocked baseUrl to actual back-end server once available.
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:4000/' }),
    endpoints: builder => ({
        getCourses: builder.query({
            query: universityId => `university/${universityId}/courses`
        }),
        getCourse: builder.query({
            query: courseId => `/courses/${courseId}`
        }),
        getCourseReviews: builder.query({
            query: courseId => `/courses/${courseId}/reviews`
        }),
    })
})

export const { useGetCoursesQuery, useGetCourseQuery, useGetCourseReviewsQuery } = apiSlice