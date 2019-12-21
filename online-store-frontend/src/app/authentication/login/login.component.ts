import {Router} from '@angular/router';
import {FormBuilder, Validators, FormGroup} from '@angular/forms';
import {Component, EventEmitter, Output} from '@angular/core';

import {UserService} from '../services/user.service';
import {AuthenticationService} from '../services/authentication.service';
import {UserApiService} from "../../store/services/user-api.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    public loginForm: FormGroup;
    @Output() public viewChange: EventEmitter<any> = new EventEmitter();
    @Output() public loggedIn: EventEmitter<any> = new EventEmitter();
    /** expose to template */
    public errormsg: String;

    constructor(
        private authentication: AuthenticationService,
        private userService: UserService, private router: Router, fb: FormBuilder,
        private userApiService: UserApiService
    ) {
        this.loginForm = fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
        });
    }

    async tryLogin() {
        const response = await this.authentication.login(this.loginForm.value);
        if (!response.error) {
            this.userService.setMerge(this.userService.currentUser, response.user);

            this.router.navigate(['/profile']);
            this.loginForm.reset();
            // https://github.com/angular/angular/issues/15741
            Object.keys(this.loginForm.controls).forEach(key => {
                this.loginForm.controls[key].setErrors(null);
            });
            this.loggedIn.emit();
        } else {
            this.errormsg = response.error;
        }

    }

    showRegistrationForm() {
        this.viewChange.emit();
    }
}
