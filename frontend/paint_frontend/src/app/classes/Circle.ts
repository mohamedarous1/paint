import { CircleSize } from "./CircleSize";
import { ClosedShape } from "./ClosedShape";
import { Position } from "./Position";

export class Circle extends ClosedShape{
    constructor(id:number, size:CircleSize, strokeWidth?:number, 
        enabled?:boolean, strokeColor?:string, 
        fillColor?:string, shapePosition?: Position){

            super( id, size, strokeWidth, 
                enabled, strokeColor, fillColor, shapePosition);

    }

    override setSize(size:CircleSize):void{
        this.size = size;
    }

}