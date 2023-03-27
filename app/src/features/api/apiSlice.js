import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }),
    endpoints: builder => ({
        getCourses: builder.query({
            query: ({universityId, page, size} ) => {
                return `university/${universityId}/courses?pageNum=${page}&pageSize=${size}`
            }
        }),
        getCourse: builder.query({
            query: ({universityId, courseId}) => `university/${universityId}/courses/${courseId}`
        }),
        getCourseReviews: builder.query({
            query: ({universityId, courseId}) => `university/${universityId}/courses/${courseId}/review`
        }),
        getDepartments: builder.query({
            query: universityId => `university/${universityId}/departments`
        }),
        getLevels: builder.query({
            query: universityId => `university/${universityId}/levels`
        }),
        addNewReview: builder.mutation({
            query: ({universityId, courseId, newReview, isAnonymous}) => ({
                url: `university/${universityId}/courses/${courseId}/review`,
                method: 'POST',
                body: newReview
            })
        })
    })
})

export const { useGetCoursesQuery, useGetCourseQuery, useGetCourseReviewsQuery, useGetDepartmentsQuery, useGetLevelsQuery, useAddNewReviewMutation } = apiSlice