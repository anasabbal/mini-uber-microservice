import DriverCommand from "../types/driver";
import DriverResponse from "../types/driver";
import http from "../core/http-common";
import ApiRoutes from "../core/ApiRoutes";


const findAllByDeletedFalse = () => {
    return http.get<DriverResponse>(ApiRoutes.about);
}
const create = (payload: DriverCommand) => {
    return http.post<DriverCommand>(ApiRoutes.about, payload);
}
const findById = (id: any) => {
    return http.get<DriverResponse>(ApiRoutes.about +`/${id}`);
}
const updateInfo = (id: any, payload: DriverCommand) => {
    return http.put(ApiRoutes.about +`/${id}`, payload);
}
const deleteById = (id: any) => {
    return http.delete(ApiRoutes.about +`/${id}`);
}


const driverService = {
    create,
    findById,
    updateInfo,
    deleteById,
    findAllByDeletedFalse
}
export default driverService;