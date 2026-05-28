create database market;
use market;

create table category (
id int auto_increment primary key,
name varchar(40) not null
);
select * from products;

-- Tabla productos
create table products (
id int auto_increment primary key,
name varchar(100) not null,
price decimal(10,2) not null,
barCode varchar(25) unique not null,
state boolean default false,
id_category int not null,
foreign key(id_category)
references category(id)
);

drop table products;


-- tabla inventario
create table inventory (
	id int auto_increment primary key,
    id_product int not null,
    id_supplier int not null,
    stock int not null default 0,
    entry_date date not null,
    
    foreign key(id_product)
    references products(id),

    foreign key(id_supplier)
    references suppliers(id)
);

-- tabla proveedores
create table suppliers (
	id int auto_increment primary key,
    name varchar(50) not null,
    phone varchar(10) not null
);
-- tabla empleados
create table employees (
	id int auto_increment primary key,
    identification varchar (10),
    name varchar (50),
    role_position varchar(20) check(role_position in ('administrador', 'cajero', 'auxiliar')),
    start_date date not null
);

-- tabla venta
create table sale (
    id int auto_increment primary key,
    id_employee int not null,
    sale_date datetime default current_timestamp,
    total decimal(10,2) not null default 0,

    foreign key(id_employee)
    references employees(id)
);


-- detalle de venta
create table sale_detail (
    id int auto_increment primary key,
    id_sale int not null,
    id_product int not null,
    quantity int not null,
    unit_price decimal(10,2) not null,
    subtotal decimal(10,2) not null,
    
    foreign key(id_sale)
    references sale(id),

    foreign key(id_product)
    references products(id)
);

delimiter //
create procedure get_category_with_products(in p_category_id int)
begin
    select c.id as category_id, c.name as category_name, p.id as product_id, p.name as product_name, p.barCode, p.state
    from category c
    inner join products p on c.id = p.id_category
	where c.id = p_category_id and p.state = true;
end //
delimiter ;







