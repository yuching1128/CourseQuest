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
            <div className="timeslot-excerpt">
                <div className="timeslot-info">
                    <p className="timeslot-date">{<FormattedDate date={timeslot.time}/>}</p>
                    <p className="timeslot-status">{timeslot.advisingTimeslotStatus}</p>
                </div>
                <div className="timeslot-actions">
                    {timeslot.advisingTimeslotStatus === "FREE" && <button type="button" className="timeslot-delete-btn"
                                                                           onClick={onDeleteTimeslotClicked}>Delete</button>}
                </div>
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
            <div className="appointment-excerpt">
                <p className="advising-time">
                    Appointment Time: <FormattedDate date={appointment.advisingTimeslot.time}/>
                </p>
                <p className="appointment-details">
                    Advisee's Name: {appointment.advisee.firstName} {appointment.advisee.lastName}
                    <span><br/></span>
                    Subject: {appointment.course.name}
                    <span><br/></span>
                    Status: {appointment.appointmentStatus}
                </p>
                <div className="button-group">
                    <Link to={`/advising/appointment/${appointment.id}`} className="btn view-btn">View</Link>
                    <button type="button" className="btn cancel-btn" onClick={onCancelAppointmentClicked}>
                        Cancel
                    </button>
                </div>
            </div>
        )
    }


    let content

    if (isLoading) {
        content = <Spinner text="Loading..." />
    } else if (isSuccess) {
        content = (
            <Accordion defaultActiveKey={['0']} alwaysOpen>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Be A Mentor</Accordion.Header>
                    <Accordion.Body className="reviewsComponent">
                        <Stack direction="horizontal" gap={2}>
                            <div className="ms-auto">
                                <p className="timeslot-title">Your Timeslots</p>
                                {selectedTimeslots.length===0 && <p className="noTimeslot">You have not set any advising timeslots!</p>}
                                {selectedTimeslots && selectedTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot.id} timeslot={timeslot} />)}
                            </div>
                            <AddTimeslotForm selectedTimeslots={selectedTimes} coursesTaken={coursesTaken} />
                        </Stack>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header style={{marginBottom: '50px'}}>Your Appointments</Accordion.Header>
                    <Accordion.Body>
                        <div style={{marginTop: '-2em', marginBottom: '4em'}}>
                            {appointmentsByAdvisor.length===0 && <p className="noAppointment">You have no upcoming appointments!</p>}
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