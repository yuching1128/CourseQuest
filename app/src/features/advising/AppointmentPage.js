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
                <div className="appointment-excerpt" style={{marginTop: '3em', marginBottom: '3em'}}>
                    <p className="advising-time">
                        Appointment Time: {<FormattedDate date={appointment.advisingTimeslot.time} />}
                    </p>
                    <p className="appointment-details">
                        Advisor: {appointment.advisor.firstName} {appointment.advisor.lastName}
                        <span><br/></span>
                        Advisee: {appointment.advisee.firstName} {appointment.advisee.lastName}
                        <span><br/></span>
                        Subject: {appointment.course.name}
                    </p>
                    <div>
                        { appointment.appointmentStatus === "UPCOMING" && <button className="btn view-btn" onClick={() => setJoinAppointment(!joinAppointment)}>Join Meeting</button> }
                        { appointment.appointmentStatus !== "CANCELLED" && <button type="button" className="btn cancel-btn" onClick={onDeleteAppointmentClicked}>Cancel Appointment</button> }
                        { joinAppointment && <MeetingLink creationTime={appointment.advisingTimeslot.time} /> }
                    </div>
                </div>
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