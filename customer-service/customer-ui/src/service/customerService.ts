import ApiRoutes from "../core/ApiRoutes";
import http from "../core/http-common";
import CustomerCommand from "../type/customer";
import CustomerResponse from "../type/customer";


const findAllByDeletedFalse = () => {
    return http.get<Array<CustomerResponse>>(ApiRoutes.about);
}
const create = (payload: CustomerCommand) => {
    return http.post<CustomerCommand>(ApiRoutes.about, payload);
}
const findById = (id: string) => {
    return http.get<CustomerResponse>(ApiRoutes.about +`/${id}`);
}
const updateInfo =(id: string, payload : CustomerCommand) => {

}


const customerService = {
    create,
    findAllByDeletedFalse,
    findById,
    updateInfo
}
export default customerService;
