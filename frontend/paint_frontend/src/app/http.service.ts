import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }


  CreateRequest(shape: any, ShapeType:string):Observable<any>
  {
    var temp = this.UpdateJsonForFillError(shape);
    console.log(11111);
    return this.http.get('http://localhost:8080/create/' + ShapeType +
      "/" + temp, {responseType: 'text'});
  }

  edit_pos_sizeRequest(shape: any) :Observable<any>{

    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ResizeAndChangePosition/' + temp
      ,{responseType : 'text'});
  }

  edit_posRequest(shape : any):Observable<any>{
    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ChangePositionLine/' + temp,{responseType : 'text'});
  }

  fillRequest(shape : any ):Observable<any>
  {
    let temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ChangeFillColor/' + temp,{responseType : 'text' });
  }

  strokeColorRequest(shape : any):Observable<any> {
    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ChangeStrokeColor/' + temp, {responseType: 'text'});
  }

  strokeWidthRequest(shape : any):Observable<any> {
    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ChangeStrokeWidth/' + temp, {responseType: 'text'});
  }

  deleteRequest(shape : any){
    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/DisableShape/' + temp,{responseType : 'text'});
  }

  undoRequest():Observable<any>{
    return this.http.get('http://localhost:8080/Undo' ,{responseType : 'text'});
  }

  redoRequest():Observable<any>{
    return this.http.get('http://localhost:8080/Redo' ,{responseType : 'text'});
  }

  UpdateJsonForFillError(shape : any)
  {
    var temp = JSON.stringify(shape.attrs);
    var temp2 = JSON.parse(temp);
    if (temp2.hasOwnProperty("fill") == true)
    {
      temp2["fill"] = shape.fill().slice(1);
    }
    if (temp2.hasOwnProperty("points") == true)
    {
      temp2["points"] = "";
    }
    temp = JSON.stringify(temp2);
    return temp;
  }

  savedemo():Observable<any>
  {
    return this.http.get('http://localhost:8080/save'  ,{responseType : 'text'});
  }

  saveXml(namefile:string):Observable<any>
  {
    return this.http.get("http://localhost:8080/save/"+namefile);
  }

  loadXml(namefile:string):Observable<any>{
    return this.http.get("http://localhost:8080/load/"+namefile, {responseType : 'text'});
  }


CreateLineRequest(shape:any)
  {
    var temp = JSON.stringify(shape);
    return this.http.post('http://localhost:8080/CreateLine', shape.attrs);
  }

  ClearService()
  {
    return this.http.get("http://localhost:8080/clear", {responseType : 'text'});
  }
}
