import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import {ProductService} from "@/_services/product.service";

@Component({ templateUrl: 'store.component.html' })
export class StoreComponent implements OnInit {
    products = [];

    constructor(
        private productService: ProductService
    ) {}

    ngOnInit() {
        this.loadAllProducts();
    }

    deleteUser(id: number) {
        this.productService.delete(id)
            .pipe(first())
            .subscribe(() => this.loadAllProducts());
    }

    loadAllProducts() {
        this.productService.getAll()
            .pipe(first())
            .subscribe(products => this.products = products);
    }
}