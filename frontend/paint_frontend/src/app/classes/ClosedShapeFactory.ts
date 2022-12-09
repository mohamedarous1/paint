import { Circle } from "./Circle";
import { ClosedShape } from "./ClosedShape";
import { Ellipse } from "./Ellipse";
import { Position } from "./Position";
import { Rectangle } from "./Rectangle";
import { Size } from "./Size";
import { Square } from "./Square";

export class ClosedShapeFactory{
    createClosedShape(
        type:string, id:number, size:Size, 
        parameters:{
            strokeWidth?:number, 
            enabled?:boolean, 
            strokeColor?: string,
            fillColor?: string, 
            position?:Position,
        }
    ):ClosedShape|null{
        switch (type) {
            case "circle":
                return new Circle(
                    id,
                    size,
                    parameters?.strokeWidth,
                    parameters?.enabled,
                    parameters?.strokeColor,
                    parameters?.fillColor,
                    parameters?.position,
                );
            case "rectangle":
                return new Rectangle(
                    id,
                    size,
                    parameters.strokeWidth,
                    parameters.enabled,
                    parameters.strokeColor,
                    parameters.fillColor,
                    parameters.position,
                );
            case "ellipse":
                return new Ellipse(
                    id, 
                    size,
                    parameters.strokeWidth,
                    parameters.enabled,
                    parameters.strokeColor,
                    parameters.fillColor,
                    parameters.position,
                );
            case "square":
                return new Square(
                    id, 
                    size,
                    parameters.strokeWidth,
                    parameters.enabled,
                    parameters.strokeColor,
                    parameters.fillColor,
                    parameters.position,
                );
            default:
                return null;
        }
    }
}