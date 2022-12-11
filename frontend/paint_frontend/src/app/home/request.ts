import {HttpClient, HttpHandler} from '@angular/common/http';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";

export class request{

    constructor(public http : HttpClient){
    }

    // createRequest(shape: any){
    //     var temp = shape.toJSON()
    //     console.log(temp);
    //     this.http.get('http://localhost:8080/create/'+temp , {responseType : 'text'})
    //     .subscribe(response=>{
    //         console.log(response.toString());
    //     })
    // }
    //
    //
    //




}
