import React from 'react';
import Container from "react-bootstrap/Container";
import Accordion from "react-bootstrap/Accordion";
import {
    useCancelAppointmentMutation,
    useDeleteTimeslotMutation,
    useGetAdvisorAppointmentsQuery,
    useGetAdvisorTimeslotsQuery,
    useGetUserInfoQuery
} from "../api/apiSlice";
import { AddTimeslotForm } from "./AddTimeslotForm";
import {Button, Spinner, Stack} from "react-bootstrap";
import {Link} from "react-router-dom";
import {FormattedDate} from "./FormattedDate";


export const MentorPage = () => {

    // State
    const [deleteTimeslot, { isLoadingDeleteTimeslot }] = useDeleteTimeslotMutation()
    const [cancelAppointment, { isLoadingCancelAppointment }] = useCancelAppointmentMutation()

    // generate selection list of courses advisor has taken
    const {
        data: userInfo = [],
        isLoading: isLoadingUser,
        isFetching: isFetchingUser,
        isSuccess: isSuccessUser,
        isError: isErrorUser,
        error: errorUser
    } = useGetUserInfoQuery();
    const coursesTaken = userInfo.course

    // get list of appointments by an advisor
    const {
        data: appointmentsByAdvisor = [],
        isLoading: isLoadingAdvisor,
        isFetching: isFetchingAdvisor,
        isSuccess: isSuccessAdvisor,
        isError: isErrorAdvisor,
        error: errorAdvisor
    } = useGetAdvisorAppointmentsQuery();

    // get list of already-selected timeslots from advisor
    const selectedTimes = []
    const {
        data: selectedTimeslots = [],
        isLoading: isLoading,
        isFetching: isFetching,
        isSuccess: isSuccess,
        isError: isError,
        error: error
    } = useGetAdvisorTimeslotsQuery();

    for (let key = 0; key < selectedTimeslots.length; ++key) {
        selectedTimes.push(selectedTimeslots[key].time)
    }

    let TimeslotExcerpt = ({ timeslot }) => {
        const onDeleteTimeslotClicked = async () => {
            try {
                await deleteTimeslot({ timeslotId: timeslot.id })
            } catch (err) {
                console.error('Failed to delete the timeslot: ', err)
            }
        }

        return (
            <div className="timeslotExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>{<FormattedDate date={timeslot.time} />}</p>
                    <p>{timeslot.advisingTimeslotStatus}</p>
                    {timeslot.advisingTimeslotStatus === "FREE" && <Button type="button" onClick={onDeleteTimeslotClicked}>Delete</Button>}
                </Stack>
            </div>
        )
    }

    let AppointmentExcerpt = ({ appointment }) => {

        const onCancelAppointmentClicked = async () => {
            try {
                await cancelAppointment({appointmentId:appointment.id})
            } catch (err) {
                console.error('Failed to cancel the appointment: ', err)
            }
        }

        return (
            <div className="appointmentExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>Advisee's Name: {appointment.advisee.firstName} {appointment.advisee.lastName}</p>
                    <p>Appointment Time: {<FormattedDate date={appointment.advisingTimeslot.time} />}</p>
                    <p>Subject: {appointment.course.name}</p>
                    <p>Status: {appointment.appointmentStatus}</p>
                    <Link to={`/advising/appointment/${appointment.id}`} >View</Link>
                    <Button type="button" onClick={onCancelAppointmentClicked}>Cancel</Button>
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
                    <Accordion.Header>Be A Mentor</Accordion.Header>
                    <Accordion.Body className="reviewsComponent">
                        <Stack direction="horizontal" gap={2}>
                            <div className="ms-auto">
                                <h5>Your Timeslots</h5>
                                {selectedTimeslots.length===0 && <p>You have not set any advising timeslots!</p>}
                                {selectedTimeslots && selectedTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot.id} timeslot={timeslot} />)}
                            </div>
                            <AddTimeslotForm selectedTimeslots={selectedTimes} coursesTaken={coursesTaken} />
                        </Stack>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Your Appointments</Accordion.Header>
                    <Accordion.Body>
                        <div>
                            {appointmentsByAdvisor.length===0 && <p>You have no upcoming appointments!</p>}
                            {appointmentsByAdvisor.map(appointment => <AppointmentExcerpt key={appointment.id} appointment={appointment} />)}
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        )
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="mentorPage">
            {content}
        </Container>
    )
}