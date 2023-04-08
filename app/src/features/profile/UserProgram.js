import React, {useEffect, useState} from 'react';
import {
    useAddUserDegreeMutation, useAddUserMajorMutation,
    useAddUserUniversityMutation,
    useGetDegreeQuery, useGetMajorQuery,
    useGetUniversityQuery
} from "../api/apiSlice";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPenToSquare, faSquareCheck} from "@fortawesome/free-regular-svg-icons";
import Select from "react-select";

export const UserProgram = ({userId, userProfileData}) => {

    // set university select option
    const [university, setUniversity] = useState(null);
    const [selectedUniversity, setSelectedUniversity] = useState(null);
    const [addUserUniversity, { isLoading: universityIsLoading}] = useAddUserUniversityMutation();
    const [defaultUniversity, setDefaultUniversity] = useState(null);

    const {
        data: universityData,
        isSuccess: universitySuccess,
    } = useGetUniversityQuery();

    useEffect(() => {
        if (userProfileData && userProfileData.university) {
            const defaultOption = {
                value: userProfileData.university.name,
                label: userProfileData.university.name,
            };
            setDefaultUniversity(defaultOption);
            setSelectedUniversity(userProfileData.university.id);
        }
    }, [userProfileData]);


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
        const selectedUniversityObj = universityData.find(
            (university) => university.name === selectedOption.value
        );
        // Set the selected university ID as the state
        setSelectedUniversity(selectedUniversityObj?.id || null);
        setDefaultUniversity(selectedOption);
    };

    // set degree select options
    const [degree, setDegree] = useState([]);
    const [selectedDegree, setSelectedDegree]= useState(null);
    const [addUserDegree, { isLoading: degreeIsLoading}] = useAddUserDegreeMutation();
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
            setDegree([ ,...degreeOptions,
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
    const [addUserMajor, { isLoading: majorIsLoading}] = useAddUserMajorMutation();
    const [defaultMajor, setDefaultMajor] = useState(null);

    const {
        data: majorData,
        isSuccess: majorSuccess,
    } = useGetMajorQuery();

    useEffect(() => {
        if (userProfileData && userProfileData.major) {
            const defaultOption = {
                value: userProfileData.major.name,
                label: userProfileData.major.name,
            };
            setDefaultMajor(defaultOption);
            setSelectedMajor(userProfileData.major.id);
        }
    }, [userProfileData]);


    useEffect(() => {
        if (majorSuccess) {
            const majorOptions = majorData.map((major) => ({
                value: major.name,
                label: major.name,
            }));
            setMajor([ ,...majorOptions,
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
        await addUserUniversity({userId: userId, universityId: selectedUniversity})
        await addUserDegree({userId: userId, degreeId: selectedDegree})
        await addUserMajor({userId: userId, majorId: selectedMajor})
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
                        value={defaultUniversity}
                />
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