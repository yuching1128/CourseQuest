import Container from "react-bootstrap/Container";
import React, {useState} from "react";
import {useParams} from "react-router-dom";
import {useCancelAppointmentMutation, useDeleteTimeslotMutation, useGetAppointmentQuery} from "../api/apiSlice";
import {Button, Spinner} from "react-bootstrap";
import {FormattedDate} from "./FormattedDate";
import {MeetingLink} from "./MeetingLink";

export const AppointmentPage = () => {

    // state
    const { appointmentId } = useParams()
    const [joinAppointment, setJoinAppointment] = useState(false);
    const [deleteTimeslot, { isLoadingDeleteTimeslot }] = useDeleteTimeslotMutation()
    const [cancelAppointment, { isLoadingCancelAppointment }] = useCancelAppointmentMutation()

    // API to get appointment
    const {
        data: appointment,
        isLoading,
        isSuccess,
        isError,
        error
    } = useGetAppointmentQuery(appointmentId)

    const onDeleteAppointmentClicked = async () => {
        try {
            await cancelAppointment({appointmentId:appointment.id})
        } catch (err) {
            console.error('Failed to delete the appointment: ', err)
        }
    }

    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {

        content = (
            <Container>
                <p>Advisor: {appointment.advisor.firstName} {appointment.advisor.lastName}</p>
                <p>Advisee: {appointment.advisee.firstName} {appointment.advisee.lastName}</p>
                <p>Appointment Time: {<FormattedDate date={appointment.advisingTimeslot.time} />}</p>
                <p>Subject: {appointment.course.name}</p>
                { appointment.appointmentStatus === "UPCOMING" && <Button onClick={() => setJoinAppointment(!joinAppointment)}>Join Meeting</Button> }
                { appointment.appointmentStatus !== "CANCELLED" && <Button type="button" onClick={onDeleteAppointmentClicked}>Cancel Appointment</Button> }
                { joinAppointment && <MeetingLink creationTime={appointment.advisingTimeslot.time} /> }

            </Container>
        )
    } else if (isError) {
        content = <div>{error.toString()}</div>
    }

    return (
        <Container className="appointment-page">
            {content}
        </Container>
    )
}