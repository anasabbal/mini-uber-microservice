enum RoleType {
    ADMIN, USER
}


export default interface AccountDto{
    id: string,
    userName: string,
    email: string,
    password: string,
    roles: [],
    customerId: string,
}

