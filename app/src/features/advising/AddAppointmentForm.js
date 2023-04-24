import React, {useState} from "react";
import {Button, Form, Spinner, Stack} from "react-bootstrap";
import {useAddNewAppointmentMutation, useGetFreeTimeslotsForCourseQuery, useGetUserInfoQuery} from "../api/apiSlice";
import Container from "react-bootstrap/Container";
import {useNavigate} from "react-router-dom";
import {FormattedDate} from "./FormattedDate";

export const AddAppointmentForm = () => {

    const navigate = useNavigate()

    // States
    const [addAppointment, { isLoadingBookAppointment }] = useAddNewAppointmentMutation()
    const [interestedCourseId, setInterestedCourseId] = useState(-1);
    const onInterestedCourseChange = (e) => setInterestedCourseId(e.target.value)

    // API query to get user profile info (used to get user's interested courses)
    const {
        data: userInfo = [],
        isLoading: isLoadingUser,
        isSuccess: isSuccessUser,
        isError: isErrorUser,
        error: errorUser
    } = useGetUserInfoQuery();

    // API query to get free timeslots for a selected course
    // will only fetch when the interestedCourseId state is set
    const {
        data: freeTimeslots = []
    } = useGetFreeTimeslotsForCourseQuery(interestedCourseId, {skip: interestedCourseId === -1});

    let TimeslotExcerpt = ({ timeslot }) => {
        const timeslotId = timeslot[0];
        const timeslotStatus = timeslot[1];
        const timeslotDateTime = timeslot[2];
        const timeslotAdvisorId = timeslot[3];

        const onAddAppointmentClicked = async () => {
            try {
                const newAppointment = {
                    "advisingTimeslot": {
                        "id": timeslotId
                    },
                    "advisor": {
                        "id": timeslotAdvisorId
                    },
                    "appointmentStatus": "UPCOMING",
                    "course": {
                        "id": interestedCourseId
                    }
                }

                await addAppointment({ newAppointment: newAppointment }).unwrap()
                navigate(0)
            } catch (err) {
                console.error('Failed to book the appointment: ', err)
            }
        }

        return (
            <div className="timeslotExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>{<FormattedDate date={timeslotDateTime} />}</p>
                    <Button type="button" onClick={onAddAppointmentClicked}>
                        Book
                    </Button>
                </Stack>
            </div>
        )
    }

    // load content
    let content

    if (isLoadingUser) {
        content = <Spinner text="Loading..." />
    } else if (isSuccessUser) {
        // generate selection option of interested courses by user
        const interestedCoursesOptions = userInfo.interestedCourse.map((course) => (
            <option key={course.id} value={course.id}>
                {course.name}
            </option>
        ))
        
        content = (
            <div>
                Select Course to get mentoring on:
                <Form.Select onChange={onInterestedCourseChange}>
                    <option disabled selected value> -- select an option --</option>
                    {interestedCoursesOptions}
                </Form.Select>

                {(freeTimeslots.length===0 && interestedCourseId !== -1) && <p>Currently there are no advising timeslots available for this course. Check back later.</p>}
                {freeTimeslots && freeTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot[0]} timeslot={timeslot} />)}

            </div>
        )
    } else if (isErrorUser) {
        content = <div>{errorUser.toString()}</div>
    }

    return (
        <Container className="AddAppointmentForm">
            {content}
        </Container>
    )

}