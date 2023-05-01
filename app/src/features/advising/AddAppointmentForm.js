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
            <div className="timeslot-excerpt" style={{padding: '1em 1em 0em 0.2em'}}>
                <div className="timeslot-info">
                    <p className="timeslot-date">{<FormattedDate date={timeslotDateTime}/>}</p>
                </div>
                <div className="timeslot-actions">
                    <button type="button" className="timeslot-delete-btn" style={{marginBottom: '7px'}} onClick={onAddAppointmentClicked}>
                        Book
                    </button>
                </div>
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
            <div className="mentoring-container">
                <p className="mentoring-title">Select Course to get mentoring on:</p>
                <div className="mentoring-form">
                    <Form.Select className="mentoring-select" onChange={onInterestedCourseChange}>
                        <option disabled selected value> -- select an option --</option>
                        {interestedCoursesOptions}
                    </Form.Select>
                </div>
                {freeTimeslots.length === 0 && interestedCourseId !== -1 && (
                    <p className="mentoring-message">
                        Currently there are no advising timeslots available for this course. Check back later.
                    </p>
                )}
                <div className="mentoring-timeslots">
                    {freeTimeslots.map((timeslot) => (
                        <TimeslotExcerpt key={timeslot[0]} timeslot={timeslot}/>
                    ))}
                </div>
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