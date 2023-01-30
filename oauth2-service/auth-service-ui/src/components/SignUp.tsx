import React, {ChangeEvent, useState} from "react";
import userService from "../service/userService";
import AccountCommand from "../service/accountcmd";
import "../css/style.css";

const SignUp: React.FC = () => {

    const initAccount = {
        userName: "",
        email: "",
        password: ""
    };

    const [account, setAccount] = useState<AccountCommand>(initAccount);
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setAccount({ ...account, [name]: value });
    };

    const register = () => {
        const data = {
            userName: account?.userName,
            email: account?.email,
            password: account?.password
        };
        userService.create(data)
            .then((response : any) => {
                console.log(response.data);
                setAccount({
                    userName: response.data.userName,
                    email: response.data.email,
                    password: response.data.password
                });
                setSubmitted(true);
            }).catch((e: Error) => {
            console.log(e);
        });
    }
    const newAccount = () => {
        register();
        setSubmitted(true);
    }

    const login = () => {
        const data = {
            userName: account?.userName,
            email: account?.email,
            password: account?.password
        };
        userService.create(data)
            .then((response : any) => {
                console.log(response.data);
                setAccount({
                    userName: response.data.userName,
                    email: response.data.email,
                    password: response.data.password
                });
                localStorage.setItem("account", JSON.stringify(response.data));
            }).catch((e: Error) => {
                console.log(e);
        });
    }
    const logout = () => {
        return localStorage.removeItem("account");
    }

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newAccount}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="title">UserName</label>
                        <input
                            type="text"
                            className="form-control"
                            id="userName"
                            required
                            value={account.userName}
                            onChange={handleInputChange}
                            name="userName"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Email</label>
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            required
                            value={account.email}
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
                            value={account.password}
                            onChange={handleInputChange}
                            name="password"
                        />
                    </div>
                    <button onClick={register} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
}
export default SignUp;