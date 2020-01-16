import { Injectable } from '@angular/core';
import {LoginResult, User} from "../../authentication";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Product} from "../models";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  constructor(private http: HttpClient) { }

  public async findByUsername(username: string): Promise<LoginResult> {
    let user: User;
    try {
      user = await this.http.get<User>(`${environment.publicApi}/users/username/${username}`).toPromise();
    } catch (e) {
      console.log(e);
      return {error: e.message};
    }
    return {user};
  }

    async add(user: User) {
        let resultUser: User;
        try {
            resultUser = await this.http.post<User>(`${environment.adminApi}/users`, user).toPromise();
        } catch (e) {
            console.log(e);
        }
        return resultUser;
    }
}
