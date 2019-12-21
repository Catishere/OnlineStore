import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/product"
import {Router} from "@angular/router";
import {EventService} from "../services/event.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  private _onSale: boolean = false;
  private discount: string;

  constructor(private router: Router,
              private eventService: EventService) {
  }

  public get isSale(): boolean {
    return this._onSale;
  }

  ngOnInit() {
    if (this.product.specialPrice != null) {
      this.discount = (100 - (this.product.specialPrice / this.product.price)).toString() + "%";
      this._onSale = true;
    }
  }

  openProductPage(id: number) {
    this.router.navigate(['/products/' + id])
  }
}
