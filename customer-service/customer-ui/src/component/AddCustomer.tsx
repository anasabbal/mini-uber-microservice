import React, {ChangeEvent, useState} from "react";
import CustomerCommand from "../type/customer";
import customerService from "../service/customerService";


const AddCustomer: React.FC = () => {

    const initCustomer = {
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    };
    const [customer, setCustomer] = useState<CustomerCommand>(initCustomer);
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setCustomer({ ...customer, [name]: value });
    };

    const createCustomer = () => {
        const data = {
            firstName: customer.firstName,
            lastName: customer.lastName,
            email: customer.email,
            password: customer.password
        };
        customerService.create(data)
            .then((response : any) => {
                setCustomer({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    email: response.data.email,
                    password: response.data.password
                });
                setSubmitted(true);
                console.log(response.data);
            }).catch((e: Error) => {
                console.log(e);
        })
    };
    const newCustomer = () => {
        setCustomer(initCustomer);
        setSubmitted(true);
    }

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newCustomer}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="title">First Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="firstName"
                            required
                            value={customer.firstName}
                            onChange={handleInputChange}
                            name="firstName"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Last Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="lastName"
                            required
                            value={customer.lastName}
                            onChange={handleInputChange}
                            name="lastName"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Email</label>
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            required
                            value={customer.email}
                            onChange={handleInputChange}
                            name="email"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Password</label>
                        <input
                            type="text"
                            className="form-control"
                            id="password"
                            required
                            value={customer.password}
                            onChange={handleInputChange}
                            name="password"
                        />
                    </div>
                    <button onClick={createCustomer} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
}

export default AddCustomer;