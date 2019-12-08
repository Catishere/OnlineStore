import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { appRoutingModule } from '@/app.routing';
import { JwtInterceptor, ErrorInterceptor } from '@/_helpers';
import { AppComponent } from '@/app.component';
import { HomeComponent } from '@/_components/home';
import { LoginComponent } from '@/_components/login';
import { RegisterComponent } from '@/_components/register';
import { AlertComponent } from '@/_components/alert';
import {StoreComponent} from "@/_components/store";
import {ProfileComponent} from "@/_components/profile";
import {AddProductComponent} from "@/_components/store/addproduct/addproduct.component";

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        appRoutingModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        StoreComponent,
        ProfileComponent,
        AlertComponent,
        AddProductComponent
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    ],
    bootstrap: [AppComponent]
})
export class AppModule { };