# Deployment Guide for Spring Boot Application with MySQL

This guide will help you deploy your Spring Boot application on Render.com and Clever Cloud for free.

## Prerequisites
- A Java 11+ environment
- Maven installed
- MySQL database
- Git installed
- A GitHub repository for your Spring Boot application

## Step 1: Setting Up Your MySQL Database
1. **Create a MySQL database:**  
Go to [MySQL Database](https://www.clever-cloud.com/en/). Choose the free plan and create a new database. Make a note of the database name, username, and password.

2. **Update your `application.properties`:** 
   Add your database credentials to your `src/main/resources/application.properties` file:
   
   ```properties
   spring.datasource.url=jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>
   spring.datasource.username=<DB_USERNAME>
   spring.datasource.password=<DB_PASSWORD>
   ```

## Step 2: Deploying on Render.com
1. **Sign up for Render:**  
   Go to [Render.com](https://render.com) and sign up for an account.
   
2. **Create a new web service:**  
   - Click on the “New” button in the upper left corner, then select “Web Service.”
   - Connect your GitHub account and choose your repository.

3. **Configure the service:**  
   - Set the **Environment** to `Java`. 
   - Set the **Build Command** to `./mvnw clean package`.
   - Set the **Start Command** to `java -jar target/<your-jar-file>.jar`.

4. **Environment Variables:**  
   Add the necessary environment variables such as your MySQL credentials:
   - `DB_HOST`
   - `DB_DATABASE`
   - `DB_USERNAME`
   - `DB_PASSWORD`

5. **Deploy:**  
   Click on the **Create Web Service** button. Your app will be built and deployed automatically.

## Step 3: Deploying on Clever Cloud
1. **Sign up for Clever Cloud:**  
   Go to [Clever Cloud](https://www.clever-cloud.com) and sign up for a free account.
   
2. **Create a new application:**  
   - Select `Java` as the runtime and click on **Create Application**.
   - Connect your GitHub repository and select your repository.
   
3. **Environment Variables:**  
   Set up the same environment variables for your MySQL credentials.
   
4. **Deploy:**  
   Click on the deploy button and your Spring Boot application will be deployed.

## Conclusion
Your Spring Boot application is now deployed on Render.com and Clever Cloud. You can access it using the provided URLs. Be sure to monitor the logs for any potential issues during the deployment process.