export default interface CustomerCommand {
    firstName?: string,
    lastName?: string,
    email?: string,
    password?: string
}

export default interface CustomerResponse {
    id?: string,
    firstName?: string,
    lastName?: string,
    email?: string,
    password?: string
}