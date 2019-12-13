import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '..';
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Product[]>(`${environment.apiUrl}/products`, {headers: environment.HEADERS});
  }

  add(product: Product) {

    return this.http.post(`${environment.apiUrl}/products`, product, {headers: environment.HEADERS});
  }

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/products/${id}`, {headers: environment.HEADERS});
  }

  getProductById(id: string) {
    return this.http.get<Product>(`${environment.apiUrl}/products/${id}`, {headers: environment.HEADERS})
  }
}
