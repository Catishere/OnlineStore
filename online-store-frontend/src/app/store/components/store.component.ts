import { Component, OnInit } from '@angular/core';
import {Product} from "../models/product";
import {ProductService} from "../services/product.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.scss']
})
export class StoreComponent implements OnInit {

  products: Product[];
  constructor(
    private productService: ProductService
  ) {}


  ngOnInit() {
    this.loadAllProducts();
  }

  deleteProduct(id: number) {
    this.productService.delete(id)
      .pipe(first())
      .subscribe(data =>
      {
        this.loadAllProducts()
      }, error => {
          console.log(error);
      })
  }

  loadAllProducts() {
    this.productService.getAll()
      .pipe(first())
      .subscribe(products => this.products = products);
  }

}
