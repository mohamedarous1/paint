import { Size } from "./Size";

export class RegularPolygonSize extends Size{
    
    constructor(parameters:{sideLength:number}){
        super();
        this.parameters = parameters;
    }

}