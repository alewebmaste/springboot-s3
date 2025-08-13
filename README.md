# ☁️ Spring Boot + AWS | Gestão de Arquivos com Deploy Automatizado

[![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![AWS](https://img.shields.io/badge/AWS-S3%20%7C%20DynamoDB%20%7C%20Lambda-orange?logo=amazon-aws)](https://aws.amazon.com/)[![Docker](https://img.shields.io/badge/Docker-Containerized-blue?logo=docker)](https://www.docker.com/)
[![CI/CD](https://img.shields.io/badge/GitHub_Actions-Automated_Deploy-purple?logo=github-actions)](https://github.com/features/actions)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](LICENSE)


Aplicação Java com Spring Boot para gerenciamento de arquivos em um bucket da AWS S3.  
Suporta **upload**, **download**, **listagem** e **remoção** via API REST — agora com **persistência de metadados no DynamoDB**, integração com **lambda**,**deploy automatizado via GitHub Actions**, containerização com **Docker**, e monitoramento em tempo real com **CloudWatch Logs**.
---

## 🚀 Funcionalidades

- ✅ Upload de arquivos (`multipart/form-data`)
- 📥 Download direto do S3 com `Content-Type` dinâmico
- 📁 Listagem de objetos no bucket
- ❌ Remoção de arquivos específicos
- 🧾 Persistência de metadados no DynamoDB
- 🔍 Consulta de registros via chave primária
- ⚡ Integração com AWS Lambda para processamento assíncrono
- 🔐 Autenticação segura via `DefaultCredentialsProvider`
- 🐳 Deploy automatizado com GitHub Actions + EC2
- 📡 Logs em tempo real enviados para AWS CloudWatch

---

## 🛠️ Tecnologias Utilizadas

| Camada         | Tecnologias                          |
|----------------|--------------------------------------|
| Backend        | Java 17+, Spring Boot                |
| Build          | Maven                                |
| Cloud Storage  | AWS S3, AWS SDK for Java v2          |
| Banco NoSQL    | Amazon DynamoDB                      |
| Serverless     | AWS Lambda                           |
| Containerização| Docker                               |
| CI/CD          | GitHub Actions, Amazon EC2 (Linux)   |
| Monitoramento  | Amazon CloudWatch Logs               |
| Testes         | Postman, curl                        |                 |

---

## ⚙️ Configuração

### `application.yml`

```yaml
aws:
  region: sa-east-1
  bucket: java-arquivos-ale

```

## 🔄 Pipeline Automatizada

A aplicação conta com uma pipeline CI/CD completa, que garante deploy contínuo e monitoramento em tempo real:

```mermaid
graph TD
    A[Push no GitHub] --> B[GitHub Actions dispara workflow]
    B --> C[Build Maven: gera JAR]
    C --> D[Build Docker: imagem criada]
    D --> E[Push para Docker Hub]
    E --> F[SSH para EC2 via chave privada]
    F --> G[Pull da nova imagem]
    G --> H[Restart do container]
    H --> I[Aplicação no ar]
    I --> J[CloudWatch Agent coleta logs]
    J --> K[Logs visíveis no AWS CloudWatch]
```

## 📘 Licença

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e contribuir.

---

## 👨‍🚀 Autor

**Alexandre Webmaste**  
Desenvolvedor Java | Cloud Enthusiast | DevOps Explorer  
[GitHub](https://github.com/alewebmaste) • [LinkedIn](https://www.linkedin.com/in/borbabackend/)

