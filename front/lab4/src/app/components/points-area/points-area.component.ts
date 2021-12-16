import {Component, ElementRef, Input, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {Point} from "../../models/point";
import {PointService} from "../../services/point.service";

@Component({
  selector: 'app-points-area',
  templateUrl: './points-area.component.html',
  styleUrls: ['./points-area.component.css']
})
export class PointsAreaComponent implements OnInit {
  @Input() pointList!: Point[];
  _r: number = 1;

  @Input() get r(): number{
    return this._r;
  }

  set r(value: number){
    this._r = value;
    this.scaleCircles(this._r);
  }

  @ViewChildren('circle') circles!:QueryList<ElementRef>;
  @ViewChild('canvas') canvas!:ElementRef;
  constructor(private pointService: PointService) { }

  ngOnInit(): void {
  }

  checkPoint(e: MouseEvent){
    const rect = this.canvas.nativeElement.getBoundingClientRect();
    console.log(this.canvas.nativeElement)
    if(this._r) {
      const x = (e.clientX - rect.left - 200) * this._r / 160;
      if(x > 2 || x < -2){
        alert("X should be between -2 and 2");
        return;
      }
      const y = -(e.clientY - rect.top - 200) * this._r / 160;
      if(y < -5 || y > 5){
        alert("Y should be between -3 and 3");
        return;
      }

      this.pointService.postPoint(x, y, this._r);
    } else{
      alert("Please choose R before doing anything lewd.");
    }
  }

  scaleCircles(newR: number){
    this.circles?.forEach(circle=>{
      const newX = circle.nativeElement.dataset.x/newR;
      const newY = -circle.nativeElement.dataset.y/newR;
      console.log(`${newR} ${newX} ${-newY}`);
      circle.nativeElement.style = `transform: translate(${newX}px, ${newY}px)`;
    })
  }
}
