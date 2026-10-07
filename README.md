# Employee Management - DevOps CI/CD Project

A production-style DevOps practice project demonstrating:

- Java Spring Boot application
- MySQL database
- Maven build and unit testing
- SonarQube code quality analysis
- Docker images for application and database
- Docker Compose for multi-container deployment
- Jenkins CI/CD pipeline
- Docker image scanning with Trivy
- Build artifact (JAR)

## Architecture

GitHub
   |
   v
Jenkins
   |
   +--> Checkout
   +--> Maven Build
   +--> Unit Tests
   +--> SonarQube Code Quality
   +--> Package JAR Artifact
   +--> Build Docker App Image
   +--> Build MySQL DB Image
   +--> Trivy Security Scan
   +--> Docker Compose Up
   |
   +--> Spring Boot Container ---> MySQL Container

## Project structure

```text
devops-employee-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/employee/
│   │   │   ├── EmployeeManagementApplication.java
│   │   │   ├── controller/EmployeeController.java
│   │   │   ├── model/Employee.java
│   │   │   ├── repository/EmployeeRepository.java
│   │   │   └── service/EmployeeService.java
│   │   └── resources/application.properties
│   └── test/
│       └── java/com/example/employee/EmployeeServiceTest.java
├── db/
│   ├── Dockerfile
│   └── init.sql
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── pom.xml
├── .dockerignore
├── .gitignore
└── README.md
```

## Run locally

Requirements:
- Java 17+
- Maven 3.9+
- Docker
- Docker Compose

Build:

```bash
mvn clean package
```

Run the complete stack:

```bash
docker compose up -d --build
```

Check:

```bash
docker compose ps
```

Application:

```text
http://localhost:8080
```

API:

```text
GET    /api/employees
GET    /api/employees/{id}
POST   /api/employees
PUT    /api/employees/{id}
DELETE /api/employees/{id}
```

Example POST:

```json
{
  "name": "Sai Kumar",
  "email": "sai@example.com",
  "department": "DevOps"
}
```

## Stop

```bash
docker compose down
```

To remove the database volume too:

```bash
docker compose down -v
```

## Jenkins setup

Install Jenkins plugins:

- Git
- Pipeline
- Docker Pipeline
- Maven Integration
- SonarQube Scanner
- Credentials Binding

Configure:
- GitHub repository credentials
- Docker Hub credentials
- SonarQube server
- Docker access for Jenkins

The `Jenkinsfile` contains the pipeline stages:

1. Checkout
2. Build
3. Unit Test
4. SonarQube Code Quality
5. Archive Artifact
6. Build App Docker Image
7. Build DB Docker Image
8. Security Scan
9. Docker Compose Deploy

## GitHub

Create a GitHub repository, then:

```bash
git init
git add .
git commit -m "Initial DevOps project"
git branch -M main
git remote add origin https://github.com/<YOUR-USERNAME>/devops-employee-management.git
git push -u origin main
```

Replace `<YOUR-USERNAME>` with your GitHub username.

## Important

The Jenkinsfile assumes Jenkins is running on a Linux host where Docker is available. The Jenkins user must be allowed to communicate with Docker.

For a course/demo environment, this can be done with:

```bash
sudo usermod -aG docker jenkins
sudo systemctl restart jenkins
```

Do not blindly use this configuration in a production environment; use your organization's approved Jenkins/Docker security model.
