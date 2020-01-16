import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from "./admin-routing.module";
import {AuthenticationModule, AuthGuard} from "../authentication";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../authentication/services/authentication.interceptor";
import {AddEventComponent} from "./components/add-event/add-event.component";
import {AddProductComponent} from "./components/add-product/add-product.component";
import {AddUserComponent} from "./components/add-user/add-user.component";
import {AdminComponent} from "./admin.component";
import {EditProductComponent} from './components/edit-product/edit-product.component';
import {IgxButtonModule, IgxInputGroupModule, IgxRippleModule, IgxSelectModule, IgxTabsModule} from "igniteui-angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AlertModule} from "../alert/alert.module";



@NgModule({
  declarations: [
    AddEventComponent,
    AddProductComponent,
    AddUserComponent,
    AdminComponent,
    EditProductComponent,
    AddUserComponent
  ],
    imports: [
        CommonModule,
        AlertModule,
        AdminRoutingModule,
        IgxTabsModule,
        IgxInputGroupModule,
        ReactiveFormsModule,
        IgxButtonModule,
        IgxRippleModule,
        IgxSelectModule,
        FormsModule
    ],
  providers: [
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  exports: [
    AddEventComponent,
    AddProductComponent,
    EditProductComponent,
    AddUserComponent,
    AdminComponent
  ]
})
export class AdminModule { }
