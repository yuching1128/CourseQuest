import React, {useEffect, useState} from 'react';
import {useGetLevelsQuery, useGetUniversityQuery} from "../api/apiSlice";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPenToSquare, faSquareCheck} from "@fortawesome/free-regular-svg-icons";
import Select from "react-select";

export const UserProgram = ({userId}) => {
    // set university select option

    const [university, setUniversity] = useState(null);
    const [selectedUniversity, setSelectedUniversity] = useState([]);

    const {
        data: universityData,
        isSuccess: universitySuccess,
    } = useGetUniversityQuery();

    useEffect(() => {
        if (universitySuccess) {
            const universityOptions = universityData.map((university) => ({
                value: university.name,
                label: university.name,
            }));
            setUniversity([ ,...universityOptions,
            ]);
        }
    }, [universitySuccess, universityData]);

    const handleUniversityChange = (selectedOption) => {
        setSelectedUniversity(selectedOption);
    };

    // set degree select options
    const [degree, setDegree] = useState([]);

    const {
        data: degreeData,
        isSuccess: degreeSuccess,
    } = useGetLevelsQuery(1);

    useEffect(() => {
        if (degreeSuccess) {
            const degreeOptions = degreeData.map((degree) => ({
                value: degree.name,
                label: degree.name,
            }));
            setDegree([ ,...degreeOptions,
            ]);
        }
    }, [degreeSuccess, degreeData]);

    const [courseIsEditing, setCourseIsEditing] = useState(false);

    const handleCourseEditClick = () => {
        setCourseIsEditing(true);
    };

    const handleCourseDoneClick = () => {
        setCourseIsEditing(false);
    };

    return (
        <div className="profileBlock">
            <div className="courseBlockEdit">
                {courseIsEditing ? (
                    <button onClick={handleCourseDoneClick} className="profileEditDone"><FontAwesomeIcon icon={faSquareCheck} /> Done</button>
                ) : (
                    <button onClick={handleCourseEditClick} className="profileEditDone"><FontAwesomeIcon icon={faPenToSquare} /> Edit</button>
                )}
            </div>
            <div>
                <p className="profileText">University: </p>
                <Select className="profileUniversity"
                        options={university}
                        placeholder="Select the university"
                        isDisabled={!courseIsEditing}
                        onChange={handleUniversityChange}
                />
            </div>

            <div>
                <p className="profileText">Degree: </p>
                <Select className="profileDegree"
                        options={degree}
                        placeholder="Select the degree"
                        isDisabled={!courseIsEditing}
                />
            </div>
            <p className="profileText">Major: </p>
        </div>
    );
}