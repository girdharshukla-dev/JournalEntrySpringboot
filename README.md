Setup Instructions
1. Clone the repository
git clone https://github.com/yourusername/journal-app.git



3. Configure Database
The application.properties file is excluded from the repository for security reasons (it's added to .gitignore).
You need to create and configure the application.properties file in the src/main/resources directory with your database details. Example configuration:
application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/journal_db

spring.datasource.username=root

spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update



4. Build the Project
If you are using Maven, run the following command to build the project:
mvn clean install


4. Run the Application
You can run the application using:
mvn spring-boot:run


5. API Endpoints

POST "/user": Register a new user.

Request body:
{
  "username": "user1",
  "password": "password123"
}


GET "/journal/{username}": Retrieve all journal entries for a specific user.

POST "/journal/{username}": Create a new journal entry.

Request body:
{
  "title": "My First Journal Entry",
  "content": "This is my first entry!"
}

PUT "/journal/id/{username}/{id}": Update an existing journal entry.

Request body:
{
  "title": "Updated Title",
  "content": "Updated content"
}


DELETE "/journal/{username}/{id}": Delete a journal entry by ID.
