import Container from "react-bootstrap/Container";
import React, {useEffect, useState} from "react";
import Accordion from "react-bootstrap/Accordion";
import {faCircleUser} from "@fortawesome/free-regular-svg-icons";
import {faBook} from"@fortawesome/free-solid-svg-icons"
import {faHeart} from "@fortawesome/free-regular-svg-icons";
import {faUserGraduate} from"@fortawesome/free-solid-svg-icons"
import {faPenToSquare} from "@fortawesome/free-regular-svg-icons";
import {faSquareCheck} from "@fortawesome/free-regular-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useGetCoursesQuery, useGetLevelsQuery, useGetUserInfoQuery} from "../api/apiSlice";
import {useParams} from "react-router-dom";
import Select from "react-select";
import {UserInfo} from "./UserInfo";
import {UserProgram} from "./UserProgram";

export const ProfilePage = () => {

    const { userId } = useParams();

    const {
        data: userProfileData,
        isSuccess: userProfileSuccess,
    } = useGetUserInfoQuery({userId: userId});
    console.log(userProfileData)

    let universityId = 1
    console.log(userProfileData)

    // if (userProfileData.university !== null) {
    //     universityId = userProfileData.university_id;
    // }

    // set course taken and subject interested select options
    const [courseTaken, setCourseTaken] = useStatmajore([]);
    const [selectedCourses, setSelectedCourses] = useState([]);

    const {
        data: courseTakenData,
        isSuccess: courseTakenSuccess,
    } = useGetCoursesQuery({universityId: universityId, page: "", size: ""});

    useEffect(() => {
        if (courseTakenSuccess) {
            const courseTakenOptions = courseTakenData.map((courseTaken) => ({
                value: courseTaken.name,
                label: courseTaken.name,
            }));
            setCourseTaken([ ,...courseTakenOptions,
            ]);
        }
    }, [courseTakenSuccess, courseTakenData]);

    // set mentoring on select options based on course taken
    const [mentoringOn, setMentoringOn] = useState([]);
    const [selectedMentorOn, setSelectedMentorOn] = useState([]);

    const handleCourseSelection = (selectedOptions) => {
        setSelectedCourses(selectedOptions);
        const filteredSelectedMentorOn = selectedMentorOn.filter((option) =>
            selectedOptions.some((subject) => subject.value === option.value)
        );
        setSelectedMentorOn(filteredSelectedMentorOn);
        setMentoringOn(selectedOptions);
    };

    useEffect(() => {
        // Filter out any selected options that are not in the courseTaken options
        const filteredMentoringOn = mentoringOn.filter((option) =>
            courseTaken.some((subject) => subject.value === option.value)
        );
        setMentoringOn(filteredMentoringOn);
    }, [courseTaken]);

    // edit and done for each section
    const [interestIsEditing, setInterestIsEditing] = useState(false);

    const handleInterestEditClick = () => {
        setInterestIsEditing(true);
    };

    const handleInterestDoneClick = () => {
        setInterestIsEditing(false);
    };

    const [mentoringIsEditing, setMentoringIsEditing] = useState(false);

    const handleMentoringEditClick = () => {
        setMentoringIsEditing(true);
    };

    const handleMentoringDoneClick = () => {
        setMentoringIsEditing(false);
    };

    return (
        <Container>
            <Accordion alwaysOpen>
                <Accordion.Item eventKey="0">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faCircleUser} className="profileIcon"/>Information</Accordion.Header>
                    <Accordion.Body>
                        <UserInfo userProfileData={userProfileData}/>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faBook} className="profileIcon"/>Program</Accordion.Header>
                    <Accordion.Body>
                        <UserProgram userId={userId} universityId={universityId}/>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="2">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faHeart} className="profileIcon"/>Interest</Accordion.Header>
                    <Accordion.Body>
                        <div className="profileBlock">
                            <div className="courseBlockEdit">
                                {interestIsEditing ? (
                                    <button onClick={handleInterestDoneClick} className="profileEditDone"><FontAwesomeIcon icon={faSquareCheck} /> Done</button>
                                ) : (
                                    <button onClick={handleInterestEditClick} className="profileEditDone"><FontAwesomeIcon icon={faPenToSquare} /> Edit</button>
                                )}
                            </div>
                            <div>
                                <p className="profileText">Courses Taken: </p>
                                <Select
                                    isMulti
                                    options={courseTaken}
                                    value={selectedCourses}
                                    placeholder="Select the subjects you have taken"
                                    className="selectSubjectTaken"
                                    onChange={handleCourseSelection}
                                    isDisabled={!interestIsEditing}
                                />
                            </div>
                            <div>
                                <p className="profileText">Courses Interested: </p>
                                <Select
                                    isMulti
                                    options={courseTaken}
                                    placeholder="Select the subjects you are interested in"
                                    className="selectSubjectTaken"
                                    isDisabled={!interestIsEditing}
                                />
                            </div>
                            <div>
                                <p className="profileText">Concentration Interested: </p>
                            </div>

                        </div>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="3">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faUserGraduate} className="profileIcon"/>Mentoring on</Accordion.Header>
                    <Accordion.Body>
                        <div className="profileBlock">
                            <div className="courseBlockEdit">
                                {mentoringIsEditing ? (
                                    <button onClick={handleMentoringDoneClick} className="profileEditDone"><FontAwesomeIcon icon={faSquareCheck} /> Done</button>
                                ) : (
                                    <button onClick={handleMentoringEditClick} className="profileEditDone"><FontAwesomeIcon icon={faPenToSquare} /> Edit</button>
                                )}
                            </div>
                            <p className="profileText">Mentoring on: </p>
                            <Select
                                isMulti
                                options={mentoringOn}
                                placeholder="Select the subjects you would like to mentor on"
                                className="selectSubjectTaken"
                                onChange={(selectedOptions) => {
                                    setSelectedMentorOn(selectedOptions);
                                }}
                                key={mentoringOn.map((option) => option.value).join(',')}
                                defaultValue={mentoringOn.filter((option) =>
                                    selectedMentorOn.some((selectedOption) => selectedOption.value === option.value)
                                )}
                                isDisabled={!mentoringIsEditing}
                            />
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </Container>
    );
};