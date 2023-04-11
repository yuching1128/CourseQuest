import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    tagTypes: ['Review', 'Timeslots', 'Appointments'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8080/api/', credentials: 'include', prepareHeaders: (headers) => {
            const token = sessionStorage.getItem("access_token")
            if (token) {
                headers.set('authorization', `Bearer ${token}`)
            }
            return headers
        }
    }),
    endpoints: builder => ({
        getCourses: builder.query({
            query: ({ universityId, page, size }) => {
                return `university/${universityId}/courses?pageNum=${page}&pageSize=${size}`
            }
        }),
        getCourse: builder.query({
            query: ({ universityId, courseId }) => `university/${universityId}/courses/${courseId}`
        }),
        getCourseReviews: builder.query({
            query: ({ universityId, courseId }) => `university/${universityId}/courses/${courseId}/review`,
            providesTags: ['Review']
        }),
        getDepartments: builder.query({
            query: universityId => `university/${universityId}/departments`
        }),
        getLevels: builder.query({
            query: universityId => `university/${universityId}/levels`
        }),
        getUserReviews: builder.query({
            query: () => `/user/reviews`,
            providesTags: ['Review']
        }),
        addNewReview: builder.mutation({
            query: ({ universityId, courseId, newReview }) => ({
                url: `university/${universityId}/courses/${courseId}/review`,
                method: 'POST',
                body: newReview
            }),
            invalidatesTags: ['Review']
        }),
        editReview: builder.mutation({
            query: ({ universityId, courseId, reviewId, editedReview }) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'PUT',
                body: editedReview
            }),
            invalidatesTags: ['Review']
        }),
        deleteReview: builder.mutation({
            query: ({ universityId, courseId, reviewId }) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'DELETE'
            }),
            invalidatesTags: ['Review']
        }),
        getAdvisorTimeslots: builder.query({
            query: () => `/advisor/all`,
            providesTags: ['Timeslots']
        }),
        getFreeAdvisorTimeslots: builder.query({
            query: () => `/advising/free`,
            providesTags: ['Timeslots']
        }),
        addNewTimeslot: builder.mutation({
            query: ({ newTimeslot }) => ({
                url: `advising/timeslot/add`,
                method: 'POST',
                body: newTimeslot
            }),
            invalidatesTags: ['Timeslots']
        }),
        deleteTimeslot: builder.mutation({
            query: ({ timeslotId }) => ({
                url: `advising/${timeslotId}/delete`,
                method: 'DELETE'
            }),
            invalidatesTags: ['Timeslots']
        }),
        getUserInfo: builder.query({
            query: () => `user/profile`
        }),
        getUniversity: builder.query({
            query: () => `university/types`
        }),
        getDegree: builder.query({
            query: universityId => `university/${universityId}/degreeTypes`
        }),
        getMajor: builder.query({
            query: () => `major/types`
        }),
        addUserUniversity: builder.mutation({
            query: ({ universityId }) => ({
                url: `user/university`,
                method: 'POST',
                params: { universityId: universityId }
            }),
        }),
        addUserDegree: builder.mutation({
            query: ({ degreeId }) => ({
                url: `user/degree`,
                method: 'POST',
                params: { degreeId: degreeId }
            }),
        }),
        addUserMajor: builder.mutation({
            query: ({ majorId }) => ({
                url: `user/major`,
                method: 'POST',
                params: { majorId: majorId }
            }),
        }),
        addUserCourseTaken: builder.mutation({
            query: ({ courseList }) => ({
                url: `user/course`,
                method: 'POST',
                body: courseList
            }),
        }),
        addUserCourseInterested: builder.mutation({
            query: ({ courseList }) => ({
                url: `user/interested`,
                method: 'POST',
                body: courseList
            }),
        }),
        addUserConcentration: builder.mutation({
            query: ({ concentration }) => ({
                url: `user/concentration`,
                method: 'POST',
                params: { concentration: concentration }
            }),
        }),
        addUserMentorCourse: builder.mutation({
            query: ({ courseList }) => ({
                url: `user/mentorCourse`,
                method: 'POST',
                body: courseList
            }),
        }),
        addNewAppointment: builder.mutation({
            query: ({ newAppointment }) => ({
                url: `advising/book`,
                method: 'POST',
                body: newAppointment
            }),
            invalidatesTags: ['Timeslots']
        }),
        getAdvisorAppointments: builder.query({
            query: () => `/advising/advisor/appointments`,
            providesTags: ['Timeslots']
        }),
        getAdviseeAppointments: builder.query({
            query: () => `/advising/advisee/appointments`,
            providesTags: ['Timeslots']
        })
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
    useGetFreeAdvisorTimeslotsQuery,
    useAddNewTimeslotMutation,
    useDeleteTimeslotMutation,
    useAddNewAppointmentMutation,
    useGetAdvisorAppointmentsQuery,
    useGetAdviseeAppointmentsQuery,
    useGetUserInfoQuery,
    useGetUniversityQuery,
    useGetDegreeQuery,
    useGetMajorQuery,
    useAddUserUniversityMutation,
    useAddUserDegreeMutation,
    useAddUserMajorMutation,
    useAddUserCourseTakenMutation,
    useAddUserCourseInterestedMutation,
    useAddUserConcentrationMutation,
    useAddUserMentorCourseMutation
} = apiSlice