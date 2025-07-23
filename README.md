# ☁️ Spring Boot + AWS S3 | Gestão de Arquivos

Aplicação Java com Spring Boot para gerenciamento de arquivos em um bucket da AWS S3. Suporta **upload**, **download**, **listagem** e **remoção** via API REST, com segurança, escalabilidade e deploy automatizado via **GitHub Actions + Docker + EC2**.

---

## 🚀 Funcionalidades

- ✅ Upload de arquivos (`multipart/form-data`)
- 📥 Download direto do S3 com `Content-Type` dinâmico
- 📁 Listagem de objetos no bucket
- ❌ Remoção de arquivos específicos
- 🔐 Autenticação segura via `DefaultCredentialsProvider`
- 🐳 Deploy automatizado com GitHub Actions + EC2

---

## 🛠️ Tecnologias

| Camada         | Tecnologias                          |
|----------------|--------------------------------------|
| Backend        | Java 17+, Spring Boot                |
| Build          | Maven                                |
| Cloud Storage  | AWS S3, AWS SDK for Java v2          |
| Containerização| Docker                               |
| CI/CD          | GitHub Actions, Amazon EC2 (Linux)   |
| Testes         | Postman, curl                        |

---



## ⚙️ Configuração

### `application.yml`

```yaml
aws:
  region: sa-east-1
  bucket: java-arquivos-ale
```

## 📘 Licença

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e contribuir.

---

## 👨‍🚀 Autor

**Alexandre Webmaste**  
Desenvolvedor Java | Cloud Enthusiast | DevOps Explorer  
[GitHub](https://github.com/alewebmaste) • [LinkedIn](https://www.linkedin.com/in/borbabackend/)

