import { Role } from "../role/role";

export class User {
    id: number | undefined;
    username: string | undefined;
    email: string | undefined;
    firstName: string | undefined;
    lastName: string | undefined;
    password: string | undefined;
    roles: Array<Role> | undefined;
    activated: boolean | undefined;
    message: string | undefined;
}
