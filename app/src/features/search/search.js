import React, {useEffect, useState} from "react";
import Select from "react-select";
import { faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {useGetDepartmentsQuery, useGetLevelsQuery, useSearchCoursesMutation} from "../api/apiSlice";
import {useParams} from "react-router-dom";

export const SearchComponent = () => {
    const { universityId } = useParams();

    const [departments, setDepartments] = useState([]);

    const [levels, setLevels] = useState([]);
    //----
    const [searchCourses, { isLoading: searchIsLoading }] =  useSearchCoursesMutation();

    const [searchDTO,setSearchDTO] = useState({
        fullTextSearch:'',
        dept:'',
        level:'',
        universityId:''
    })

    const {
        data: departmentData,
        isSuccess:departmentSuccess,
    } = useGetDepartmentsQuery(universityId);

    const {
        data: levelData,
        isSuccess:levelSuccess,
    } = useGetLevelsQuery(universityId);
//---
    useEffect(() => {
        if (departmentSuccess) {
            const departmentOptions = departmentData.map((dept) => ({
                value: dept.name,
                label: dept.name,
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
                value: level.name,
                label: level.name,
            }));
            setLevels([
                { value: "", label: "-- Select level" },
                ...levelOptions,
            ]);
        }
    }, [levelSuccess, levelData]);
//-----------
    const handleDeptChange = (selectedOption) => {
        const selectedDeptObj = departmentData.find(
            (dept) => dept.name === selectedOption.value
        );
        setSearchDTO({dept: selectedDeptObj.name})
    };

    const handleLevelChange = (selectedOption) => {
        const selectedLevelObj = levelData.find(
            (level) => level.name === selectedOption.value
        );
        setSearchDTO({level: selectedLevelObj.name})
    };
//-----------
    const handleCourseSearchClick = (e)=> {
        e.preventDefault();
        console.log("Data submitted: ",  e);
        searchDTO.fullTextSearch = e.target[0].value;
        searchDTO.level = e.target[5].value;
        searchDTO.dept = e.target[3].value;
        searchDTO.universityId = universityId
        setSearchDTO({...searchDTO })
        searchCourses({searchDTO: searchDTO, page: 1, size:  10})
    };

//---
    return (
        <form className="searchForm" onSubmit={handleCourseSearchClick}>
            <div className="searchBarContainer">
                <input className="searchBar"
                   type="text"
                   placeholder="Search for keywords"
                   name="searchText"
                />
                <button className="searchButton" type="submit" ><FontAwesomeIcon icon={faMagnifyingGlass}/></button>
            </div>
            <div className="filterContainer">
                <div className="filterItem">
                    <p className="filterLabel">Department: </p>
                    <Select
                        className="department"
                        options={departments}
                        defaultValue={{ value: "", label: "-- Select department" }}
                        isSearchable={false}
                        /*onChange={handleDeptChange}*/
                        name="department"
                    />
                </div>
                <div className="filterItem">
                    <p className="filterLabel">Level:</p>
                    <Select className="level"
                            options={levels}
                            defaultValue={{value: "", label: "-- Select level" }}
                            isSearchable={false}
                            /*onChange={handleLevelChange}*/
                            name="level"
                    />
                </div>
            </div>
        </form>
    );
}
