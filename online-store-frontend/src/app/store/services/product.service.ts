import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Event, Product} from '..';
import {environment} from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    constructor(private http: HttpClient) {
    }

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

    async delete(id: number) {
        return await this.http.delete(`${environment.adminApi}/products/${id}`).toPromise();
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

    async buy(id: number, quantity: number) {
        return await this.http.put(`${environment.userApi}/products/buy/${id}`, {quantity: quantity}).toPromise();
    }
}
