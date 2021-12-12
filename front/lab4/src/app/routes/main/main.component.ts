import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  login='login'

  constructor() { }

  ngOnInit(): void {}

  changeLogin(){
    this.login = 'Kirill';
  }

}
