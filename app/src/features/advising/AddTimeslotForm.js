import {useNavigate} from "react-router-dom";
import {useAddNewTimeslotMutation} from "../api/apiSlice";
import React, {useState} from "react";
import DayTimePicker from "@mooncake-dev/react-day-time-picker";


export const AddTimeslotForm = ({selectedTimeslots}) => {

    const navigate = useNavigate()

    const [addTimeslot, { isLoading }] = useAddNewTimeslotMutation()
    const [isScheduling, setIsScheduling] = useState(false);
    const [isScheduled, setIsScheduled] = useState(false);
    const [scheduleErr, setScheduleErr] = useState('');

    // validates all timeslots in the calendar and ensure the timeslot is free
    function timeSlotValidator(slotTime) {
        let isValid = true;
        for (let key = 0; key < selectedTimeslots.length; ++key) {
            if (slotTime.valueOf() === new Date(selectedTimeslots[key]).valueOf()) {
                isValid = false
                break;
            }
        }
        return isValid;
    }

    const onAddTimeslotClicked = async dateTime => {
        setIsScheduling(true);
        setScheduleErr('');

        try {
            setScheduleErr('');
            setIsScheduled(true);
            console.log('Scheduling timeslot for: ', dateTime);
            await addTimeslot({newTimeslot: dateTime}).unwrap()

        } catch (err) {
            setScheduleErr(err);
            console.error('Failed to add timeslot: ', err)
        } finally {
            setIsScheduling(false);
            console.log('Finished request!')
            navigate(0)
        }
    };

    return (
        <DayTimePicker timeSlotSizeMinutes={60}
                       isLoading={isScheduling}
                       isDone={isScheduled}
                       err={scheduleErr}
                       timeSlotValidator={timeSlotValidator}
                       onConfirm={onAddTimeslotClicked} />
    )
}