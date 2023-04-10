import React, {useMemo} from 'react';
import Container from "react-bootstrap/Container";
import Accordion from "react-bootstrap/Accordion";
import {useSelector} from "react-redux";
import {useDeleteTimeslotMutation, useGetAdvisorAppointmentsQuery, useGetAdvisorTimeslotsQuery} from "../api/apiSlice";
import {AddTimeslotForm} from "./AddTimeslotForm";
import {Button, Spinner, Stack} from "react-bootstrap";
import {parseISO} from "date-fns";
import {useNavigate} from "react-router-dom";

export const MentorPage = () => {

    const user = useSelector(state => state.user)
    const [deleteTimeslot, { isLoadingDeleteTimeslot }] = useDeleteTimeslotMutation()
    const navigate = useNavigate()

    // get list of appointments by an advisor
    const {
        data: appointmentsByAdvisor = [],
        isLoading: isLoadingAdvisor,
        isFetching: isFetchingAdvisor,
        isSuccess: isSuccessAdvisor,
        isError: isErrorAdvisor,
        error: errorAdvisor
    } = useGetAdvisorAppointmentsQuery(user.id);

    // get list of already-selected timeslots from advisor
    const selectedTimes = []
    const {
        data: selectedTimeslots = [],
        isLoading: isLoading,
        isFetching: isFetching,
        isSuccess: isSuccess,
        isError: isError,
        error: error
    } = useGetAdvisorTimeslotsQuery(user.id);

    for (let key = 0; key < selectedTimeslots.length; ++key) {
        selectedTimes.push(selectedTimeslots[key].time)
    }

    // TODO fix - Sort in descending chronological order
    const sortedSelectedTime = useMemo(() => {
        const sortedSelectedTime = selectedTimes.slice()
        selectedTimes.sort((a, b) => b.localeCompare(a))
        return sortedSelectedTime
    }, [selectedTimes])

    let TimeslotExcerpt = ({ timeslot }) => {

        const onDeleteTimeslotClicked = async () => {
            try {
                await deleteTimeslot({timeslotId: timeslot.id})
                navigate(0)
            } catch (err) {
                console.error('Failed to delete the timeslot: ', err)
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
                    <p>{timeslot.advisingTimeslotStatus}</p>
                    {timeslot.advisingTimeslotStatus==="FREE" && <Button type="button" onClick={onDeleteTimeslotClicked}>Delete</Button>}
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
                    <p>Advisee's Name: {appointment.advisee.firstName} {appointment.advisee.lastName}</p>
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
                    <Accordion.Header>Be A Mentor</Accordion.Header>
                    <Accordion.Body className="reviewsComponent">
                        <Stack direction="horizontal" gap={2}>
                            <div className="ms-auto">
                                <h5>Your Timeslots</h5>
                                { !selectedTimeslots && <p>You have not set any advising timeslots!</p>}
                                { selectedTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot.id} timeslot={timeslot} />) }
                            </div>
                            <AddTimeslotForm selectedTimeslots={selectedTimes} />
                        </Stack>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Upcoming Appointments</Accordion.Header>
                    <Accordion.Body>
                        <div>
                            { appointmentsByAdvisor.map(appointment => <AppointmentExcerpt key={appointment.id} appointment={appointment} />) }
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