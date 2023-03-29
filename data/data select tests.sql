/*select * from operation.equipment;*/
/*select * from operation.equipment_model;*/
/*select * from operation.equipment_model_state_hourly_earnings;*/
/*select * from operation.equipment_position_history;*/
/*select * from operation.equipment_state;*/
/*select * from operation.equipment_state_history;*/
/* select distinct on (equipment_id) equipment_id, date, equipment_state_id from operation.equipment_state_history order by equipment_id, date desc; 
*/
select distinct on (equipment_id) equipment_id, date, lat, lon from operation.equipment_position_history order by equipment_id, date desc;