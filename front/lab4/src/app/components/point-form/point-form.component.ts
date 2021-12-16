import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {PointService} from "../../services/point.service";
import {Point} from "../../models/point";

@Component({
  selector: 'app-point-form',
  templateUrl: './point-form.component.html',
  styleUrls: ['./point-form.component.css']
})
export class PointFormComponent implements OnInit {
  readonly xValues = [-4, -3, -2, -1, 0, 1, 2, 3, 4]; //{'-4','-3','-2','-1','0','1','2','3','4'} для координаты по оси X,
  readonly yMin = -3; // Text (-3 ... 3) для координаты по оси Y,
  readonly yMax = 3;
  readonly rValues = [-4, -3, -2, -1, 0, 1, 2, 3, 4]; // Select {'-4','-3','-2','-1','0','1','2','3','4'} для задания радиуса области

  x = new FormControl();
  y = new FormControl('', Validators.compose([
    Validators.min(-3),
    Validators.max(3)
  ]));
  r = new FormControl();

  submit(){
    this.pointService.postPoint(this.x.value, this.y.value, this.r.value);
  }

  reset(){
    this.x.reset();
    this.y.reset();
    this.r.reset();
  }

  constructor(private pointService: PointService) {}

  ngOnInit(): void {
  }

}
