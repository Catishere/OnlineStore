import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login/login.component';
import { LoginBarComponent } from './login-bar/login-bar.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import {
  IgxDialogModule, IgxIconModule,
  IgxInputGroupModule, IgxButtonModule,
  IgxAvatarModule, IgxToggleModule, IgxDropDownModule, IgxRippleModule
} from 'igniteui-angular';
import {AlertModule} from "../alert/alert.module";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthenticationRoutingModule,
    IgxToggleModule,
    IgxRippleModule,
    IgxDialogModule,
    IgxInputGroupModule,
    IgxIconModule,
    IgxAvatarModule,
    IgxButtonModule,
    IgxDropDownModule,
    AlertModule
  ],
  declarations: [
    LoginBarComponent,
    LoginComponent,
    RegisterComponent,
    LoginDialogComponent,
    ProfileComponent
  ],
  providers: [],
  exports: [
    LoginBarComponent,
    LoginComponent,
    RegisterComponent,
    LoginDialogComponent,
    ProfileComponent
  ]
})
export class AuthenticationModule { }
