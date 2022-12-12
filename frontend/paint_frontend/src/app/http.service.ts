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
    var temp = JSON.stringify(shape.attrs);

    return this.http.get('http://localhost:8080/create/' + ShapeType +
      "/" + temp, {responseType: 'text'});
  }

  edit_pos_sizeRequest(shape: any) :Observable<any>{
    var temp = JSON.stringify(shape.attrs);
    return this.http.get('http://localhost:8080/ResizeAndChangePosition/' + temp,{responseType : 'text'});
  }

edit_posRequest(shape : any):Observable<any>{
  var temp = JSON.stringify(shape.attrs);
  return this.http.get('http://localhost:8080/edit_pos/' + temp,{responseType : 'text'});
}

fillRequest(shape : any):Observable<any>{
  var temp = JSON.stringify(shape.attrs);
  return this.http.get('http://localhost:8080/FillColor/' + temp,{responseType : 'text' });
}


strokeRequest(shape : any):Observable<any>{
  var temp = JSON.stringify(shape.attrs);
  return this.http.get('http://localhost:8080/stroke/' + temp,{responseType : 'text'});
  
}

deleteRequest(shape : any){
  var temp = JSON.stringify(shape.attrs);
  return this.http.get('http://localhost:8080/DisableShape/' + temp,{responseType : 'text'});
}

undoRequest():Observable<any>{
    return this.http.get('http://localhost:8080/Undo' ,{responseType : 'text'});
    }

    redoRequest():Observable<any>{
      return this.http.get('http://localhost:8080/redo/' ,{responseType : 'text'});
    }

}
