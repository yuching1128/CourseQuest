// API Documentation http://localhost:8080/swagger-ui.html

import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }),
    tagTypes: ['Review', 'Timeslots'],
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
        getUserReviews: builder.query({
            query: userId => `/user/${userId}/reviews`,
            providesTags: ['Review']
        }),
        addNewReview: builder.mutation({
            query: ({universityId, courseId, newReview}) => ({
                url: `university/${universityId}/courses/${courseId}/review`,
                method: 'POST',
                body: newReview
            }),
            invalidatesTags: ['Review']
        }),
        editReview: builder.mutation({
            query: ({universityId, courseId, reviewId, editedReview}) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'PUT',
                body: editedReview
            }),
            invalidatesTags: ['Review']
        }),
        deleteReview: builder.mutation({
            query: ({universityId, courseId, reviewId}) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'DELETE'
            }),
            invalidatesTags: ['Review']
        }),
        getAdvisorTimeslots: builder.query({
            query: userId => `/user/${userId}/advising`,
            providesTags: ['Timeslots']
        }),
        addNewTimeslot: builder.mutation({
            query: ({newTimeslot}) => ({
                url: `advising/add`,
                method: 'POST',
                body: newTimeslot
            }),
            invalidatesTags: ['Timeslots']
        }),
    })
})

export const {
    useGetCoursesQuery,
    useGetCourseQuery,
    useGetCourseReviewsQuery,
    useGetDepartmentsQuery,
    useGetLevelsQuery,
    useGetUserReviewsQuery,
    useAddNewReviewMutation,
    useEditReviewMutation,
    useDeleteReviewMutation,
    useGetAdvisorTimeslotsQuery,
    useAddNewTimeslotMutation
} = apiSlice