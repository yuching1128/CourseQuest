// Request Endpoint: /api/university/{universityid}/courses/{courseid}
// Request method: GET
// Response code: 200

import { createSlice } from '@reduxjs/toolkit'

const initialState = [
    {
        id: 1,
        crn_number: 4704,
        name: 'CS4704: Software Engineering Capstone',
        rating: 4.5,
        instructor: 'Dr. Bob',
        dept_name_id: 1,
        credits: 3,
        description: 'Senior project course integrating software engineering knowledge and skills acquired in previous courses. Team-based approach to problem formulation, requirements engineering, architecture, design, implementation, integration, documentation and delivery of a software system that solves a real-world problem.',
        date_created: 'time',
        level: 400,
        university_id: 1,
        reviews: ['Good', 'Interesting!', 'I love it.']
    },
    {
        id: 2,
        crn_number: 1114,
        name: 'CS1114: Introduction to Software Design',
        rating: 3,
        instructor: 'Dr. Joe',
        dept_name_id: 1,
        credits: 3,
        description: 'Fundamental concepts of programming from an object-oriented perspective. Basic software engineering principles and programming skills in a programming language that supports the object-oriented paradigm. Simple data types, control structures, array and string data structures, basic algorithms, testing and debugging. A basic model of the computer as an abstract machine. Modeling and problem-solving skills applicable to programming at this level.',
        date_created: 'time',
        level: 400,
        university_id: 1
    }
]


const coursesSlice = createSlice({
    name: 'courses',
    initialState,
    reducers: {}
})

export default coursesSlice.reducer