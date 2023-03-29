

import { HttpClient } from "@angular/common/http";
import { Component, Injectable, OnInit } from "@angular/core";

declare const L: any;

@Component({
  selector: 'app-gps',
  templateUrl: './gps.component.html',

})

@Injectable({
  providedIn: 'root'
})
export class GPSComponent implements OnInit{

  ngOnInit(): void {
    if (!navigator.geolocation){
      console.log('location is not supported')
      }
    navigator.geolocation.getCurrentPosition((position)=>{
      const coords = position.coords;
      const latlong = [coords.latitude, coords.longitude]
      console.log(`lat: ${position.coords.latitude}, lon: ${position.   coords.longitude}`);
      let map = L.map('map').setView(latlong, 13);

      L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
      }).addTo(map);

      let marker = L.marker(latlong).addTo(map);
    });
  }

  EquipmentArray: any[] = [];
  isResultLoaded = false;

  historyPosition: string = '';
  currentEquipmentID = '';



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
      "historyPosition" : this.historyPosition,
    };
    this.http.post("http://localhost:8080/api/v1/equipment/save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Equipment Registered Successfully");
        this.getAllEquipment();

        this.historyPosition = '';
    });
  }

}
