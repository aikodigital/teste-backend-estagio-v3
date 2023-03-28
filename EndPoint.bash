POST /estados
RequestBody:
{
  "idEquipamento": 1,
  "estado": "Operando",
  "dataHora": "2022-03-28T10:00:00Z"
}


DELETE /estados/{id}

PUT /estados/{id}
RequestBody:
{
  "estado": "Parado",
  "dataHora": "2022-03-28T11:00:00Z"
}

GET /estados/{id}

GET /equipamentos/{idEquipamento}/estados

GET /equipamentos/{idEquipamento}/estado-atual
