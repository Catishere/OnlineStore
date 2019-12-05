import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import headers from './headers.json';
import { Client } from '@/_models';

@Injectable({ providedIn: 'root' })
export class ClientService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Client[]>(`${config.apiUrl}/clients`, {headers: headers});
    }

    register(client: Client) {
        return this.http.post(`${config.apiUrl}/clients/register`, client, {headers: headers});
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/clients/${id}`, {headers: headers});
    }
}