import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-equipment',
  templateUrl: './equipment.component.html',
})
export class EquipmentComponent {

  EquipmentArray : any[] = [];
  isResultLoaded = false;
  isUpdateFormActive = false;

  equipmentName: string ="";
  equipmentState: string ="";
  equipmentModel: string ="";
  earningsPerHour: Number =0;
  historyState: string ="";
  currentEquipmentID = "";


  constructor(private http: HttpClient )
  {
    this.getAllEquipment();
  }

  getAllEquipment()
  {

    this.http.get("http://localhost:8080/api/v1/equipment/getAllEquipment")

    .subscribe((resultData: any)=>
    {

        console.log(resultData);
        this.EquipmentArray = resultData;
    });
  }

  register()
  {

    let bodyData = {
      "equipmentId" : this.currentEquipmentID,
      "equipmentName" : this.equipmentName,
      "equipmentState" : this.equipmentState,
      "equipmentModel" : this.equipmentModel,
      "earningsPerHour" : this.earningsPerHour,
      "historyState" : this.historyState
    };
    this.http.post("http://localhost:8080/api/v1/equipment/save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Equipment Registered Successfully");
        this.getAllEquipment();

        this.equipmentName = '';
        this.equipmentState = '';
        this.equipmentModel = '';
        this.earningsPerHour = 0;
        this.historyState  = '';
    });
  }

  setUpdate(data: any)
  {
    this.currentEquipmentID = data.equipmentId;
    this.equipmentName = data.equipmentName;
    this.equipmentState = data.equipmentState;
    this.equipmentModel = data.equipmentModel;
    this.earningsPerHour = data.earningPerHour;
    this.historyState  = data.historyState;


  }
  UpdateRecords()
  {
    let bodyData = {
      "equipmentId" : this.currentEquipmentID,
      "equipmentName" : this.equipmentName,
      "equipmentState" : this.equipmentState,
      "equipmentModel" : this.equipmentModel,
      "earningsPerHour" : this.earningsPerHour,
      "historyPosition" : this.historyState,

    };

    this.http.put("http://localhost:8080/api/v1/equipment/update",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Equipment Registered Updated")
        this.getAllEquipment();

        this.equipmentName = '';
        this.equipmentState = '';
        this.equipmentModel = '';
        this.earningsPerHour = 0;
        this.historyState  = '';

    });
  }
  save()
  {
    if(this.currentEquipmentID == '')
    {
        this.register();
    }
      else
      {
       this.UpdateRecords();
      }
  }
  setDelete(data: any)
  {


    this.http.delete("http://localhost:8080/api/v1/equipment/deleteEquipment/"+ data.equipmentId,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Equipment Deleted")
        this.getAllEquipment();

        this.equipmentName = '';
        this.equipmentState = '';
        this.equipmentModel = '';
        this.earningsPerHour = 0;
        this.historyState  = '';
    });
  }


}
