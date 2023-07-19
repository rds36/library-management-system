# Cara Running

1. Run Database PostgreSQL
2. Build Library Management System App JAR files
    ``` bash
   mvn clean install
   ```
   Jar file akan tersimpan di directory : `/target`
4. Run jar file dengan perintah:
   ```bash
   java -jar <nama-file>.jar
   ```

   contoh:
   ```bash
   java -jar library-management-system-0.0.1-SNAPSHOT.jar
   ```

# Pre Config
### Menjalankan Database
- #### Manual
1. Install PostgreSQL / run database via docker
2. Jalankan postgreSQL dan buat database dengan nama `agit-library`
3. Buat table 
   ```sql
   CREATE TABLE public.book (
	book_id varchar(255) NOT NULL,
	author varchar(255) NULL,
	borrowed_at timestamp NULL,
	borrowed_by varchar(255) NULL,
	publish_date date NULL,
	publisher varchar(255) NULL,
	returned_at timestamp NULL,
	status int4 NULL,
	title varchar(255) NULL,
	total_page varchar(255) NULL,
	CONSTRAINT book_pkey PRIMARY KEY (book_id)
   ```
- #### Export Sql
1. Install PostgreSQL / run database via docker
2. Jalankan postgreSQL dan buat database dengan nama `agit-library`
3. buka terminal di directory file `postgres-backup.sql`
4. lalu jalankan perintah: 
   ```bash
   psql -U <database_owner> -d <database_name> < postgres-backup.sql
   ```
   via docker
   ```bash
      docker exec -i <container-name> psql -U <database_owner> -d <database_name> < postgres-backup.sql
   ```