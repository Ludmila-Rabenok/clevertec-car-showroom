drop table reviews;
drop table cars;
drop table clients;
drop table categories;
drop table car_showrooms;


create table car_showrooms
(id bigserial not null,
 name varchar(50) not null,
 address varchar(100) not null,
 constraint pk_showroom primary key(id),
 constraint name_unique unique ("name"));


create table categories
(id bigserial not null,
 name varchar(50) not null,
 constraint pk_category primary key(id),
 constraint name_category_unique unique ("name"));


create table clients
(id bigserial not null,
 name varchar(50) not null,
 contact varchar(50) not null,
 registration date default now(),
 constraint pk_client primary key(id));

create table cars
(id bigserial not null,
 model varchar(50) not null,
 brand varchar(50) not null,
 year int not null,
 price decimal not null,
 category_id int not null,
 car_showroom_id int not null,
 constraint pk_car primary key(id),
 foreign key (category_id) references categories(id) on update restrict,
 foreign key (car_showroom_id) references car_showrooms(id) on update restrict);

create table reviews
(id bigserial not null,
 text varchar(300) not null,
 rating int not null,
 client_id int not null,
 car_id int not null,
 constraint pk_review primary key(id),
 foreign key (client_id) references clients(id) on delete cascade on update restrict,
 foreign key (car_id) references cars(id) on delete cascade on update restrict);

insert into car_showrooms(name, address)
values('Salon1', 'Ivanova 5');

insert into categories(name)
values('sedan'),
      ('truck');

insert into clients(name, contact)
values('Luda', '12336547'),
      ('Olga', '8745621');

insert into cars(model, brand, year, price, category_id, car_showroom_id)
values('A-5', 'AUDI', 1999, 30000.5, 1, 1),
      ('PnV', 'BMV', 2010, 50000.5, 2, 1);

insert into reviews(text, rating, client_id, car_id)
values('Ok', 5, 1, 1),
      ('super', 10, 2, 2);