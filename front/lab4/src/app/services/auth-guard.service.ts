import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {
  // Класс не допускает пользователя к страницам, если он не авторизован

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(): boolean {
    if (!this.auth.isAuthenticated()) {
      console.log("Access denied: not authorized");
      this.router.navigate(['login']);
      return false;
    }
    console.log("Access allowed");
    return true;
  }
}
