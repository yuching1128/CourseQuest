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
//-----------
    const handleCourseSearchClick = async (e) => {
        e.preventDefault();
        const searchDTO = {
            fullTextSearch: e.target[0].value,
            dept: e.target[3].value,
            level: e.target[5].value,
            universityId: universityId,
        };

        try {
            const response = await searchCourses({
                searchDTO: searchDTO,
                page: 1,
                size: 10,
            });
        } catch (error) {
            console.log(error.message);
        }
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
                        name="department"
                    />
                </div>
                <div className="filterItem">
                    <p className="filterLabel">Level:</p>
                    <Select className="level"
                            options={levels}
                            defaultValue={{value: "", label: "-- Select level" }}
                            isSearchable={false}
                            name="level"
                    />
                </div>
            </div>
        </form>
    );
}
