// API Documentation http://localhost:8080/swagger-ui.html#/
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }),
    tagTypes: ['Review'],
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
            query: ({universityId, courseId}) => `university/${universityId}/courses/${courseId}/review`,
            providesTags: ['Review']
        }),
        getDepartments: builder.query({
            query: universityId => `university/${universityId}/departments`
        }),
        getLevels: builder.query({
            query: universityId => `university/${universityId}/levels`
        }),
        getReview: builder.query({
            query: ({universityId, courseId, reviewId}) => `university/${universityId}/courses/${courseId}/review/${reviewId}`
        }),
        addNewReview: builder.mutation({
            query: ({universityId, courseId, newReview}) => ({
                url: `university/${universityId}/courses/${courseId}/review`,
                method: 'POST',
                body: newReview
            }),
            invalidatesTags: ['Review']
        })
    })
})

export const {
    useGetCoursesQuery,
    useGetCourseQuery,
    useGetCourseReviewsQuery,
    useGetDepartmentsQuery,
    useGetLevelsQuery,
    useAddNewReviewMutation,
    useGetReviewQuery,
} = apiSlice