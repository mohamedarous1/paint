import { Position } from "./Position";
import { Shape } from "./Shape";
import { Size } from "./Size";

export abstract class ClosedShape extends Shape{

    fillColor:string;
    shapePosition: Position;
    size:Size;

    constructor(id:number, size:Size, strokeWidth?:number, 
        enabled?:boolean, strokeColor?:string, 
        fillColor?:string, shapePosition?: Position){
        
        super(id, strokeWidth, enabled, strokeColor);

        //size is mandatory
        this.size = size;

        this.fillColor = fillColor || "FFFFFF";
        this.shapePosition = shapePosition || new Position(0,0);
        

    }

    setSize(size:Size):void{
        this.size = size;
    }


}