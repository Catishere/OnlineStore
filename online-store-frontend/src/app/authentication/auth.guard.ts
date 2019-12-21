import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserService } from './services/user.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private router: Router, private userService: UserService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
       let currUser = this.userService.currentUser;
        if (currUser
          && (route.data.role == null
            || currUser.roles.indexOf(route.data.role) >= 0))
        {
            return true;
        }
        this.router.navigate([''], { queryParams: { returnUrl: state.url } });
        return false;
    }
}
