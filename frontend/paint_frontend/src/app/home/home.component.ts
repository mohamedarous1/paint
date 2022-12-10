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
  constructor(public http  : HttpClient) { }

  ngOnInit(): void {
    
    this.stage = new Konva.Stage({  
      container: 'container',
      width: window.innerWidth,
      height: window.innerHeight
    });
    
    this.layer = new Konva.Layer;
    this.stage.add(this.layer);
    
    this.layer.add(this.shape.shapecreator("squ", "15").get());
    //this.selected = this.shape.shapecreator("cir" , "-1").get();
    //this.selected.visible("false");
    var tr  = new Konva.Transformer();
    this.layer.add(tr);
    var temp ;


    this.stage.on('mousedown', (e: any) => {
      if(e.target == this.stage){
        tr.nodes([]);
        return;
      }
      this.selected = this.stage.findOne("#" + e.target.id());
      this.selected.draggable("true");
      tr.nodes([this.selected]);
      
    });


    this.stage.on('mousemove', (e : any) => {
      //this.selected = this.stage.findOne(e.target.id());
      
      
    });

    this.stage.on('mouseup', (e :any ) => {
      return; 
    });

    
  }

  check_fill(){
    if(this.fill){
      this.fill = false;
      
    }else{
      this.fill = true;
    }
  }

  create(s : string){
    var temp =  this.shape.shapecreator(s, this.id.toString()).get();
    if(this.fill && s != "line"){
      temp.fill("color");
    }
    this.layer.add(temp);
    this.id = this.id +1;
  }

  


    
}
