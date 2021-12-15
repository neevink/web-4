import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PointService {

  constructor() { }

  addPoint(x: number, y:number, r:number){
    console.log('Adding new points:', x, y, r);
  }
}
