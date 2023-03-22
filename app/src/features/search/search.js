import React, { useState } from "react";
import Select from "react-select";
import { faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export const SearchComponent = () => {
    const sortBy = [
        { value: "", label: "-- Sort by"  },
        { value: "chocolate", label: "Chocolate" },
        { value: "strawberry", label: "Strawberry" },
        { value: "vanilla", label: "Vanilla" },
    ];

    const departments = [
        { value: "", label: "-- Select department"  },
        { value: "CS", label: "CS" },
        { value: "ECE", label: "ECE" },
        { value: "MIT", label: "MIT" },
    ];

    const levels = [
        { value: "", label: "-- Select level"  },
        { value: "1xxx", label: "1xxx" },
        { value: "2xxx", label: "2xxx" },
        { value: "3xxx", label: "3xxx" },
    ];

    return (
        <form method="get">
            <input className="searchBar"
                   type="text"
                   placeholder="Search for keywords"
            />
            <button className="searchButton" type="submit"><FontAwesomeIcon icon={faMagnifyingGlass}/></button>

            <p className="sortByText">Sort by: </p>
            <Select className="sortBy"
                    options={sortBy}
                    defaultValue={sortBy[0]}
                    isSearchable={false}
            />
            <div>
                <p className="departmentText">Department: </p>
                <Select className="department"
                         options={departments}
                         defaultValue={departments[0]}
                         isSearchable={false}
                />
                <p className="levelText">Level: </p>
                <Select className="level"
                        options={levels}
                        defaultValue={levels[0]}
                        isSearchable={false}
                />
            </div>

        </form>
    );
}