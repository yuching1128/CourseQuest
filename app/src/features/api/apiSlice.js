import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    // todo update mocked baseUrl to actual back-end server once available.
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }),
    endpoints: builder => ({
        getCourses: builder.query({
            query: ({universityId, page, size} ) => {
                return `university/${universityId}/courses?pageNum=${page}&pageSize=${size}`
            }
        }),
        getCourse: builder.query({
            query: ({universityId,courseId}) => {
                return `university/${universityId}/course/${courseId}`
            }
        }),
        getCourseReviews: builder.query({
            query: ({universityId,courseId}) => `university/${universityId}/course/${courseId}/reviews`
        }),
        getDepartments: builder.query({
            query: universityId => `university/${universityId}/departments`
        }),
        getLevels: builder.query({
            query: universityId => `university/${universityId}/levels`
        }),
    })
})

export const { useGetCoursesQuery, useGetCourseQuery, useGetCourseReviewsQuery, useGetDepartmentsQuery, useGetLevelsQuery } = apiSlice