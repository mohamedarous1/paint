import { Position } from "./Position";
import { RegularPolygon } from "./RegularPolygon";
import { RegularPolygonSize } from "./RegularPolygonSize";

export class Square extends RegularPolygon{
    constructor(id:number, size:RegularPolygonSize, strokeWidth?:number, 
        enabled?:boolean, strokeColor?:string, 
        fillColor?:string, shapePosition?: Position){

            super(id, size, strokeWidth, 
                enabled, strokeColor, fillColor, shapePosition);

    }

    
}