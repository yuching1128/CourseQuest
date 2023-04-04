import React, {useMemo} from 'react';
import Container from "react-bootstrap/Container";
import Accordion from "react-bootstrap/Accordion";
import {useSelector} from "react-redux";
import {useGetAdvisorTimeslotsQuery} from "../api/apiSlice";
import {AddTimeslotForm} from "./AddTimeslotForm";
import {Spinner, Stack} from "react-bootstrap";
import {parseISO} from "date-fns";


export const MentorPage = () => {

    const user = useSelector(state => state.user)

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
                    <p>{timeslot.subject}</p>
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
                                <h5>Your Available Times</h5>
                                { !selectedTimeslots && <p>You have set any advising timeslots!</p>}
                                { selectedTimeslots.map(timeslot => <TimeslotExcerpt key={timeslot.id} timeslot={timeslot} />) }
                            </div>
                            <AddTimeslotForm selectedTimeslots={selectedTimes} />
                        </Stack>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Upcoming Appointments</Accordion.Header>
                    <Accordion.Body>
                        TEXT
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