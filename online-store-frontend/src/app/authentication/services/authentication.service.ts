import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User, LoginResult } from '../models/user';
import { Register } from '../models/register';
import {environment} from "../../../environments/environment";
import {UserService} from "./user.service";

/** Authentication API Service */
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient,
                private userService: UserService) {}

    /** Send basic credentials to login endpoint. */
    public async login(userData: Register): Promise<LoginResult> {
        return this.loginPost('/users/authenticate', userData);
    }

    /** Send user info to register endpoint. */
    public async register(userData: Register): Promise<LoginResult> {
        return this.loginPost('/users/register', userData);
    }

    protected async loginPost(endpoint: string, userData: Register): Promise<LoginResult> {
        let data: User;
        let token = btoa(userData.username + ":" + userData.password);
        try {
            data = await this.http.post<User>(environment.publicApi + endpoint, userData).toPromise();
        } catch (e) {
            console.log(e);
            return { error: "Wrong Credentials!" };
        }

        this.userService.setCurrentUser({token: token} as User);
        let user: User = data as User;
        return { user };
    }

    public async logout(): Promise<any> {
        return await this.http.post(`${environment.api}/logout`, {}).toPromise();
    }
}
