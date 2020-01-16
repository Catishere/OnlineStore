import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {LoginResult, User} from '../models/user';
import {Register} from '../models/register';
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
        return this.loginPost(environment.userApi + '/authenticate', userData);
    }

    /** Send user info to register endpoint. */
    public async register(userData: Register): Promise<LoginResult> {
        return this.loginPost(environment.publicApi + '/users/register', userData);
    }

    protected async loginPost(endpoint: string, userData: Register): Promise<LoginResult> {
        let data: User = {} as User;
        data.token = btoa(userData.username + ":" + userData.password);
        this.userService.setCurrentUser(data);
        data = await this.http.post<User>(endpoint, userData).toPromise();
        if (data["status"] === 202)
            return { error: "Wrong Credentials!" };
        let user: User = data as User;
        this.userService.setMerge(this.userService.currentUser, user);
        return { user };
    }
}
