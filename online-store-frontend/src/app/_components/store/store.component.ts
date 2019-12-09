import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import {ProductService} from "@/_services/product.service";
import {AlertService, AuthenticationService} from "@/_services";

@Component({ templateUrl: 'store.component.html' })
export class StoreComponent implements OnInit {
    products = [];

    constructor(
        private productService: ProductService,
        private alertService: AlertService,
        private authenticationService: AuthenticationService
    ) {}

    ngOnInit() {
        this.loadAllProducts();
    }

    deleteProduct(id: number) {
        this.productService.delete(id)
            .pipe(first())
            .subscribe(data => this.loadAllProducts(),
                    error => this.alertService.error("Unauthorized"));
    }

    loadAllProducts() {
        this.productService.getAll()
            .pipe(first())
            .subscribe(products => this.products = products);
    }
}