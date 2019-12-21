import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Event, Product} from '..';
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Product[]>(`${environment.publicApi}/products`);
  }

  async add(product: Product) {
    let resultProduct: Product;
    try {
      resultProduct = await this.http.post<Product>(`${environment.adminApi}/products`, product).toPromise();
    } catch (e) {
      console.log(e);
    }
    return resultProduct;
  }

  delete(id: number) {
    return this.http.delete(`${environment.adminApi}/products/${id}`);
  }

  async getProductById(id: string) {
    let resultProduct: Product;
    try {
      resultProduct = await this.http.get<Product>(`${environment.publicApi}/products/${id}`).toPromise();
    } catch (e) {
      console.log(e);
    }
    return resultProduct;
  }
}
