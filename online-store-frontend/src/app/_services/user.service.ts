import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HEADERS } from './headers';
import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${config.apiUrl}/users`, {headers: HEADERS});
    }

    register(user: User) {

        return this.http.post(`${config.apiUrl}/users/register`, user, {headers: HEADERS});
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`, {headers: HEADERS});
    }

    update(id: number) {
        return this.http.put(`${config.apiUrl}/users/${id}`, {headers: HEADERS});
    }
}