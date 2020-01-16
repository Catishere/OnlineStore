import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import {UserApiService} from "../../../store/services/user-api.service";
import {User} from "../../../authentication";

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

    public addUserForm: FormGroup;
    public alert_message: string;
    public alert_status: string;

    constructor(
        private router: Router, fb: FormBuilder,
        private userService: UserApiService
    ) {
        this.addUserForm = fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
            email: ['', Validators.required],
            picture: ['', Validators.required],
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            roles: ['', Validators.required],
        });
    }
    ngOnInit() {
    }

    async onSubmit() {
        this.addUserForm.value["roles"] = this.addUserForm.value["roles"].split(",");
        console.log(this.addUserForm.value);
        let user: User = await this.userService.add(this.addUserForm.value);
        if (user.username == null) {
            this.alert_status = "error";
            this.alert_message = "This user already exists!";
        }
        else {
            this.alert_status = "success";
            this.alert_message = "User added!";
        }
        this.addUserForm.reset();
    }
}
