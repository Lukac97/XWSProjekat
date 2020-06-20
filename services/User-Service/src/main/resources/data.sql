--CUSTOMERS
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 1,'cus1@gmail.com','customer', 123, 'Customer', 'One');
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 2,'cus2@gmail.com','customer', 123, 'Customer', 'One');
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 3,'cus3@gmail.com','customer', 123, 'Customer', 'One');
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 4,'cus4@gmail.com','customer', 123, 'Customer', 'One');
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 5,'cus5@gmail.com','customer', 123, 'Customer', 'One');
INSERT INTO person (discriminator, id, email, password, phone_number, name, surname) VALUES ('CUSTOMER', 6,'cus6@gmail.com','customer', 123, 'Customer', 'One');

--AGENTS
INSERT INTO person (discriminator, id, email, password, phone_number, company_name) VALUES ('AGENT', 7,'agent1@gmail.com','agent', 123, 'Company 1');
INSERT INTO person (discriminator, id, email, password, phone_number, company_name) VALUES ('AGENT', 8,'agent2@gmail.com','agent', 123, 'Company 2');
INSERT INTO person (discriminator, id, email, password, phone_number, company_name) VALUES ('AGENT', 9,'agent3@gmail.com','agent', 123, 'Company 3');

--ADMINS
INSERT INTO person (discriminator, id, email, password, phone_number, admin_name) VALUES ('ADMINISTRATOR', 10,'admin1@gmail.com','admin', 123, 'Admin 1');
