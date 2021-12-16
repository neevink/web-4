import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  getUsername(){
    return localStorage.getItem('login');
  }


  logout(){
    this.authService.logout();
    localStorage.removeItem('login');
    console.log('Выход');
    this.router.navigate(['/login']);
  }

}
