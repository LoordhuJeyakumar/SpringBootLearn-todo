# 🔐 Environment Variables Explained

This guide explains how to manage the "Secrets" for your application. We use environment variables so your database passwords aren't saved in your source code.

---

### 1. In the Cloud (Render Dashboard)
When you deploy to Render, you will see an **"Environment"** tab in your service dashboard. 

**Steps:**
1. Go to your Web Service on Render.
2. Click **Environment** on the left menu.
3. Click **Add Environment Variable**.
4. Add these pairs:
   - **Key**: `DB_HOST` | **Value**: (your TiDB Host)
   - **Key**: `DB_USERNAME` | **Value**: (your TiDB User)
   - **Key**: `DB_PASSWORD` | **Value**: (your TiDB Password)
   - **Key**: `DB_NAME` | **Value**: `todo_db`
   - **Key**: `DB_PORT` | **Value**: `4000`

---

### 2. Locally (On your Computer)
If you want to test a different database locally WITHOUT changing your code, you can set variables in your terminal before running the app.

**On Linux/Mac:**
```bash
export DB_PASSWORD=my_secret_pass
export DB_HOST=localhost
./mvnw spring-boot:run
```

**On Windows (Powershell):**
```powershell
$env:DB_PASSWORD="my_secret_pass"
$env:DB_HOST="localhost"
./mvnw spring-boot:run
```

---

### 4. What about `.env` files?
Many developers use a `.env` file to store their local settings. 

**How to set it up:**
1. Create a file named `.env` in your project root.
2. Inside, add your local settings:
   ```env
   DB_HOST=localhost
   DB_USERNAME=root
   DB_PASSWORD=John@1710#
   ```
3. To make Spring Boot read this file locally, you can use the **Spring Boot Helper** in your IDE or add a library, but the **easiest way** for now is to use the "Environment Variables" field in your IDE's run configuration.

> [!CAUTION]
> **CRITICAL SECURITY RULE**: Never push your `.env` file to GitHub! If you do, your passwords will be stolen. I have already added `.env` to your `.gitignore` file to protect you.
