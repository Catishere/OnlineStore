import { Component, OnInit } from '@angular/core';
import {Product} from "../models";
import {ProductService} from "..";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {

    public product: Product;
    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private service: ProductService) { }

    async ngOnInit() {
      let id = this.route.snapshot.paramMap.get('id');
      console.log(id);
      this.product = await this.service.getProductById(id);
      console.log(this.product.name);
    }

}
