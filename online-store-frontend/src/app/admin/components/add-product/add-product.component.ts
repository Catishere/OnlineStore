import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Product, ProductService} from "../../../store";

@Component({
    selector: 'app-add-product',
    templateUrl: './add-product.component.html',
    styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {

    public addProductForm: FormGroup;
    public alert_message: string;
    public alert_status: string;

    constructor(
        private router: Router, fb: FormBuilder,
        private productService: ProductService
    ) {
        this.addProductForm = fb.group({
            name: ['', Validators.required],
            type: ['', Validators.required],
            image: ['', Validators.required],
            description: ['', Validators.required],
            price: ['', Validators.required],
            quantity: ['', [Validators.required, Validators.min(0)]]
        });
    }

    ngOnInit() {
    }

    async onSubmit() {
        let product: Product = await this.productService.add(this.addProductForm.value);
        if (product.id == null) {
            this.alert_status = "error";
            this.alert_message = "This product already exists!";
        } else {
            this.alert_status = "success";
            this.alert_message = "Product added!";
        }
        this.addProductForm.reset();
    }
}
