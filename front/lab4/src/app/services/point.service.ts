import { Injectable } from '@angular/core';
import {Point} from "../models/point";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {shareReplay} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PointService {
  points: Point[] = this.getPoints();

  getPoints(){
    this.points = [];
    this.fetchPoints();
    //console.log(this.points);
    return this.points;
  }

  constructor(private http: HttpClient) {
    this.fetchPoints();
  }

  fetchPoints(){
    console.log("Fetching points...");
    this.http.get<Point[]>(`${environment.api}/points`)
      .subscribe(
        this.addPoints.bind(this),
        console.error,
        console.log
    );
  }

  postPoint(x: number, y: number, r: number){
    this.http.post<Point>(`${environment.api}/points`, {x, y, r}).pipe(shareReplay())
      .subscribe(
        data => {this.addPoint(data)},
        error => {console.log(error)}
    );
  }

  addPoint(p: Point){
    //console.log(p);
    p.x = parseFloat(parseFloat(p.x.toString()).toFixed(2));
    p.y = parseFloat(parseFloat(p.y.toString()).toFixed(2));
    p.r = parseFloat(parseFloat(p.r.toString()).toFixed(2));

    this.points.push(p);
    //console.log(p);
  }

  addPoints(ps: Point[]){
    //console.log(ps);
    //console.log(this);
    if(ps)
      ps.forEach((p)=>this.addPoint(p));
  }
}
