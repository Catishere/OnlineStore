import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HEADERS } from './headers';
import { Product } from '@/_models';

@Injectable({ providedIn: 'root' })
export class ProductService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Product[]>(`${config.apiUrl}/products`, {headers: HEADERS});
    }

    add(product: Product) {

        return this.http.post(`${config.apiUrl}/products`, product, {headers: HEADERS});
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/products/${id}`, {headers: HEADERS});
    }

    getProductById(id: string) {
        return this.http.get<Product>(`${config.apiUrl}/products/${id}`, {headers: HEADERS})
    }
}