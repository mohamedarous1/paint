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
  tr : any;
  BruchColor:string="black";
  newtemp:string = "#ffffff";
  namefile:string = "";

  hashmap:any = new Map();
  constructor(public http  : HttpService) { }

  ngOnInit(): void {

    this.stage = new Konva.Stage({
      container: 'container',
      width: 1370,
      height: 600
    });

    this.layer = new Konva.Layer;
    this.stage.add(this.layer);

    this.tr  = new Konva.Transformer();
    this.layer.add(this.tr);

    this.stage.on('click',  (e:any) => this.OnClickOnStageSelect(e));
  }

  SelectButtonClick()
  {
    this.ClearEventListeners();
    this.AddFillEventListener();
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
    this.selected = SelectedShape;
    this.selected.draggable(true);
    this.tr.nodes([this.selected]);
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
    this.tr.nodes([]);
    this.selected = undefined;
  }

  create(ShapeType : string)
  {
    var temp =  this.shape.shapecreator(ShapeType, "500").get();
    if(ShapeType != "Line"){
      temp.fill("#FFFFFF");
    }
    this.layer.add(temp);
    this.selected = temp;
    this.tr.nodes([this.selected])

    this.SelectButtonClick();
    console.log(ShapeType);
    this.CreateRequest(temp, ShapeType);
  }

  CreateRequest(Shape:any, ShapeType:string)
  {
    console.log(Shape);
    this.http.CreateRequest(Shape, ShapeType)
      .subscribe(id => {this.UpdateIdAndPutInMap(Shape, ShapeType, id)});
  }

  UpdateIdAndPutInMap(Shape:any, ShapeType:string, id:number)
  {
    Shape.id(id);
    console.log(id);
    this.hashmap[id] = ShapeType;
  }

  BrushClick()
  {
    this.ClearEventListeners();
    this.OnClickOnStageBrush();
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
        stroke: this.BruchColor,
        id:"5",
        strokeWidth: 5,
        globalCompositeOperation:
          mode === 'brush' ? 'source-over' : 'destination-out',
        // round cap for smoother lines
        lineCap: 'round',
        lineJoin: 'round',
        // add point twice, so we have some drawings even on a simple click
        points: [pos.x, pos.y, pos.x, pos.y],
      });
      this.layer.add(lastLine);
    });

    this.stage.on('mouseup touchend',  () => {
      isPaint = false;
      this.http.HeshamService(lastLine).subscribe(e=>{console.log(e)});
    });

    // and core function - drawing
    this.stage.on('mousemove touchmove',  (e:any) => {
      if (!isPaint) {
        return;
      }

      // prevent scrolling on touch devices
      e.evt.preventDefault();

      const pos = this.stage.getPointerPosition();
      var newPoints = lastLine.points().concat([pos.x, pos.y]);
      lastLine.points(newPoints);
    });

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
  AddFillEventListener()
  {
    let colorbtn = document.getElementById("favcolor");
    colorbtn?.addEventListener('input', (e:any)=>this.ColorValue(e));
  }
  ColorValue(evt:any)
  {
    if (this.selected == undefined) return;
    let color:string = evt.target.value;
    this.ColorShape(this.selected, color);
  }
  ColorShape(SelectedShape:any, color:string)
  {
    this.selected.fill(color);
    console.log(color + "   "+ color.substring(1,7) + " "+ this.selected.fill());

    console.log(color);
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
    if(id==undefined){
      return;
    }
    this.tr.nodes([]);
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
      console.log(temp);
      this.UpdateShapeWithJson(temp);
    })

  }

  save()
  {
    this.http.savedemo().subscribe(e=>{});
  }

  redo()
  {
    this.SelectButtonClick();
    var temp
    this.http.redoRequest().subscribe(respone => {
      temp = JSON.parse(respone);
      console.log(temp);
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
}
