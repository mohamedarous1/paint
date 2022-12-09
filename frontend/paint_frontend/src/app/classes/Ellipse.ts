import { ClosedShape } from "./ClosedShape";
import { LWSize } from "./LWSize";
import { Position } from "./Position";

export class Ellipse extends ClosedShape{
    constructor(id:number, size:LWSize, strokeWidth?:number, 
        enabled?:boolean, strokeColor?:string, 
        fillColor?:string, shapePosition?: Position){

            super(id, size, strokeWidth,
                enabled, strokeColor, fillColor, shapePosition);

    }

    override setSize(size:LWSize){
        this.size = size;
    }
}