export abstract class Shape{
    strokeWidth: number;
    id: number;
    enabled: boolean;
    strokeColor: string;

    constructor( id:number, strokeWidth?:number, enabled?:boolean, strokeColor?:string){
        this.id = id;
        this.strokeWidth = strokeWidth || 1;
        this.enabled = enabled || true;
        this.strokeColor = strokeColor || "FFFFFF";
    }

    initialize(jsonObject:any):void{
        this.strokeWidth = jsonObject.strokeWidth;
        this.id = jsonObject.id;
        this.enabled = jsonObject.enabled;
        this.strokeColor = jsonObject.strokeColor;
    }

    getStrokeColor():string{
        return this.strokeColor;
    }

    setStrokeColor(strokeColor:string):void{
        this.strokeColor = strokeColor;
    }

    getStrokeWidth():number{
        return this.strokeWidth;
    }

    setStrokeWidth(strokeWidth:number):void{
        this.strokeWidth = strokeWidth;
    }

    getId():number{
        return this.id;
    }

    setEnabled():void{
        //enables object
        this.enabled = true;
    }

    setDisabled():void{
        //disables object
        this.enabled = false;
    }

    draw(layer:any):void{}

    

}