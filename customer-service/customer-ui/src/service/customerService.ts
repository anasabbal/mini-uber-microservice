import ApiRoutes from "../core/ApiRoutes";
import http from "../core/http-common";
import CustomerCommand from "../type/customer";
import CustomerResponse from "../type/customer";
import {Params} from "react-router-dom";


const findAllByDeletedFalse = () => {
    return http.get<Array<CustomerResponse>>(ApiRoutes.about);
}
const create = (payload: CustomerCommand) => {
    return http.post<CustomerCommand>(ApiRoutes.about, payload);
}
const findById = (id: string) => {
    return http.get<CustomerResponse>(ApiRoutes.about +`/${id}`);
}
const updateInfo = (id: any, payload: CustomerCommand) => {
    return http.put(ApiRoutes.about +`/${id}`, payload);
}
const deleteById = (id: any) => {
    return http.delete(ApiRoutes.about +`/${id}`);
}


const customerService = {
    create,
    findAllByDeletedFalse,
    findById,
    updateInfo,
    deleteById
}
export default customerService;
