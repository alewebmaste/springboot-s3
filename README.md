# ☁️ Projeto: Upload de Arquivos com Spring Boot + AWS S3

Este projeto é uma aplicação Java com Spring Boot que realiza **upload**, **download**, **listagem** e **deleção** de arquivos em um bucket da AWS S3. Desenvolvido com as melhores práticas: segurança com `DefaultCredentialsProvider`, configuração externa via `application.yml` e integração limpa com o AWS SDK v2.

---

## 🚀 Funcionalidades

- ✅ Upload de arquivos via REST (multipart)
- 📥 Download direto do S3 com Content-Type dinâmico
- 📁 Listagem de objetos no bucket
- ❌ Remoção de arquivos específicos
- 🔐 Credenciais seguras com `DefaultCredentialsProvider`

---

## 🛠️ Tecnologias usadas

- Java 17+
- Spring Boot
- Maven
- AWS SDK for Java v2
- Amazon S3
- Postman / curl (para testes)

---

## ⚙️ Configuração do projeto

### `application.yml`

```yaml
aws:
  region: sa-east-1
  bucket: java-arquivos-ale
