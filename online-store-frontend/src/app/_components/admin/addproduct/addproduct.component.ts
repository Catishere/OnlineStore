import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService } from '@/_services';
import {ProductService} from "@/_services/product.service";

@Component({ selector: 'app-store-addproduct', templateUrl: 'addproduct.component.html' })
export class AddProductComponent implements OnInit {
    addProductForm: FormGroup;
    loading = false;
    submitted = false;
    //returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private productService: ProductService,
        private alertService: AlertService
    ) {
    }

    ngOnInit() {
        const URL_REGEXP = /^[A-Za-z][A-Za-z\d.+-]*:\/*(?:\w+(?::\w+)?@)?[^\s/]+(?::\d+)?(?:\/[\w#!:.?+=&%@\-/]*)?$/;

        this.addProductForm = this.formBuilder.group({
            name: ['', Validators.required],
            type: ['', Validators.required],
            price: ['', [Validators.required, Validators.min(0)]],
            description: ['', [Validators.maxLength(255)]],
            image: ['', [Validators.pattern(URL_REGEXP)]]
        });

        // get return url from route parameters or default to '/'
        //this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/store';
    }

    // convenience getter for easy access to form fields
    get field() { return this.addProductForm.controls; }

    onSubmit(formDirective: FormGroupDirective) {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.addProductForm.invalid) {
            return;
        }

        this.loading = true;
        this.productService.add(this.addProductForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success("Added");
                    this.loading = false;
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}
