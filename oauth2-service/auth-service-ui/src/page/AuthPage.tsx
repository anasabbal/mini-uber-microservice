import React, {ChangeEvent, useState} from "react";
import AccountCommand from "../service/accountcmd";
import userService from "../service/userService";


const AuthPage: React.FC = () => {

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
        <div></div>
    );
}


export default AuthPage;