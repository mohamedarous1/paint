import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ScreenComponent } from './home/screen/screen.component';
import { ToolsComponent } from './home/tools/tools.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ScreenComponent,
    ToolsComponent,
  ],
  imports: [
    BrowserModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
