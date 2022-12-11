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
        radius: Math.abs(20),
        fill: "black",
        strokeWidth: 3,
        draggable: false,
        id: id

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
      width:70,
      height : 70,
      fill: "black",
      strokeWidth: 3,
      draggable: false,
      id: id

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
      radiusX: 100,
      radiusY: 50,
      strokeWidth: 4,
      fill: "black",
      draggable : false,
      id: id

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
      height:50,
      width:50,
      strokeWidth: 3,
      draggable: false,
      fill: "black",
      id: id,
      rotation:0

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
        sides: 3,
        radius: Math.abs(200),
        strokeWidth: 3,
        draggable: false,
        fill: "black",
        id : id
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
        lineCap:"round",
        x: 50,
        y: 100,
        strokeWidth: 3,
        points:[50,100,20,20],
        draggable : false,
        stroke:"black",
        id : id
      });
    }

    public get(): any {
      return this.structure;
    }
}
