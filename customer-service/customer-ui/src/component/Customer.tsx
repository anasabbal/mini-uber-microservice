import React, {ChangeEvent, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import customerService from "../service/customerService";
import CustomerCommand from "../type/customer";
import DriverResponse from "../type/driver";
import CustomerRequestDriver from "../type/request";


const Customer: React.FC = () => {
    const {id} = useParams();
    let navigate = useNavigate();

    const initCustomer = {
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        published: false
    };
    const initDriver = {
        driver: [],
    };
    const initRequest = {
        driverId: "",
        customerId: ""
    };

    const [customer, setCustomer] = useState<CustomerCommand>(initCustomer);
    const [message, setMessage] = useState<string>("");
    const [driver, setDriver] = useState<Array<DriverResponse>>([]);
    const [request, setRequest] = useState<CustomerRequestDriver>(initRequest);


    useEffect(() => {
       if(id)
           getCustomerById(id);
    }, [id]);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setCustomer({ ...customer, [name]: value });
    };
    const getDriversAvailable = () => {
        customerService.getDriversAvailable()
            .then((response : any) => {
                console.log(response.data);
                setDriver(driver => [...driver]);
            }).catch((e: Error) => {
                console.log(e);
        });
    };
    const sendRequestToDriver = (request: CustomerRequestDriver) => {
        const data = {
            customerId: request.customerId,
            driverId: request.driverId
        };
        customerService.sendRequestToDriver(data)
            .then((response : any ) => {
                console.log(response.data);
                setRequest({
                    customerId: response.data.customerId,
                    driverId: response.data.driverId,
                });
            }).catch((e: Error) => {
                console.log(e);
        });
    };
    const getCustomers = () => {
        customerService.findAllByDeletedFalse()
            .then((res: any) => {
                console.log(res.data);
            }).catch((e: Error) => {
                console.log(e);
        });
    };
    const getCustomerById = (id: string) => {
        customerService.findById(id)
            .then((response : any) => {
                setCustomer({
                    id: response.data.id,
                    lastName: response.data.lastName,
                    email: response.data.email,
                    password: response.data.password
                });
            }).catch((e: Error) => {
                console.log(e);
        });
    }
    const updatePublished = (status: boolean) => {
        const data = {
            id: customer.id,
            firstName: customer.firstName,
            lastName: customer.lastName,
            email: customer.email,
            password: customer.password,
            published: status
        };
        customerService.updateInfo(customer.id, data)
            .then((response : any) => {
                console.log(response.data);
                setCustomer({ ...customer});
                setMessage("The status was updated successfully!");
            }).catch((e : Error) => {
                console.log(e);
        });
    };
    const updateCustomerById = () => {
        customerService.updateInfo(customer.id, customer)
            .then((response : any) => {
                console.log(response.data);
                setCustomer({ ...customer});
                setMessage("The status was updated successfully!");
            }).catch((e : Error) => {
            console.log(e);
        });
    }
    const deleteCustomerById = () => {
        customerService.deleteById(customer.id)
            .then((response: any) => {
                console.log(response.data);
                setMessage("The Customer was deleted");
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };
    return (
        <div>
            {customer ? (
                <div className="edit-form">
                    <h4>Customer</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="firstName">First Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                name="firstName"
                                value={customer.firstName}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                name="lastName"
                                value={customer.lastName}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input
                                type="text"
                                className="form-control"
                                id="email"
                                name="email"
                                value={customer.email}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input
                                type="text"
                                className="form-control"
                                id="password"
                                name="password"
                                value={customer.password}
                                onChange={handleInputChange}
                            />
                        </div>
                    </form>
                    <button className="badge badge-danger mr-2" onClick={deleteCustomerById}>
                        Delete
                    </button>
                    <button
                        type="submit"
                        className="badge badge-success"
                        onClick={updateCustomerById}
                    >
                        Update
                    </button>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Please click on a Customer...</p>
                </div>
            )}
        </div>
    );
}

export default Customer;