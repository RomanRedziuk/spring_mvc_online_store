Online shop with Spring Core, Spring MVC and MySQL.

Deployment algorithm:

- Create the database that is called 'online_shop_jpa_spring_mvc', or change the URL to the database in the persistence.xml
- Go to persistence.xml and change the 'javax.persistence.schema-generation.database.action' property to 'drop-and-create'
- Start the application. You can start the web module.
- Now all tables are created. Open the database and import sample data that is located in the persistence module, in the 'database-export' folder. There is a file that is called "data_import_for_jpa_project.sql". Run import statements from that file in your database. 
- Go to persistence.xml and change the 'javax.persistence.schema-generation.database.action' property to 'update'
- Run the application again and use it


