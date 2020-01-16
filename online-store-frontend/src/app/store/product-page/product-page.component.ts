import { Component, OnInit } from '@angular/core';
import {Product} from "../models";
import {ProductService} from "..";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../authentication";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {

    public product: Product;
    public quantity: number;

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private service: ProductService,
      public userService: UserService) { }

    async ngOnInit() {
      let id = this.route.snapshot.paramMap.get('id');
      console.log(id);
      this.product = await this.service.getProductById(id);
      console.log(this.product.name);
    }

    public onEditDialogOKSelected(event) {
        console.log(this.product.id);
        event.dialog.close();
    }

    public async onBuyDialogOKSelected(event) {
        console.log(this.product.id);
        try {
            let res = await this.service.buy(this.product.id, this.quantity);
        } catch (e) {
            console.log(e);
        }

        event.dialog.close();
        location.reload();
    }

    public async onDeleteDialogOKSelected(event) {
        console.log(this.product.id);
        try {
            let res = await this.service.delete(this.product.id);
        } catch (e) {
            console.log(e);
        }

        event.dialog.close();
        this.router.navigate(['/home']);
    }
}
