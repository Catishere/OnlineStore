import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpHeaders} from '@angular/common/http';
import {UserService} from "./user.service";
import {environment} from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
    constructor(private userService: UserService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const currentUser = this.userService.currentUser;
        if (currentUser && currentUser.token) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Basic ${currentUser.token}`
                }
            })
        }
        request = request.clone({
            responseType: "json",
            withCredentials: true
        });
        return next.handle(request);
    }
}
