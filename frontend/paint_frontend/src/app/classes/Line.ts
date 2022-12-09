import { Shape } from "./Shape";

export class Line extends Shape{
    
    pointsArray: number[];
    
    constructor(id:number, strokeWidth?:number, 
        enabled?:boolean, strokeColor?:string, pointsArray?:number[]){
        super(id, strokeWidth, enabled, strokeColor);
        this.pointsArray = pointsArray || [];
    }

    override draw(layer:any): void {
        
    }


}