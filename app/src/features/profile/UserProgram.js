import React, { useEffect, useState } from 'react';
import {
    useAddUserDegreeMutation, useAddUserMajorMutation,
    useGetDegreeQuery, useGetMajorQuery
} from "../api/apiSlice";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare, faSquareCheck } from "@fortawesome/free-regular-svg-icons";
import Select from "react-select";

export const UserProgram = ({ userProfileData }) => {

    // set degree select options
    const [degree, setDegree] = useState([]);
    const [selectedDegree, setSelectedDegree] = useState(null);
    const [addUserDegree, { isLoading: degreeIsLoading }] = useAddUserDegreeMutation();
    const [defaultDegree, setDefaultDegree] = useState(null);

    const {
        data: degreeData,
        isSuccess: degreeSuccess,
    } = useGetDegreeQuery(1);

    useEffect(() => {
        if (userProfileData && userProfileData.degree) {
            const defaultOption = {
                value: userProfileData.degree.name,
                label: userProfileData.degree.name,
            };
            setDefaultDegree(defaultOption);
            setSelectedDegree(userProfileData.degree.id);
        }
    }, [userProfileData]);

    useEffect(() => {
        if (degreeSuccess) {
            const degreeOptions = degreeData.map((degree) => ({
                value: degree.name,
                label: degree.name,
            }));
            setDegree([, ...degreeOptions,
            ]);
        }
    }, [degreeSuccess, degreeData]);

    const handleDegreeChange = (selectedOption) => {
        const selectedDegreeObj = degreeData.find(
            (degree) => degree.name === selectedOption.value
        );
        // Set the selected university ID as the state
        setSelectedDegree(selectedDegreeObj?.id || null);
        setDefaultDegree(selectedOption);
    };

    // set major select option
    const [major, setMajor] = useState(null);
    const [selectedMajor, setSelectedMajor] = useState(null);
    const [addUserMajor, { isLoading: majorIsLoading }] = useAddUserMajorMutation();
    const [defaultMajor, setDefaultMajor] = useState(null);

    const {
        data: majorData,
        isSuccess: majorSuccess,
    } = useGetMajorQuery();

    useEffect(() => {
        if (userProfileData && userProfileData.department) {
            const defaultOption = {
                value: userProfileData.department.name,
                label: userProfileData.department.name,
            };
            setDefaultMajor(defaultOption);
            setSelectedMajor(userProfileData.department.id);
        }
    }, [userProfileData]);


    useEffect(() => {
        if (majorSuccess) {
            const majorOptions = majorData.map((major) => ({
                value: major.name,
                label: major.name,
            }));
            setMajor([, ...majorOptions,
            ]);
        }
    }, [majorSuccess, majorData]);

    const handleMajorChange = (selectedOption) => {
        const selectedMajorObj = majorData.find(
            (major) => major.name === selectedOption.value
        );
        // Set the selected university ID as the state
        setSelectedMajor(selectedMajorObj?.id || null);
        setDefaultMajor(selectedOption);
    };

    const [courseIsEditing, setCourseIsEditing] = useState(false);

    const handleCourseEditClick = () => {
        setCourseIsEditing(true);
    };

    const handleCourseDoneClick = async () => {
        await addUserDegree({ degreeId: selectedDegree })
        await addUserMajor({ majorId: selectedMajor })
        setCourseIsEditing(false);
    };

    return (
        <div className="profileBlock" style={{display: 'block'}}>
            <div className="courseBlockEdit">
                {courseIsEditing ? (
                    <button onClick={handleCourseDoneClick} className="profileEditDone"><FontAwesomeIcon icon={faSquareCheck} /> Done</button>
                ) : (
                    <button onClick={handleCourseEditClick} className="profileEditDone"><FontAwesomeIcon icon={faPenToSquare} /> Edit</button>
                )}
            </div>

            <div>
                <p className="profileText">Degree: </p>
                <Select className="profileDegree"
                    options={degree}
                    placeholder="Select the degree"
                    isDisabled={!courseIsEditing}
                    onChange={handleDegreeChange}
                    value={defaultDegree}
                />
            </div>

            <div>
                <p className="profileText">Major: </p>
                <Select className="profileMajor"
                    options={major}
                    placeholder="Select the major"
                    isDisabled={!courseIsEditing}
                    onChange={handleMajorChange}
                    value={defaultMajor}
                />
            </div>
        </div>
    );
}