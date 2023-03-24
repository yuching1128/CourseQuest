import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    // todo update mocked baseUrl to actual back-end server once available.
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }),
    endpoints: builder => ({
        getCourses: builder.query({
            query: ({universityId, currentPage, pageSize} ) => {
                console.log(universityId, currentPage, pageSize);
                return `university/${universityId}/courses?pageNum=${currentPage}&pageSize=${pageSize}`
            }
        }),
        getCourse: builder.query({
            query: courseId => `courses/${courseId}`
        }),
        getCourseReviews: builder.query({
            query: courseId => `courses/${courseId}/reviews`
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