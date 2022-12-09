import { Component, OnInit } from '@angular/core';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import {factoryShape} from "./factoryShape";



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


  constructor() { }

  ngOnInit(): void {
    
    this.stage = new Konva.Stage({  
      container: 'container',
      width: window.innerWidth,
      height: window.innerHeight
    });
    
    this.layer = new Konva.Layer;
    this.stage.add(this.layer);
    
    this.layer.add(this.shape.shapecreator("squ", "15").get());
   console.log("fgfg");
   var tr  = new Konva.Transformer();
    this.layer.add(tr);
    var temp =  this.stage.findOne('#'+"15");
    tr.nodes([temp]);
  }


  create(){
    var  gfh = new Konva.RegularPolygon({
      x: 156,
      y: 145,
      sides: 3,
      radius: Math.abs(50),
      fill: "yellow",
      stroke: "red",
      strokeWidth: 30,
      id : "51"
    });
    this.layer.add(gfh);
  }

  


    
}
