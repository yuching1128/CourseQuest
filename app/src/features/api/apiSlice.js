import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const apiSlice = createApi({
    reducerPath: 'api',
    tagTypes: ['Review', 'Timeslots', 'Appointments', 'Courses', 'Profile'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8080/api/', credentials: 'include', prepareHeaders: (headers) => {
            const token = localStorage.getItem("access_token")
            if (token) {
                headers.set('authorization', `Bearer ${token}`)
            }
            return headers
        }
    }),
    endpoints: builder => ({
        // Course APIs
        getCourses: builder.query({
            query: ({ universityId, page, size }) => {
                return `university/${universityId}/courses?pageNum=${page}&pageSize=${size}`
            },
            providesTags: ['Courses']
        }),
        getCourse: builder.query({
            query: ({ universityId, courseId }) => `university/${universityId}/courses/${courseId}`,
            providesTags: ['Courses', 'Profile']
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
            invalidatesTags: ['Review', 'Courses']
        }),
        editReview: builder.mutation({
            query: ({ universityId, courseId, reviewId, editedReview }) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'PUT',
                body: editedReview
            }),
            invalidatesTags: ['Review', 'Courses']
        }),
        deleteReview: builder.mutation({
            query: ({ universityId, courseId, reviewId }) => ({
                url: `university/${universityId}/courses/${courseId}/review/${reviewId}`,
                method: 'DELETE'
            }),
            invalidatesTags: ['Review', 'Courses']
        }),

        // User Profile APIs
        getUserInfo: builder.query({
            query: () => `user/profile`,
            providesTags:['Profile']
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

        // Advising APIs
        getAdvisorTimeslots: builder.query({
            query: () => `/advising/all`,
            providesTags: ['Timeslots']
        }),
        getFreeTimeslotsForCourse: builder.query({
            query: courseId => `advising/course/${courseId}/free`,
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
                url: `advising/timeslot/${timeslotId}/delete`,
                method: 'DELETE'
            }),
            invalidatesTags: ['Timeslots']
        }),
        getAppointment: builder.query({
            query: appointmentId => `/advising/appointment/${appointmentId}`,
            providesTags: ['Appointments']
        }),
        getAdvisorAppointments: builder.query({
            query: () => `/advising/advisor/appointments`,
            providesTags: ['Timeslots', 'Appointments']
        }),
        getAdviseeAppointments: builder.query({
            query: () => `/advising/advisee/appointments`,
            providesTags: ['Timeslots', 'Appointments']
        }),
        addNewAppointment: builder.mutation({
            query: ({ newAppointment }) => ({
                url: `advising/book`,
                method: 'POST',
                body: newAppointment
            }),
            invalidatesTags: ['Timeslots', 'Appointments']
        }),
        cancelAppointment: builder.mutation({
            query: ({ appointmentId }) => ({
                url: `advising/appointment/${appointmentId}/cancel`,
                method: 'PUT'
            }),
            invalidatesTags: ['Timeslots', 'Appointments']
        }),

        // Search
        searchCourses: builder.query({
            query: ({dept, searchText, level, universityId, page, size}) => `university/courses/search?fullTextSearch=${searchText}&dept=${dept}&level=${level}&universityId=${universityId}&pageNum=${page}&pageSize=${size}`,
            providesTags: ['Courses']
        }),
        getRecommendedCourses: builder.query({
            query: () => `openai/get-recommended-courses`
        }),
        addFollowCourse: builder.mutation({
            query: ({ universityId, courseId}) => ({
                url: `university/${universityId}/courses/${courseId}/follow`,
                method: 'POST'
            }),
            invalidatesTags:['Profile']
        }),
        deleteFollowCourse: builder.mutation({
            query: ({ universityId, courseId}) => ({
                url: `university/${universityId}/courses/${courseId}/unfollow`,
                method: 'DELETE'
            }),
            invalidatesTags:['Profile']
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
    useGetFreeTimeslotsForCourseQuery,
    useAddNewTimeslotMutation,
    useDeleteTimeslotMutation,
    useAddNewAppointmentMutation,
    useCancelAppointmentMutation,
    useGetAppointmentQuery,
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
    useAddUserMentorCourseMutation,
    useSearchCoursesQuery,
    useGetRecommendedCoursesQuery,
    useAddFollowCourseMutation,
    useDeleteFollowCourseMutation
} = apiSlice