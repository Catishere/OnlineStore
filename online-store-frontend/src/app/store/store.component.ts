import { Component, OnInit } from '@angular/core';
import {Product, Event} from "./models";
import {ProductService} from "./services/product.service";
import {first} from "rxjs/operators";
import {EventService} from "./services/event.service";
import {forkJoin} from "rxjs";
import {UserService} from "../authentication";

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.scss']
})
export class StoreComponent implements OnInit {

  products: Product[];
  events: Event[];

  constructor(
    private productService: ProductService,
    private eventService: EventService,
    private userService: UserService
  ) {}


  ngOnInit() {
    this.loadEventsAndProducts();
  }

  async loadEventsAndProducts() {
      this.events = await this.eventService.getAll().toPromise();
      this.products = await this.productService.getAll().toPromise();
      this.applyDiscounts();
  }
  loadAllEvents() {
    this.eventService.getAll()
      .pipe(first())
      .subscribe(events => this.events = events);
  }
  loadAllProducts() {
    this.productService.getAll()
      .pipe(first())
      .subscribe(products => this.products = products);
  }

  //TODO: Fix
  private applyDiscounts() {
    console.log(this.events);
    if (this.events != null)
      this.events.forEach(event => {
        if (event.products != null)
          event.products.forEach((ev_product: Product) => {
            if (this.products != null)
              this.products.forEach((product: Product) => {
                if(product.id === ev_product.id) {
                  StoreComponent.applyDiscount(product, ev_product);
                }
              });
          });
      })
  }

  private static applyDiscount(product: Product, ev_product: Product) {
    product.specialPrice = ev_product.price;
  }
}

