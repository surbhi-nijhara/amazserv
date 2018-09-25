Documents:
####  Datatbase Documents
* Data model Vertabelo file: https://drive.google.com/open?id=1H-hVWtLIUdBJ-bsLKY4Eg1i-wkMmUH4O
* Model_PNG: https://drive.google.com/open?id=171lqSBPbZyak5UszmlVbdsspOCooY9nS
* Preseeded data XLS: https://drive.google.com/open?id=11BKoW5kqe7ZLEjrGGdxCCY_KWUu6IBr_
* Aurora Cluster Setup: https://drive.google.com/open?id=1NCFY-3UqoHUtEoUk488QuvW2UKx03wUu
**References: https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Aurora.CreateInstance.html

##### Steps for creation of Database

 * Open the PostgreSQL client
 * Run the below commands to create database with name **_savkar_db_**
 ```sql
 create database savkar_db;
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
psql -h <host_name> --username=<username> -p <port> -d savkar_db -a -f Savkar_Preseeded.sql
```
