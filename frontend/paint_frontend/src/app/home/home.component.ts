import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import {factoryShape} from "./factoryShape";
import { HitCanvas } from 'konva/cmj/Canvas';
import { Conditional } from '@angular/compiler';
import {request} from './request'
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  stage:any;
  layer:any;
  drawMode : boolean  = false;
  freeDraw : boolean  = false;
  shape :any =  new factoryShape();
  id : number = 1;
  fill : boolean = false ;
  color : string = "black";
  request : any = new request(this.http);
  selected : any;
  tr : any ;
  isselected : boolean = true ;
  constructor(public http  : HttpClient) { }

  ngOnInit(): void {

    this.stage = new Konva.Stage({
      container: 'container',
      width: 1200,
      height: 600
    });

    this.layer = new Konva.Layer;
    this.stage.add(this.layer);


    this.tr  = new Konva.Transformer();
    this.layer.add(this.tr);

    console.log(this.isselected);


    this.stage.on('click',  (e:any) => this.OnClickOnStageSelect(e));


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
      console.log(this.GetNewSizeAndPosition());
    });

    this.selected.on('dragend' , (e:any) => {
      console.log(this.GetNewSizeAndPosition());
    });
  }

  GetNewSizeAndPosition()
  {
    this.selected.width( this.selected.width()* this.selected.scaleX()) ;
    this.selected.height(  this.selected.height()*this.selected.scaleY()) ;
    this.selected.scaleX(1);
    this.selected.scaleY(1);

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
  }

  SelectShape(SelectedShape:any)
  {
    this.selected = SelectedShape;
    this.selected.draggable(true);
    this.tr.nodes([this.selected]);
  }

  check_fill()
  {
    if(this.fill){
      this.fill = false;

    }else{
      this.fill = true;
    }
  }

  create(s : string)
  {
    var temp =  this.shape.shapecreator(s, this.id.toString()).get();
    if(this.fill && s != "line"){
      temp.fill("color");
    }
    this.layer.add(temp);
    this.id = this.id +1;
    this.selected = temp;
    this.tr.nodes([this.selected])
    this.SelectButtonClick();
  }

  OnClickOnStageBrush(ee:any)
  {
    var isPaint = false;
    var mode = 'brush';
    var lastLine : any;

    this.stage.on('mousedown touchstart',  (e:any) => {
      isPaint = true;
      var pos = this.stage.getPointerPosition();
      lastLine = new Konva.Line({
        stroke: '#df4b26',
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

  BrushClick()
  {
    this.RemoveSelection();
    this.ClearStageEventListeners();
    this.stage.on('click', (e:any)=>this.OnClickOnStageBrush(e));
  }

  SelectButtonClick()
  {
    this.ClearStageEventListeners();
    this.stage.on('click', (e:any) => this.OnClickOnStageSelect(e));
  }

  ClearStageEventListeners()
  {
    this.stage.off("click");
    this.stage.off('mousedown touchstart');
    this.stage.off('mousemove touchmove');
    this.stage.off('mouseup touchend');
  }

  select()
  {
    this.SelectButtonClick();
  }



}
