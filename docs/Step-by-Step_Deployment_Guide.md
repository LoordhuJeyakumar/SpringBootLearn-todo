# 🚀 Step-by-Step Free Deployment Guide

This guide will help you deploy your Spring Boot Todo application completely for free using **Render** and **TiDB Cloud**. 

**Prerequisites:**
- A [GitHub](https://github.com/) account.
- **No Credit Card Required!**

> [!TIP]
> **Confused about Environment Variables?** Read my [Environment Variables Explained](file:///home/loordhujeyakumar/Downloads/startSpring/docs/ENVIRONMENT_VARIABLES_EXPLAINED.md) guide first to understand how they keep your app secure.

---

### Phase 1: Create your Free Database (TiDB Cloud)
Render doesn't offer a free MySQL database, so we will use **TiDB Cloud**. It is 100% compatible with your MySQL project.

1.  Go to [TiDB Cloud](https://pingcap.com/products/tidb-cloud) and sign up (Email/Google/GitHub).
2.  Click **Create Cluster** and choose **Serverless** (Free).
3.  Choose your preferred region (e.g., AWS / N. Virginia).
4.  Once created, click **Connect**.
5.  **Important**: Save these details!
    - **Host**: (e.g., `gateway01.us-east-1.prod.aws.tidbcloud.com`)
    - **Port**: `4000` (TiDB uses 4000, which works with your MySQL driver).
    - **User**: (e.g., `xxxxxx.root`)
    - **Password**: (Your password).
    - **Database**: `todo_db` (or create a new one).

---

### Phase 2: Push your Code to GitHub
Render needs to "see" your code to build it.

1.  Go to GitHub and create a **New Repository** (Private is recommended).
2.  In your terminal (inside the project folder), run:
    ```bash
    git init
    git add .
    git commit -m "Bootstrap production deployment"
    git remote add origin YOUR_GITHUB_REPO_URL
    git push -u origin main
    ```

---

### Phase 3: Deploy to Render
This is where your code becomes a live website.

1.  Go to [Render.com](https://render.com/) and sign up.
2.  Click **New +** -> **Web Service**.
3.  Connect your GitHub repository.
4.  **Settings**:
    - **Name**: `todo-backend`
    - **Environment**: `Docker` (Render will automatically find your `Dockerfile`).
    - **Instance Type**: `Free` (512MB RAM).
5.  **Environment Variables** (The most important part!):
    Click **Advanced** -> **Add Environment Variable** for each of these:
    - `DB_HOST`: (From Phase 1)
    - `DB_PORT`: `4000`
    - `DB_NAME`: `todo_db`
    - `DB_USERNAME`: (From Phase 1)
    - `DB_PASSWORD`: (From Phase 1)
    - `PORT`: `5000`
6.  Click **Create Web Service**.

---

### Phase 4: Verify your Live API
1.  Render will take about 2-3 minutes to build your Docker image and start the server.
2.  Once the status turns **Live**, copy your URL (e.g., `https://todo-backend.onrender.com`).
3.  Test your public health check by visiting:
    `https://YOUR_URL.onrender.com/api/health`
4.  If you see `{"status":"UP"}`, **Congratulations!** Your distributed backend is live and free.

---

### 💡 Pro-Tip: Cold Starts
Because this is a free account, Render will "sleep" if no one uses your site for 15 minutes. The next time you visit, it might take **60 seconds** to wake up. This is normal!
