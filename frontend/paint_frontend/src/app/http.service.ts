import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }


  CreateRequest(shape: any, ShapeType:string):Observable<any>
  {
    var temp = this.UpdateJsonForFillError(shape);

    return this.http.get('http://localhost:8080/create/' + ShapeType +
      "/" + temp, {responseType: 'text'});
  }

  edit_pos_sizeRequest(shape: any) :Observable<any>{

    var temp = this.UpdateJsonForFillError(shape);
    return this.http.get('http://localhost:8080/ResizeAndChangePosition/' + temp,{responseType : 'text'});
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
    temp2["fill"] = shape.fill().slice(1);
    temp = JSON.stringify(temp2);
    return temp;
  }




}
