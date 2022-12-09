import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import { TitleStrategy } from '@angular/router';

export class factoryShape{
    shape :any
    public shapecreator(s:string  , id : string){
        switch (s) {
            case 'cir':
              return new circle( id);
            case 'rect':
              return new rectangle( id);
            case 'squ':
              return new square( id);
            case 'line':
              return new line( id);
            case 'tri':
              return new triangle( id);
            default:
              return new square(id);
          }
    }


}


export interface Shape {
    x: number
    y: number,
    width: number,
    height: number,
    fill: string,
    stroke: string,
    strokeWidth: number,
    draggable: boolean;
    get(): any;
}


class circle implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 20;
      this.fill = "red";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.Circle({
        x: 50,
        y: 100,
        radius: Math.abs(20),
        fill: "red",
        stroke: "yellow",
        strokeWidth: 3,
        draggable: true,
        id: id

      });
    }
    
    public get(): any {
      return this.structure;
  
    }
  }


  class rectangle implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 20;
      this.fill = "red";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.Rect({
        x: 50,
        y: 100,
        width:70,
        height : 70,
        fill: "red",
        stroke: "yellow",
        strokeWidth: 3,
        draggable: true,
        id: id

      });
    }
    
    public get(): any {
      return this.structure;
  
    }
  }



  class square implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id2 : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 20;
      this.fill = "red";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.Rect({
        x: 200,
        y: 200,
        width:70,
        height : 70,
        fill: "red",
        stroke: "yellow",
        strokeWidth: 3,
        draggable: true,
        id: id2
      });
    }
    
    public get(): any {
      return this.structure;
  
    }
  }



  class ellipse implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 40;
      this.fill = "red";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.Circle({
        x: 50,
        y: 100,
        width : this.width,
        height : this.height,
        fill: "red",
        stroke: "yellow",
        strokeWidth: 3,
        draggable: true,
        id: id

      });
    }
    
    public get(): any {
      return this.structure;
  
    }
  }


export class triangle implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 20;
      this.fill = "red";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.RegularPolygon({
        x: this.x,
        y: this.y,
        sides: 3,
        radius: Math.abs(this.width),
        fill: this.fill,
        stroke: this.stroke,
        strokeWidth: this.strokeWidth,
        id : id
      });
    }
    
    public get(): any {
      return this.structure;
    }
}

class line implements Shape {
    x; y; width; height; fill; stroke; strokeWidth; draggable;
    structure;
  
    constructor(id : string) {
      this.x = 50;
      this.y = 100;
      this.width = 20;
      this.height = 20;
      this.fill = "none";
      this.stroke = "yellow";
      this.strokeWidth = 3;
      this.draggable = true;
      this.structure = new Konva.Line({
        lineCap:"round",
        x: this.x,
        y: this.y,
        stroke: this.stroke,
        strokeWidth: this.strokeWidth,
        points:[this.x,this.y,this.width,this.height],
        id : id
      });
    }
    
    public get(): any {
      return this.structure;
    }
}