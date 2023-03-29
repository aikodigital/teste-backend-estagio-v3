import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EquipmentComponent } from './equipment/equipment.component';
import { HttpClientModule } from '@angular/common/http';
import { HistoryStateComponent } from './equipment-state/history.state.component';

@NgModule({
  declarations: [
    AppComponent,
    EquipmentComponent,
    HistoryStateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
