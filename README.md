<h1 align="center"> Api-Controle de Equipamentos </h1>

# 📁 Acesso ao projeto

**Baixe os arquivos do projeto na sua máquina**

# 🛠️ Abrir e rodar o projeto

**Entrar no arquivo application.properties**
**Editar na primeira linha onde está escrito /estagiodb, substituir pelo nome do seu banco de dados**
**Não se esqueça de alterar o login e a senha que estão nas linhas 2 e 3 respectivamente**

# Metodos

<p>A api possui uma rota com todos os metodos http para cada entidade</p>

- `GET`: Lista todos os objetos
- `GET{id}`: Lista o objeto especificado pelo ID no corpo da requisição
- `POST`: Adiciona no banco de dados o objeto enviado no corpo da requisição
- `DELETE{id}`: Deleta o objeto especificado pelo ID no corpo da requisição
- `PUT{id}`: Atualiza o elemento relativo ao ID enviado no corpo da requisição

# Rotas

<p>/equipment_models</p>
<p>/equipment_model_state_hourly_earnings</p>
<p>/equipment_position_history</p>
<p>/equipments</p>
<p>/equipment_state_history</p>
<p>/equipment_states</p>

# Exceções

<p>O projeto ainda possui exceções referentes a deletar ou atualizar objetos que não existem
