import { Injectable } from '@angular/core';
import { UserToken } from "../models/user-token";
import { HttpClient } from "@angular/common/http";
import { shareReplay } from "rxjs";
import { environment } from "src/environments/environment";

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

  doRegister(user: String, pass: String){
    const obs = this.register(user, pass);
    obs.subscribe(
      this.setSession,
      console.error,
      console.log);
    return obs;
  }

  register(username: String, password: String){
    return this.http.post<UserToken>(`${environment}/register`, {username, password}).pipe( shareReplay() );
  }

  login(username: String, password: String) {
    return this.http.post<UserToken>(`${environment}/login`, {username, password});
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
