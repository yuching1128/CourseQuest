import React from 'react'
import {parseISO} from "date-fns";

export const FormattedDate = ({ date }) => {
    const myDate = parseISO(date)
    return myDate.toLocaleString("en-GB", {
        day: "numeric",
        month: "short",
        year: "numeric",
        hour: "numeric",
        minute: "2-digit"
    });
}