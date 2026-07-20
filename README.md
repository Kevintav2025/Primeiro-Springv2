# 🚀 API Geradora de Senhas com Medidor de Força - V2

Esta é a segunda versão da API REST desenvolvida em **Java** com **Spring Boot** para geração de senhas seguras e aleatórias. A grande evolução dessa versão foi a estruturação dos dados de retorno em formato **JSON** e a criação de um **sistema de pontuação** para medir a força da senha.

---

## 🛠️ O que mudou na Versão 2?

* **Retorno Estruturado (JSON):** A API agora responde com uma classe de objeto (`SenhaResponse`), entregando os dados padronizados no formato chave/valor.
* **Medidor de Força Dinâmico:** Implementação de uma lógica baseada em regras que distribui pontos conforme o tamanho da senha e o uso de números ou símbolos. A senha é classificada em: `Fraca`, `Media`, `Forte` ou `Muito Forte`.
* **Validações de Segurança:** Filtros que impedem senhas menores que 8 ou maiores que 50 caracteres, além de impedir que o usuário desative todas as opções.

---

## 📋 Como testar as requisições

A API escuta na porta local através do endpoint `/gerar-senha`. Você pode passar os parâmetros na URL para customizar sua senha:

| Parâmetro | Tipo | Padrão | Descrição |
| :--- | :---: | :---: | :--- |
| `tamanho` | int | 8 | Quantidade de caracteres (Mín: 8 / Máx: 50) |
| `maiuscula` | boolean | true | Ativa letras maiúsculas |
| `minuscula` | boolean | true | Ativa letras minúsculas |
| `numero` | boolean | true | Ativa números |
| `simbolos` | boolean | true | Ativa símbolos especiais |
### 🔹 Exemplo de URL de teste (Navegador):
```http
http://localhost:8080/gerar-senha?tamanho=12&numero=true&simbolos=true
