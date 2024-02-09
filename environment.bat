@echo off
setx HRMS_SERVER_PORT "8080"

setx HRMS_DB_PROTOCOL "postgresql"
setx HRMS_DB_IP "127.0.0.1"
setx HRMS_DB_PORT "5432"
setx HRMS_DB_NAME "HRManagementSystem"
setx HRMS_DB_DRIVER "org.postgresql.Driver"
setx HRMS_DB_DIALECT "org.hibernate.dialect.PostgreSQLDialect"

setx HRMS_DB_USERNAME "postgres"
setx HRMS_DB_PASSWORD "1234"
setx HRMS_SECRET_KEY "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9"
setx HRMS_AUTH_TIME "600000"


setx GITHUB_CONFIG_PATH_PROJECT "https://github.com/dgnklz/..."
setx GITHUB_USERNAME "dgnklz"

@REM https://github.com/settings/tokens
setx GITHUB_TOKEN_KEY "blablabla"