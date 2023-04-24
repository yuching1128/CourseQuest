import Container from "react-bootstrap/Container";
import React from "react";
import Accordion from "react-bootstrap/Accordion";
import {useCancelAppointmentMutation, useGetAdviseeAppointmentsQuery} from "../api/apiSlice";
import {Button, Stack} from "react-bootstrap";
import {AddAppointmentForm} from "./AddAppointmentForm";
import {FormattedDate} from "./FormattedDate";
import {Link} from "react-router-dom";

export const MenteePage = () => {

    // State
    const [cancelAppointment, { isLoadingCancelAppointment }] = useCancelAppointmentMutation()

    // get list of appointments by an advisee
    const {
        data: appointmentsByAdvisee = [],
        isLoading: isLoadingAdvisee,
        isFetching: isFetchingAdvisee,
        isSuccess: isSuccessAdvisee,
        isError: isErrorAdvisee,
        error: errorAdvisee
    } = useGetAdviseeAppointmentsQuery();

    let AppointmentExcerpt = ({ appointment }) => {
        const onCancelAppointmentClicked = async () => {
            try {
                await cancelAppointment({appointmentId:appointment.id})
            } catch (err) {
                console.error('Failed to delete the appointment: ', err)
            }
        }

        return (
            <div className="appointmentExcerpt">
                <Stack direction="horizontal" gap={2}>
                    <p>Advisor's Name: {appointment.advisor.firstName} {appointment.advisor.lastName}</p>
                    <p>Appointment Time: {<FormattedDate date={appointment.advisingTimeslot.time} />}</p>
                    <p>Subject: {appointment.course.name}</p>
                    <p>Status: {appointment.appointmentStatus}</p>
                    <Link to={`/advising/appointment/${appointment.id}`} >View</Link>
                    { appointment.appointmentStatus !== "CANCELLED" && <Button type="button" onClick={onCancelAppointmentClicked}>Cancel</Button> }
                </Stack>
            </div>
        )
    }

    return (
        <Container className="menteePage">
            <Accordion alwaysOpen>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Book An Appointment</Accordion.Header>
                    <Accordion.Body className="reviewsComponent">
                        <AddAppointmentForm />
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Your Appointments</Accordion.Header>
                    <Accordion.Body>
                        <div>
                            {appointmentsByAdvisee.length===0 && <p>You have no upcoming appointments!</p>}
                            {appointmentsByAdvisee.map(appointment => <AppointmentExcerpt key={appointment.id} appointment={appointment} />)}
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </Container>
    )
}