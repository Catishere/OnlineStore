import { Component, OnInit } from '@angular/core';

import {Product, User} from '@/_models';
import { AuthenticationService } from '@/_services';
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "@/_services/product.service";
import {first} from "rxjs/operators";

@Component({ templateUrl: 'productpage.component.html' })
export class ProductPageComponent implements OnInit {
    public product: Product;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private productService: ProductService
    ) {
        this.product = new Product();
    }

    ngOnInit() {
        let id = this.route.snapshot.paramMap.get('id');
        this.productService.getProductById(id)
            .pipe(first())
            .subscribe(
                data => {
                    this.product = data;
                },
            error => {
                    this.router.navigate(['/error'])
                });
    }
}