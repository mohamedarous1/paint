import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import {factoryShape} from "./factoryShape";
import { HitCanvas } from 'konva/cmj/Canvas';
import { Conditional } from '@angular/compiler';
import {request} from './request'
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
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
      this.http.edit_pos_sizeRequest(this.selected).subscribe(e=>{});
      console.log(this.hashmap[id]);
      console.log(this.GetNewSizeAndPosition());
    });

    this.selected.on('dragend' , (e:any) => {
      this.http.edit_pos_sizeRequest(this.selected).subscribe(e=>{});
      console.log(this.GetNewSizeAndPosition());
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
    // this.selected.width( this.selected.width()* this.selected.scaleX()) ;
    // this.selected.height(  this.selected.height()*this.selected.scaleY()) ;
    // this.selected.scaleX(1);
    // this.selected.scaleY(1);

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
    var temp =  this.shape.shapecreator(ShapeType, this.id.toString()).get();
    if(this.fill && ShapeType != "line"){
      temp.fill("color");
    }
    this.layer.add(temp);
    this.id = this.id +1;
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
    this.stage.on('click', (e:any)=>this.OnClickOnStageBrush(e));
  }
  OnClickOnStageBrush(ee:any)
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

    this.stage.on('mouseup touchend', function () {
      isPaint = false;
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
    SelectedShape.fill(color);
    console.log(color);
  }
  DeleteShape(){
    this.ClearEventListeners();
    this.AddFillEventListener();
    this.stage.on('click', (e:any) => this.DeleteSelectedShape(e));
  }
  DeleteSelectedShape(e:any){
    let id = e.target.attrs.id;
    let ClickedShape = e.target;
    if(id==undefined){
      return;
    }
    this.tr.nodes([]);
    ClickedShape.destroy();
    this.ClearEventListeners();
  }

}
