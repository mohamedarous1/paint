import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import { TitleStrategy } from '@angular/router';

export class factoryShape{

  public shapecreator(s:string  , id : string)
  {
    if(s == 'Circle')
      return new circle( id);
    else if ( s == 'Ellipse')
      return new ellipse(id);
    else if ( s ==  'Rectangle' )
      return new rectangle( id);
    else if (s == 'Square')
      return new square( id);
    else if (s ==  'Line' )
      return new line( id);
    else if (s == "Triangle")
      return new triangle( id);
    else
      return null;
  }
}


export interface Shape {
  structure :any;
  get(): any;
}


class circle implements Shape {

  structure;

  constructor(id : string) {

    this.structure = new Konva.Circle({
      x: 50,
      y: 100,
      scaleX:1,
      scaleY:1,
      radius: Math.abs(20),
      stroke:"black",
      strokeWidth: 2,
      draggable: false,
      id: id,
      fill:"#FFFFFF",

    });
  }

  public get(): any {
    return this.structure;

  }
}


class rectangle implements Shape {

  structure;

  constructor(id : string) {

    this.structure = new Konva.Rect({
      x: 50,
      y: 100,
      scaleX:1,
      scaleY:1,
      width:100,
      height : 70,
      stroke:"black",
      strokeWidth: 2,
      draggable: false,
      id: id,
      fill:"#FFFFFF",

    });
  }

  public get(): any {
    return this.structure;

  }
}


class ellipse implements Shape {
  structure;

  constructor(id : string) {

    this.structure = new Konva.Ellipse({
      x: 200,
      y: 100,
      scaleX:1,
      scaleY:1,
      radiusX: 100,
      radiusY: 50,
      stroke:"black",
      strokeWidth: 2,
      draggable : false,
      id: id,
      fill:"#FFFFFF",

    });
  }

  public get(): any {
    return this.structure;

  }
}

class square implements Shape {

  structure;

  constructor(id : string) {

    this.structure = new Konva.Rect({
      x: 200,
      y: 200,
      scaleX:1,
      scaleY:1,
      height:50,
      width:50,
      draggable: false,
      stroke:"black",
      strokeWidth: 2,
      id: id,
      rotation:0,
      fill:"#FFFFFF",

    });
  }

  public get(): any {
    return this.structure;

  }
}

export class triangle implements Shape {

  structure;

  constructor(id : string) {

    this.structure = new Konva.RegularPolygon({
      x: 50,
      y: 100,
      scaleX:1,
      scaleY:1,
      sides: 3,
      radius: Math.abs(50),
      stroke:"black",
      strokeWidth: 2,
      draggable: false,
      id : id,
      fill:"#FFFFFF",
    });
  }

  public get(): any {
    return this.structure;
  }
}

class line implements Shape {

  structure;

  constructor(id : string) {

    this.structure = new Konva.Line({
      x:20 + 0.0000001,
      y:30 + 0.0000001,
      stroke: "#000000",
      id:"5",
      scaleX:1.001,
      scaleY:1.001,
      strokeWidth: 5.00001,
      globalCompositeOperation: 'source-over',
      lineCap: 'round',
      lineJoin: 'round',
      points: [0, 0, 50, 20],
    });


  }

  public get(): any {
    return this.structure;
  }
}
