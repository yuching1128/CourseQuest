import React, {useEffect, useState} from "react";
import Select from "react-select";
import { faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {useGetDepartmentsQuery, useGetLevelsQuery} from "../api/apiSlice";
import {useParams} from "react-router-dom";

export const SearchComponent = () => {
    const { universityId } = useParams();

    const [departments, setDepartments] = useState([]);

    const [levels, setLevels] = useState([]);

    const {
        data: departmentData,
        isSuccess:departmentSuccess,
    } = useGetDepartmentsQuery(universityId);

    const {
        data: levelData,
        isSuccess:levelSuccess,
    } = useGetLevelsQuery(universityId);

    useEffect(() => {
        if (departmentSuccess) {
            const departmentOptions = departmentData.map((dept) => ({
                value: dept.dept_name,
                label: dept.dept_name,
            }));
            setDepartments([
                { value: "", label: "-- Select department" },
                ...departmentOptions,
            ]);
        }
    }, [departmentSuccess, departmentData]);

    useEffect(() => {
        if (levelSuccess) {
            const levelOptions = levelData.map((level) => ({
                value: level.level,
                label: level.level,
            }));
            setLevels([
                { value: "", label: "-- Select level" },
                ...levelOptions,
            ]);
        }
    }, [levelSuccess, levelData]);

    return (
        <form method="get">
            <input className="searchBar"
                   type="text"
                   placeholder="Search for keywords"
            />
            <button className="searchButton" type="submit"><FontAwesomeIcon icon={faMagnifyingGlass}/></button>

            <div>
                <p className="departmentText">Department: </p>
                <Select className="department"
                         options={departments}
                         defaultValue={{value: "", label: "-- Select level" }}
                         isSearchable={false}
                />
                <p className="levelText">Level: </p>
                <Select className="level"
                        options={levels}
                        defaultValue={{value: "", label: "-- Select level" }}
                        isSearchable={false}
                />
            </div>
        </form>
    );
}