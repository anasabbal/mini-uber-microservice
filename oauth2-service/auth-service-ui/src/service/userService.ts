import httpCommon from "../core/http-common";
import ApiRoutes from "../core/ApiRoutes";
import AccountCommand from "./accountcmd";


const create = (payload: AccountCommand) => {
    return httpCommon.post<AccountCommand>(ApiRoutes.register, payload);
}


const userService = {
    create
};


export default userService;