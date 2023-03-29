import { GPSComponent } from './gps/gps.component';
import { EquipmentComponent } from './equipment/equipment.component';

import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HistoryStateComponent } from './equipment-state/history.state.component';

const routes: Routes = [
  {path: '', component:EquipmentComponent},
  {path: 'state', component: HistoryStateComponent},
  {path: 'position', component: GPSComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
