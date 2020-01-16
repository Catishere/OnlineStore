import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StoreComponent} from "./store.component";
import {ProductPageComponent} from "./product-page/product-page.component";
import {ProductComponent} from "./product/product.component";
import {
    IgxAvatarModule,
    IgxButtonModule,
    IgxCardModule,
    IgxDialogModule,
    IgxLayoutModule,
    IgxRippleModule
} from "igniteui-angular";
import {StoreRoutingModule} from "./store-routing.module";

@NgModule({
  declarations: [
    StoreComponent,
    ProductPageComponent,
    ProductComponent
  ],
    imports: [
        CommonModule,
        StoreRoutingModule,
        IgxLayoutModule,
        IgxCardModule,
        IgxButtonModule,
        IgxRippleModule,
        IgxAvatarModule,
        IgxDialogModule
    ],
  exports: [
    StoreComponent,
    ProductPageComponent,
    ProductComponent
  ]
})
export class StoreModule { }
