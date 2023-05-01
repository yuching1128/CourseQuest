import { JitsiMeeting } from '@jitsi/react-sdk';
import React from "react";

export const MeetingLink = ({creationTime}) => {

    // generate a unique meeting id based on the creation time of the appointment
    const uniqueMeetingId = creationTime.toString().replaceAll(':','')

    return (
        <div style={{ marginTop: '50px' }}>
            <JitsiMeeting roomName={uniqueMeetingId} getIFrameRef={node => node.style.height = '600px'} />
        </div>
    )
}