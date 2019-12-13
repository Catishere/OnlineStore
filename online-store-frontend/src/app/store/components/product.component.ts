import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/product"
import {Router} from "@angular/router";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  constructor(private router: Router) {}

  ngOnInit() {
  }

  openProductPage(id: number) {
    this.router.navigate(['/product/' + id])
  }
}
