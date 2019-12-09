import {Component, Input, OnInit} from '@angular/core';
import {Product} from "@/_models";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'product-card',
    templateUrl: 'product.component.html'
})
export class ProductComponent {

    @Input() product: Product;

    constructor(private router: Router) {}

    openProductPage(id: number) {
        this.router.navigate(['/product/' + id])
    }
}