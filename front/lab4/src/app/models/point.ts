export class Point {
  constructor(
    public x: number,
    public y: number,
    public r: number,
    public color: string,
    public result: boolean,
    public user: {username: string}){
  }
}
