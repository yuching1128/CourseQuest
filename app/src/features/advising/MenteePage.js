import Container from "react-bootstrap/Container";
import React from "react";
import Accordion from "react-bootstrap/Accordion";
import {
    useAddNewAppointmentMutation,
    useGetAdviseeAppointmentsQuery,
    useGetFreeAdvisorTimeslotsQuery
} from "../api/apiSlice";
import { Button, Spinner, Stack } from "react-bootstrap";
import { parseISO } from "date-fns";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

export const MenteePage = () => {

    const user = useSelector(state => state.user)
    const [addAppointment, { isLoadingBookAppointment }] = useAddNewAppointmentMutation()
    const navigate = useNavigate()

    // get list of appointments by an advisee
    const {
        data: appointmentsByAdvisee = [],
        isLoading: isLoadingAdvisee,
        isFetching: isFetchingAdvisee,
        isSuccess: isSuccessAdvisee,
        isError: isErrorAdvisee,
        error: errorAdvisee
    } = useGetAdviseeAppointmentsQuery();

    // get all available appointment timeslots
    const {
        data: allAdvisorTimeslots = [],
        isLoading: isLoading,
        isFetching: isFetching,
        isSuccess: isSuccess,
        isError: isError,
        error: error
    } = useGetFreeAdvisorTimeslotsQuery();

    console.log(allAdvisorTimeslots)

    let TimeslotExcerpt = ({ timeslot }) => {

        const onAddAppointmentClicked = async () => {
            try {
                const appointmentDetails = {
                    "advisee": {
                        "id": user.id
                    },
                    "advisingTimeslot": {
                        "id": timeslot.id
                    },
                    "advisor": {
                        "id": timeslot.advisor.id
                    },
                    "appointmentStatus": "UPCOMING",
                    "course": {
                        "id": 1
                    }
                }

                await addAppointment({ newAppointment: appointmentDetails }).unwrap()
                navigate(0)
            } catch (err) {
                console.error('Failed to book the appointment: ', err)
            }
        }


        const myDate = parseISO(timeslot.time)
        const formattedDate = myDate.toLocaleString("en-GB", {
            day: "numeric",
            month: "short",
            year: "numeric",
            hour: "numeric",
            minute: "2-digit"
        });

        return (
            <div className="timeslotExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>{formattedDate}</p>
                    <Button type="button" onClick={onAddAppointmentClicked}>
                        Book
                    </Button>
                </Stack>
            </div>
        )
    }

    let AppointmentExcerpt = ({ appointment }) => {
        const myDate = parseISO(appointment.advisingTimeslot.time)
        const formattedDate = myDate.toLocaleString("en-GB", {
            day: "numeric",
            month: "short",
            year: "numeric",
            hour: "numeric",
            minute: "2-digit"
        });

        return (
            <div className="appointmentExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>Advisor's Name: {appointment.advisor.firstName} {appointment.advisor.lastName}</p>
                    <p>Appointment Time: {formattedDate}</p>
                    <p>Subject: {appointment.course.name}</p>
                </Stack>
            </div>
        )
    }

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = (
            <Accordion alwaysOpen>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Interested In</Accordion.Header>
                    <Accordion.Body className="reviewsComponent">
                        {allAdvisorTimeslots && <h3> Available Appointment Times: </h3>}
                        {!allAdvisorTimeslots && <h3> No Available Appointment Times At This Time. </h3>}
                        <div>
                            {allAdvisorTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot.id} timeslot={timeslot} />)}
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Upcoming Appointments</Accordion.Header>
                    <Accordion.Body>
                        <div>
                            {appointmentsByAdvisee.map(appointment => <AppointmentExcerpt key={appointment.id} appointment={appointment} />)}
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        )
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="menteePage">
            {content}
        </Container>
    )
}