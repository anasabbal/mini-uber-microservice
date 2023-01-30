import React, {useEffect, useState} from "react";
import customerService from "../service/customerService";
import CustomerResponse from "../type/customer";


const CustomerList: React.FC = () => {
    const [customers, setCustomers] = useState<Array<CustomerResponse>>([]);

    useEffect(() => {
        getCustomer();
    },[customers]);

    const getCustomer = () => {
        customerService.findAllByDeletedFalse()
            .then((response: any) => {
                setCustomers(response.data);
                console.log(customers);
            }).catch((e: Error) => {
                console.log(e)
        });
    };
    return (
        <>
            <h1>Customer List</h1>
            <div className={'App-container'}>
                {
                    customers.map((customer ) => {
                        return (
                            <div className={'App-item'}>
                                <div><h3> {customer.firstName}</h3> </div>
                                <div>
                                    <img src={customer.lastName}
                                         alt={customer.email}
                                    />
                                </div>
                                <div className={'Price-item'}>
                                    price ${customer.id}
                                </div>
                            </div>
                        )

                    })
                }
            </div>
        </>
    );
};

export default CustomerList;