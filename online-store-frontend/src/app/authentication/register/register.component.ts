import { Router } from '@angular/router';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Component, EventEmitter, Output } from '@angular/core';

import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';
import { Register } from '../models/register';
import {UserApiService} from "../../store/services/user-api.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  name: string;
  email: string;
  username: string;
  password: string;

  public registrationForm: FormGroup;

  @Output() viewChange: EventEmitter<any> = new EventEmitter();
  @Output() registered: EventEmitter<any> = new EventEmitter();

  constructor(private authentication: AuthenticationService,
              private fb: FormBuilder,
              private userService: UserService,
              private router: Router,
              private userApiService: UserApiService) {
    this.registrationForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.nullValidator],
      email: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  async tryRegister() {
    const response = await this.authentication.register(this.registrationForm.value as Register);
    if (!response.error) {
        console.log(response.user.username);
      const userInfo = await this.userApiService.findByUsername(response.user.username);
      this.userService.setMerge(response.user, userInfo.user);

      this.router.navigate(['/profile']);
      this.registered.emit();
    } else {
      alert(response.error);
    }
  }

  showLoginForm() {
    this.viewChange.emit();
  }
}
