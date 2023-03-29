import { HttpClient } from '@angular/common/http';
import { Component, Injectable} from '@angular/core';

@Component({
  selector: 'app-equipment-state',
  templateUrl: './history.state.component.html',


})
export class HistoryStateComponent {

  EquipmentArray : any[] = [];
  isResultLoaded = false;
  isUpdateFormActive = false;

  State: string ="";
  Position: string ="";

  currentEquipmentID= '';

  constructor(private http: HttpClient )
  {

  }

}
