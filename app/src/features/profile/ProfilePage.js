import Container from "react-bootstrap/Container";
import React, { useEffect, useState } from "react";
import Accordion from "react-bootstrap/Accordion";
import { faCircleUser } from "@fortawesome/free-regular-svg-icons";
import { faBook } from "@fortawesome/free-solid-svg-icons"
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { faUserGraduate } from "@fortawesome/free-solid-svg-icons"
import { faPenToSquare } from "@fortawesome/free-regular-svg-icons";
import { faSquareCheck } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    useAddUserConcentrationMutation,
    useAddUserCourseInterestedMutation,
    useAddUserCourseTakenMutation,
    useAddUserMentorCourseMutation,
    useGetCoursesQuery,
    useGetUserInfoQuery
} from "../api/apiSlice";
import { useParams } from "react-router-dom";
import Select from "react-select";
import { UserInfo } from "./UserInfo";
import { UserProgram } from "./UserProgram";
import Form from "react-bootstrap/Form";
import { Row } from "react-bootstrap";

export const ProfilePage = () => {

    const [universityId, setUniversityId] = useState(1)

    const {
        data: userProfileData,
        isSuccess: userProfileSuccess,
    } = useGetUserInfoQuery();

    // set parameters
    const [courseTaken, setCourseTaken] = useState([]);
    const [selectedCoursesId, setSelectedCoursesId] = useState([]);
    const [addUserCourseTaken, { isLoading: courseTakenIsLoading }] = useAddUserCourseTakenMutation();
    const [defaultCourseTaken, setDefaultCourseTaken] = useState([]);

    const [selectedCoursesInterestId, setSelectedCoursesInterestedId] = useState([]);
    const [addUserCourseInterested, { isLoading: courseInterestedIsLoading }] = useAddUserCourseInterestedMutation();
    const [defaultCourseInterested, setDefaultCourseInterested] = useState([]);

    const [mentoringOn, setMentoringOn] = useState([]);
    const [selectedMentorOnId, setSelectedMentorOnId] = useState([]);
    const [addMentorOn, { isLoading: mentorOnIsLoading }] = useAddUserMentorCourseMutation()
    const [defaultMentorOn, setDefaultMentorOn] = useState([]);

    // set default value
    useEffect(() => {
        if (userProfileData && userProfileData.course) {
            const courseTakenOptions = userProfileData.course.map((course) => ({
                value: course.id,
                label: course.name,
            }));
            setDefaultCourseTaken(courseTakenOptions);
            setMentoringOn(courseTakenOptions);
            const selectedCoursesId = userProfileData.course.map((course) => ({
                id: course.id,
            }));
            setSelectedCoursesId(selectedCoursesId);
        }
        if (userProfileData && userProfileData.interestedCourse) {
            const courseInterestedOptions = userProfileData.interestedCourse.map((course) => ({
                value: course.id,
                label: course.name,
            }));
            setDefaultCourseInterested(courseInterestedOptions);
            const selectedCoursesInterestId = userProfileData.interestedCourse.map((course) => ({
                id: course.id,
            }));
            setSelectedCoursesInterestedId(selectedCoursesInterestId);
        }
        if (userProfileData && userProfileData.concentration) {
            setContent(userProfileData.concentration)
        }
        if (userProfileData && userProfileData.mentorCourse) {
            const courseMentorOnOptions = userProfileData.mentorCourse.map((course) => ({
                value: course.id,
                label: course.name,
            }));
            setDefaultMentorOn(courseMentorOnOptions);
            const selectedMentorOnId = userProfileData.mentorCourse.map((course) => ({
                id: course.id,
            }));
            setSelectedCoursesId(selectedMentorOnId);
        }
    }, [userProfileData])

    // set course taken & interested select options
    const {
        data: courseTakenData,
        isSuccess: courseTakenSuccess,
    } = useGetCoursesQuery({ universityId: universityId, page: "", size: "" });

    useEffect(() => {
        if (courseTakenSuccess) {
            const courseTakenOptions = courseTakenData.courses.map((courseTaken) => ({
                value: courseTaken.id,
                label: courseTaken.name,
            }));
            setCourseTaken([, ...courseTakenOptions,
            ]);
        }
    }, [courseTakenSuccess, courseTakenData]);

    const handleCourseSelection = (selectedOptions) => {
        setDefaultCourseTaken(selectedOptions)
        setSelectedCoursesId(
            selectedOptions.map((option) => ({
                id: option.value,
            }))
        );
        const filteredMentorOn = defaultMentorOn.filter((option) =>
            selectedOptions.some((subject) => subject.value === option.value)
        );
        setDefaultMentorOn(filteredMentorOn);
        setMentoringOn(selectedOptions);
    };

    const handleCourseInterestedSelects = (selectedOptions) => {
        setDefaultCourseInterested(selectedOptions)
        setSelectedCoursesInterestedId(
            selectedOptions.map((option) => ({
                id: option.value,
            }))
        );
    }

    // set concentration interested
    const [content, setContent] = useState(null)
    const [addUserConcentration, { isLoading: concentrationIsLoading }] = useAddUserConcentrationMutation();
    const onContentChanged = (e) => setContent(e.target.value)

    // set mentoring on select options based on course taken
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

    const handleInterestDoneClick = async () => {
        await addUserCourseTaken({ courseList: selectedCoursesId })
        await addUserCourseInterested({ courseList: selectedCoursesInterestId })
        await addUserConcentration({ concentration: content })
        setInterestIsEditing(false);
    };

    console.log(selectedCoursesInterestId)
    console.log(userProfileData)

    const [mentoringIsEditing, setMentoringIsEditing] = useState(false);

    const handleMentoringEditClick = () => {
        setMentoringIsEditing(true);
    };

    const handleMentoringDoneClick = async () => {
        await addMentorOn({ courseList: selectedMentorOnId })
        setMentoringIsEditing(false);
    };

    return (
        <Container>
            <Accordion defaultActiveKey={['0']} alwaysOpen>
                <Accordion.Item eventKey="0">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faCircleUser} className="profileIcon" />Information</Accordion.Header>
                    <Accordion.Body>
                        <UserInfo userProfileData={userProfileData} />
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faBook} className="profileIcon" />Program</Accordion.Header>
                    <Accordion.Body>
                        <UserProgram userProfileData={userProfileData} />
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="2">
                    <Accordion.Header className="profileTitleText"><FontAwesomeIcon icon={faHeart} className="profileIcon" />Interest</Accordion.Header>
                    <Accordion.Body>
                        <div className="profileBlock" style={{display: 'block'}}>
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
                                    value={defaultCourseTaken}
                                    placeholder="Select the subjects you have taken"
                                    className="selectCourseTaken"
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
                                    className="selectCourseInterested"
                                    isDisabled={!interestIsEditing}
                                    onChange={handleCourseInterestedSelects}
                                    value={defaultCourseInterested}
                                />
                            </div>
                            <div>
                                <p className="profileText">Concentration Interested: </p>
                                <Form className="profileConcentration">
                                    <Form.Group as={Row}>
                                        <Form.Control
                                            as="textarea"
                                            aria-label="With textarea"
                                            placeholder='Type your concentration interested'
                                            onChange={onContentChanged}
                                            disabled={!interestIsEditing}
                                            defaultValue={content}
                                        />
                                    </Form.Group>
                                </Form>
                            </div>
                        </div>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="3">
                    <Accordion.Header style={{marginBottom: '50px'}} className="profileTitleText"><FontAwesomeIcon icon={faUserGraduate} className="profileIcon" />Mentoring on</Accordion.Header>
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
                                className="profileMentorOn"
                                onChange={(selectedOptions) => {
                                    setDefaultMentorOn(selectedOptions);
                                    setSelectedMentorOnId(
                                        selectedOptions.map((option) => ({
                                            id: option.value,
                                        }))
                                    );
                                }}
                                key={mentoringOn.map((option) => option.value).join(',')}
                                defaultValue={mentoringOn.filter((option) =>
                                    defaultMentorOn.some((selectedOption) => selectedOption.value === option.value)
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