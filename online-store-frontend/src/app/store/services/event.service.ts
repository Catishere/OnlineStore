import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Event, Product} from '..';
import {environment} from "../../../environments/environment";
import {User} from "../../authentication";

@Injectable({
  providedIn: 'root'
})
export class EventService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Event[]>(`${environment.publicApi}/events`);
  }

  async add(event: Event) {
    let resultEvent: Event;
    try {
      resultEvent = await this.http.post<Event>(`${environment.adminApi}/events`, event).toPromise();
    } catch (e) {
      console.log(e);
    }
    return resultEvent;
  }

  addProduct(eventId: number, product: Product) {
    return this.http.post(`${environment.adminApi}/events/${eventId}`, product);
  }

  delete(id: number) {
    return this.http.delete(`${environment.adminApi}/events/${id}`);
  }

  update(id: number, product: Product) {
    return this.http.put(`${environment.adminApi}/events/${id}`, product);
  }

  updateProduct(eventId: number, product: Product) {
    return this.http.put(`${environment.adminApi}/events/${eventId}`, product);
  }
}
