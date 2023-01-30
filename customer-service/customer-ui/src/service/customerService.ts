import Customer from "../type/customer";
import ApiRoutes from "../core/ApiRoutes";
import http from "../core/http-common";


const findAllByDeletedFalse = () => {
    return http.get<Array<Customer>>(ApiRoutes.about);
}
const create = (payload: Customer) => {

}
const findById = (id: string) => {
    return http.get<Customer>(ApiRoutes.about +`/${id}`);
}
const updateInfo =(id: string, payload : Customer) => {

}


const customerService = {
    create,
    findAllByDeletedFalse,
    findById,
    updateInfo
}
export default customerService;
