import React, {useEffect, useState} from "react";
import DriverResponse from "../types/driver";
import driverService from "../service/driverService";


const DriverList: React.FC = () => {
    const [drivers, setDrivers] = useState<Array<DriverResponse>>([]);

    const state = {
        drivers : [],
    };

    useEffect(() => {
        getDrivers()
    }, [drivers]);

    const getDrivers = () => {
        driverService.findAllByDeletedFalse()
            .then((response : any) => {
                console.log(response.data);
                setDrivers(driver => [...driver]);
            }).catch((e: Error) => {
                console.log(e);
        });
    };

    return (
        <div></div>
    );
}


export default DriverList;