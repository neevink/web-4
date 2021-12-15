import { Injectable } from '@angular/core';
import { UserToken } from "../models/user-token";
import { HttpClient } from "@angular/common/http";
import { shareReplay } from "rxjs";
import { environment } from "src/environments/environment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  doLogin(user: String, pass: String){
    const l = this.login(user, pass);
    l.subscribe(
      (data)=>this.setSession(data),
      console.error,
      console.log);
    return l;
  }

  doRegister(user: String, pass: String, router: Router){
    console.log(4445)
    const obs = this.register(user, pass)
      .subscribe(
        (data: any) => {
          alert(data.message);
          router.navigateByUrl('/');
        },
        error => alert("Ошибка авторизации " + error.error.message)
    );
    return obs;
  }

  register(username: String, password: String){
    return this.http.post<UserToken>(`${environment.api}/register`, {username, password}).pipe(shareReplay());
  }

  login(username: String, password: String) {
    return this.http.post<UserToken>(`${environment.api}/login`, {username, password});
  }

  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("expiresIn");
  }

  setSession(token: UserToken){
    localStorage.setItem('token', token.token);
    console.log(token.token);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return token != undefined;
  }
}
