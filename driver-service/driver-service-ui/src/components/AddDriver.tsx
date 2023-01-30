import React, {useState, useEffect, ChangeEvent} from "react";
import DriverResponse from "../types/driver";
import DriverCommand from "../types/driver";
import driverService from "../service/driverService";



const AddDriver: React.FC = () => {

    const initDriver = {
        firstName: "",
        lastName: ""
    };

    const [driver, setDriver] = useState<DriverResponse>(initDriver);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setDriver({ ...driver, [name]: value });
    };

    const createDriver = () => {

        const data = {
            firstName : driver.firstName,
            lastName : driver.lastName
        };
        driverService.create(data)
            .then((response : any) => {
                console.log(response.data);
                setDriver({
                    firstName: response.data.firstName,
                    lastName : response.data.lastName,
                });
            }).catch((e: Error) => {
                console.log(e);
        });
    };

    return (
        <div></div>
    );
}

export default AddDriver;