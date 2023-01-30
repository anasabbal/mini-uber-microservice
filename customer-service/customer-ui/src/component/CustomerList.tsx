import React, {useEffect, useState} from "react";
import customerService from "../service/customerService";
import CustomerResponse from "../type/customer";


const CustomerList: React.FC = () => {
    const [customers, setCustomers] = useState<Array<CustomerResponse>>([]);

    useEffect(() => {
        getCustomer();
    },);

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
            <h1>Tiny Store</h1>
            <div className={'App-container'}>
                {
                    customers.map((product ) => {
                        return (
                            <div className={'App-item'}>
                                <div><h3> {product.firstName}</h3> </div>
                                <div>
                                    <img src={product.lastName}
                                         alt={product.email}
                                    />
                                </div>
                                <div className={'Price-item'}>
                                    price ${product.id}
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