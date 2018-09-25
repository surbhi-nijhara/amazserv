Documents:
####  Datatbase Documents
* Data model Vertabelo file: 
* Model_PNG: 
* Preseeded data XLS: 
* Aurora Cluster Setup: 
**References: https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Aurora.CreateInstance.html

##### Steps for creation of Database

 * Open the PostgreSQL client
 * Run the below commands to create database with name **_db_**
 ```sql
 create database _db;
 ```
##### Steps for creation of Schema

 * Open the command prompt/Terminal
 * Navigate to folder **_db/_** of git checkout
 * Modify and run the below commands
 ```sql
 psql -h <host_name> --username=<username> -p <port> -d savkar_db -a -f Savkar_DDL.sql
 ```
##### Steps for inserting dummy/preseeded data
This step is **optional** in production environment with the assumption that we have set of apis for creation of Tags, Questions and other entity data.
 * Open the command prompt/Terminal
 * Navigate to folder __db/__ of git checkout
 * Modify and run the below commands
	
```sql
psql -h <host_name> --username=<username> -p <port> -d _db -a -f _Preseeded.sql
```
