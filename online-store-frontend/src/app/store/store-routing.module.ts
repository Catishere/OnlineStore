import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ProductPageComponent} from "./product-page/product-page.component";


const storeRoutes: Routes = [
  { path: 'products/:id', component: ProductPageComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(storeRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class StoreRoutingModule { }
