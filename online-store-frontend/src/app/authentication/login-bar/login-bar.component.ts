import {Router} from '@angular/router';
import {Component, OnInit, ViewChild} from '@angular/core';

import {UserService} from '../services/user.service';
import {LoginDialogComponent} from '../login-dialog/login-dialog.component';
import {IgxDropDownComponent, ISelectionEventArgs} from 'igniteui-angular';
import {LoginSelection} from "./login-selection";
import {AuthenticationService} from "../services/authentication.service";

@Component({
    selector: 'app-login-bar',
    templateUrl: './login-bar.component.html',
    styleUrls: ['./login-bar.component.scss']
})
export class LoginBarComponent implements OnInit {

    @ViewChild(LoginDialogComponent, {static: true}) loginDialog: LoginDialogComponent;
    @ViewChild(IgxDropDownComponent, {static: true}) igxDropDown: IgxDropDownComponent;

    public adminSelections: LoginSelection[] = [
        {label: "Profile", right: "ROLE_USER", route: "/profile"},
        {label: "Admin Panel", right: "ROLE_ADMIN", route: "/admin"},
        {label: "Log Out", right: "ROLE_USER", route: null}];

    public userSelections: LoginSelection[] = [
        {label: "Profile", right: "ROLE_USER", route: "/profile"},
        {label: "Log Out", right: "ROLE_USER", route: null}];

    constructor(public userService: UserService,
                private router: Router,
                private authService: AuthenticationService) {
        this.userService.init();
    }

    ngOnInit(): void {
    }

    openDialog() {
        this.loginDialog.open();
    }

    async handleLogout() {
        this.router.navigate(['/home']);
        this.userService.clearCurrentUser();
        console.log("Logout");
    }

    menuSelect(args: ISelectionEventArgs) {
        let route;
        if(this.userService.hasRole("ROLE_ADMIN"))
            route = this.adminSelections[args.newSelection.index].route;
        else if (this.userService.hasRole("ROLE_USER"))
            route = this.userSelections[args.newSelection.index].route;

        if (route == null)
            this.handleLogout();
        else
            this.router.navigate([route]);
    }
}
