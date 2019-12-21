import { NgModule } from '@angular/core';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {
  IgxLayoutModule, IgxRippleModule,
  IgxNavigationDrawerModule, IgxNavbarModule, IgxCardModule, IgxButtonModule, IgxInputGroupModule, IgxTabsModule
} from 'igniteui-angular';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import {AuthenticationModule, AuthGuard} from './authentication';
import { StoreModule } from "./store/store.module";
import { AdminModule } from "./admin/admin.module";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {AuthInterceptor} from "./authentication/services/authentication.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
        FormsModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        // NOTE: `AuthenticationModule` defines child routes, must be imported before root `AppRoutingModule`
        AuthenticationModule,
        StoreModule,
        AdminModule,
        AppRoutingModule,
        IgxNavigationDrawerModule,
        IgxNavbarModule,
        IgxLayoutModule,
        IgxRippleModule,
        IgxCardModule,
        IgxButtonModule,
        ReactiveFormsModule,
        IgxInputGroupModule,
        IgxTabsModule
      ],
      providers: [
          { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
      bootstrap: [AppComponent]
})
export class AppModule {}
