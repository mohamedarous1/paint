import { ClosedShape } from "./ClosedShape";
import { Position } from "./Position";
import { RegularPolygonSize } from "./RegularPolygonSize";

export abstract class RegularPolygon extends ClosedShape{
    constructor(id:number, size:RegularPolygonSize, strokeWidth?:number,
        enabled?:boolean, strokeColor?:string,
        fillColor?:string, shapePosition?: Position){

            super(id, size, strokeWidth,
                enabled, strokeColor, fillColor, shapePosition);
    }

    override setSize(size:RegularPolygonSize){
        this.size = size;
    }
}
