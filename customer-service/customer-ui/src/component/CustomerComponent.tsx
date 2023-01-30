import {Component, useEffect} from "react";
import customerService from "../service/customerService";
import Customer from "../type/customer";



type Props = {

}

type State = {
    customers: Array<Customer>
};

export default class CustomerComponent extends Component<Props, State>{

    constructor(props: State) {
        super(props);

        this.state = {
            customers: []
        };
    }
    componentDidMount() {
        this.findAllByDeletedFalse();
    }


    findAllByDeletedFalse() {
        customerService.findAllByDeletedFalse()
            .then((res: any) => {
                this.setState({
                    customers: res.data
                })
                console.log(res.data);
            }).catch((e : Error) => {
                console.log(e);
        });
    }
    render() {
        let customers = Array.from(this.state.customers);

        return (
            <div className="list row">
                <div className="col-md-6">
                    <h4>Tutorials List</h4>
                    <ul className="list-group">
                        {
                            customers.map((customer: Customer) => (
                                <li>
                                    {customer.email}
                                </li>
                            ))
                        }
                    </ul>
                </div>
            </div>
        );
    }
}
