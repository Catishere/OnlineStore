import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Event, Product} from '..';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EventService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Event[]>(`${environment.apiUrl}/events`, {headers: environment.HEADERS});
  }

  add(event: Event) {
    return this.http.post(`${environment.apiUrl}/events`, event, {headers: environment.HEADERS});
  }

  addProduct(eventId: number, product: Product) {
    return this.http.post(`${environment.apiUrl}/events/${eventId}`, product, {headers: environment.HEADERS});
  }

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/events/${id}`, {headers: environment.HEADERS});
  }

  update(id: number) {
    return this.http.put(`${environment.apiUrl}/events/${id}`, {headers: environment.HEADERS});
  }

  updateProduct(eventId: number, product: Product) {
    return this.http.put(`${environment.apiUrl}/events/${eventId}`, {headers: environment.HEADERS});
  }
}
