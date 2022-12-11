import {HttpClient, HttpHandler} from '@angular/common/http';
import {KonvaModule} from "ng2-konva";
import {KonvaComponent} from "ng2-konva";
import { Konva } from "konva/cmj/_FullInternals";
import {Observable} from "rxjs";

export class request{

    constructor(public http : HttpClient){
    }



    edit_pos_sizeRequest(shape: any){

          var temp = shape
          var jas = shape.toJSON()
          console.log(jas);
          this.http.get('http://localhost:8080/edit_posandsize',{
            responseType:'text',
            params:{
                shape:jas,
                id : shape.getAttr("id")
            },
            observe:'response'
          })
          .subscribe(response=>{
            console.log(response.body + "");
          })

    }


}
