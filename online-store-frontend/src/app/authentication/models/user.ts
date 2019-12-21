/** Data transfer model expected from backend API JWT-s */
export interface UserJWT {
    // using registered/public claims per https://www.iana.org/assignments/jwt/jwt.xhtml
    exp: number;
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    picture: string;
}

/** Client user model */
export interface User extends UserJWT {
    roles: string[];
    token: string;
}


export interface LoginResult {
    user?: User;
    error?: string;
}
