import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Client } from '@/_models';
import headers from "@/_services/headers.json";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentClientSubject: BehaviorSubject<Client>;
    public currentClient: Observable<Client>;

    constructor(private http: HttpClient) {
        this.currentClientSubject = new BehaviorSubject<Client>(JSON.parse(localStorage.getItem('currentClient')));
        this.currentClient = this.currentClientSubject.asObservable();
    }

    public get currentClientValue(): Client {
        return this.currentClientSubject.value;
    }

    login(username, password) {
        return this.http.post<any>(`${config.apiUrl}/clients/authenticate`,
            { username, password }, {headers: headers})
            .pipe(map(client => {
                // store client details and jwt token in local storage to keep client logged in between page refreshes
                localStorage.setItem('currentClient', JSON.stringify(client));
                this.currentClientSubject.next(client);
                return client;
            }));
    }

    logout() {
        // remove client from local storage and set current client to null
        localStorage.removeItem('currentClient');
        this.currentClientSubject.next(null);
    }
}