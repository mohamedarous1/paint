import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import {factoryShape} from "./factoryShape";
import { HitCanvas } from 'konva/cmj/Canvas';
import { Conditional } from '@angular/compiler';
import { HttpClient } from '@angular/common/http';
import {last, Observable} from "rxjs";
import {HttpService} from "../http.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  stage:any;
  layer:any;
  shape :any =  new factoryShape();
  id : number = 1;
  fill : boolean = false ;
  color : string = "black";
  selected : any;
  tr1 : any;
  tr2: any;
  BruchColor:string="black";
  newtemp:string = "#ffffff";
  namefile:string = "File1.xml";


  hashmap:any = new Map();
  constructor(public http  : HttpService) { }

  ngOnInit(): void {
    this.ClearAllShapes();
    this.stage = new Konva.Stage({
      container: 'container',
      width: 1370,
      height: 600
    });

    this.layer = new Konva.Layer;
    this.stage.add(this.layer);

    this.tr1 = new Konva.Transformer({
      rotateEnabled:false,
      keepRatio: true,
      enabledAnchors: [
        'top-left',
        'top-right',
        'bottom-left',
        'bottom-right',
      ],
    });
    this.tr2 = new Konva.Transformer({
      rotateEnabled:false,
    });

    this.layer.add(this.tr1);
    this.layer.add(this.tr2);

    this.stage.on('click',  (e:any) => this.OnClickOnStageSelect(e));
  }

  SelectButtonClick()
  {
    this.ClearEventListeners();
    this.stage.on('click', (e:any) => this.OnClickOnStageSelect(e));
  }
  OnClickOnStageSelect(e:any)
  {
    let id = e.target.attrs.id;
    let ClickedShape = e.target;

    if(id == undefined)
    {
      this.RemoveSelection();
      return;
    }
    this.SelectShape(ClickedShape);

    // This part is for requesting from backend
    this.selected.on('transformend', (e:any) =>
    {
      //this.RepairDimentions(this.selected);

      this.newtemp = ClickedShape.fill();
      this.http.edit_pos_sizeRequest(this.selected).subscribe(e=>{});
    });

    this.selected.on('dragend' , (e:any) => {
      //this.RepairDimentions(this.selected);
      this.http.edit_pos_sizeRequest(this.selected).subscribe(e=>{});
    });
  }
  SelectShape(SelectedShape:any)
  {
    this.RemoveSelection();
    this.selected = SelectedShape;
    this.selected.draggable(true);

    let id:number = SelectedShape.id();
    let ShapeType:any = this.hashmap[id];

    if (ShapeType == "Circle" || ShapeType == "Square")
      this.tr1.nodes([SelectedShape]);
    else
      this.tr2.nodes([SelectedShape]);
  }

  GetNewSizeAndPosition()
  {

    let arr =
      [this.selected.x(), this.selected.y(), this.selected.height(),
        this.selected.width()];
    return arr;
  }

  RemoveSelection()
  {
    if (this.selected == undefined) return;

    this.selected.draggable(false);
    this.tr1.nodes([]);
    this.tr2.nodes([]);
    this.selected = undefined;
  }

  create(ShapeType : string)
  {
    var temp =  this.shape.shapecreator(ShapeType, "500").get();
    if(ShapeType != "Line"){
      temp.fill("#FFFFFF");
    }
    this.layer.add(temp);

    this.SelectButtonClick();
    this.CreateRequest(temp, ShapeType);
  }

  BrushClick()
  {
    this.ClearEventListeners();
    this.OnClickOnStageBrush();
  }

  UpdateIdAndPutInMapAndSelectShape(Shape:any, ShapeType:string, id:any)
  {
    Shape.id(id.toString());
    this.hashmap[id] = ShapeType;
    this.SelectShape(Shape);
  }

  OnClickOnStageBrush()
  {
    let brushcolor = document.getElementById("favcolor");
    brushcolor?.addEventListener('input', (e:any)=>this.BrushColorValue(e));
    var isPaint = false;
    var mode = 'brush';
    var lastLine : any;
    this.stage.on('mousedown touchstart',  (e:any) =>
    {
      isPaint = true;
      var pos = this.stage.getPointerPosition();
      lastLine = new Konva.Line({
        x:pos.x + 0.0000001,
        y:pos.y + 0.0000001,
        stroke: this.BruchColor,
        id:"5",
        scaleX:1.001,
        scaleY:1.001,
        strokeWidth: 5.00001,
        globalCompositeOperation:
          mode === 'brush' ? 'source-over' : 'destination-out',
        lineCap: 'round',
        lineJoin: 'round',
        points: [0, 0, 0, 0],
      });

      this.layer.add(lastLine);
    });

    this.stage.on('mouseup touchend',  () => {
      isPaint = false;
      this.CreateLine(lastLine);
    });


    // and core function - drawing
    this.stage.on('mousemove touchmove',  (e:any) => {
      if (!isPaint) {
        return;
      }

      // prevent scrolling on touch devices
      e.evt.preventDefault();

      const pos = this.stage.getPointerPosition();
      var newPoints = lastLine.points().concat([pos.x - lastLine.x(), pos.y - lastLine.y()]);
      lastLine.points(newPoints);
    });

  }

  CreateRequest(Shape:any, ShapeType:string)
  {
    this.http.CreateRequest(Shape, ShapeType)
      .subscribe(id => {this.UpdateIdAndPutInMapAndSelectShape(Shape, ShapeType, id)});
  }

  CreateCustomLine()
  {
    var line =  this.shape.shapecreator("Line", "500").get()
    this.layer.add(line);
    this.CreateLine(line);
  }

  CreateLine(Shape:any)
  {
    this.http.CreateLineRequest(Shape)
      .subscribe(id => {this.UpdateIdAndPutInMapAndSelectShape(Shape, "Line", id)});
  }

  BrushColorValue(e:any){
    this.BruchColor = e.target.value;
  }

  ClearEventListeners()
  {
    let ColorBtn = document.getElementById("favcolor");
    ColorBtn?.removeEventListener('input', (e:any)=>this.ColorValue(e));

    this.RemoveSelection();
    this.stage.off("click");
    this.stage.off('mousedown touchstart');
    this.stage.off('mousemove touchmove');
    this.stage.off('mouseup touchend');
  }

  fillshape()
  {
    this.stage.on('click',(e:any)=>this.ColorValue(e));
  }
  ColorValue(evt:any)
  {
    if (this.selected == undefined) return;
    let color:string = this.newtemp;
    this.ColorShape(this.selected, color);
  }
  ColorShape(SelectedShape:any, color:string)
  {
    this.selected.fill(color);
    this.http.fillRequest(SelectedShape  ).subscribe(e=>{});
  }

  DeleteShape(){
    this.ClearEventListeners();
    //this.AddFillEventListener();
    this.stage.on('click', (e:any) => this.DeleteSelectedShape(e));
  }
  DeleteSelectedShape(e:any){
    let id = e.target.attrs.id;
    let ClickedShape = e.target;
    if(id==undefined)
      return;

    this.RemoveSelection();
    this.http.deleteRequest(ClickedShape).subscribe(e=>{});
    ClickedShape.hide();
    this.ClearEventListeners();
  }
  copy()
  {
    this.ClearEventListeners();
    this.stage.on('click',(e:any)=>this.CopyShape(e));
  }
  CopyShape(e:any){
    let id = e.target.attrs.id;
    let shape = e.target;
    if(id=undefined)return;
    let CopiedShape = shape.clone();
    CopiedShape.x(50).y(100);
    this.layer.add(CopiedShape);
    this.ClearEventListeners();
  }

  undo()
  {
    this.SelectButtonClick();
    var temp
    this.http.undoRequest().subscribe(response => {
      temp = JSON.parse(response);
      this.UpdateShapeWithJson(temp);
    })

  }

  save()
  {
    //this.http.savedemo().subscribe(e=>{});
    this.http.saveXml(this.namefile).subscribe(e=>{});
  }

  load()
  {
    this.ngOnInit();
    let jsonStr = "";
    this.http.loadXml(this.namefile).subscribe((e)=>{
          console.log(e);
          jsonStr = e;
          if (jsonStr === "{\"root\":\"\"}")
          {
            return;
          }

          let shapes = JSON.parse(jsonStr)["root"]["shape"];

          const size = Object.keys(shapes).length;

          if (size === 1)
          {
            return;
          }

          for(let shape of shapes)
          {
            let type = shape["ShapeType"];
            let id = shape["id"].toString();
            shape["fill"] = "#"+shape["fill"];
            let konvaShape = this.shape.shapecreator(type, id).get();
            for(const key in shape)
            {
                let value = shape[key];
                if(key === "ShapeType" || key === "id")
                  continue;

                konvaShape.attrs[key] = value;
            }
            this.layer.add(konvaShape);

            if (type == "Line")
              this.CreateLine(konvaShape);
            else
              this.CreateRequest(konvaShape, type);
          }


    });

  }

  redo()
  {
    this.SelectButtonClick();
    var temp
    this.http.redoRequest().subscribe(respone => {
      temp = JSON.parse(respone);
      this.UpdateShapeWithJson(temp);
    })
  }

  UpdateShapeWithJson(temp:any)
  {
    temp = this.UpdateFillFromBackEnd(temp);
    if (temp["OperationType"] == "EmptyOperation")
    {
      return;
    }

    let shape = this.stage.findOne("#"+temp.id.toString());

    if(temp["OperationType"] == "DisableShapeOperation")
    {
      shape.hide();
    }
    else if (temp["OperationType"] == "EnableShapeOperation")
    {
      shape.show();
    }
    else if(temp["OperationType"]== "ChangeFillColorOperation")
    {
      shape.fill(temp["fill"]);
    }
    else if (temp["OperationType"] == "RescaleAndChangePositionOperation")
    {
      shape.x(temp["x"]);
      shape.y(temp["y"]);
      shape.scaleX(temp["scaleX"]);
      shape.scaleY(temp["scaleY"]);
    }
  }

  UpdateFillFromBackEnd(temp:any)
  {
    if (temp.hasOwnProperty("fill"))
    {
      temp["fill"] = "#" + temp["fill"];
    }
    return temp;
  }

  ClearAllShapes()
  {
    this.http.ClearService().subscribe();
  }
}
