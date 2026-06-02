use market;
create database market;

-- tabla categoria
create table category (
id int auto_increment primary key,
name varchar(40) not null
);

-- Tabla productos
create table products (
id int auto_increment primary key,
name varchar(100) not null,
price decimal (10,2) not null,
bar_Code varchar(25) unique not null,
state boolean default false,
id_category int not null,
foreign key(id_category)
references category(id)
);

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

ALTER TABLE inventory
MODIFY entry_date DATE NOT NULL DEFAULT (CURRENT_DATE);

-- tabla proveedores
create table suppliers (
	id int auto_increment primary key,
    nit VARCHAR(20) NOT NULL UNIQUE,
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

-- categorias
insert into category (name) values
('Bebidas'),
('Lacteos'),
('Aseo'),
('Snacks'),
('Panaderia');

-- productos
insert into products (name, price, bar_code, state, id_category) values
('Coca Cola 1L', 4500.00, '770123456001', true, 1),
('Pepsi 500ml', 3000.00, '770123456002', true, 1),
('Leche Alpina', 5200.00, '770123456003', true, 2),
('Queso Campesino', 8500.00, '770123456004', true, 2),
('Jabon Rey', 2500.00, '770123456005', true, 3),
('Detergente Ariel', 18000.00, '770123456006', true, 3),
('Papas Margarita', 4200.00, '770123456007', true, 4),
('Chocoramo', 2800.00, '770123456008', true, 4),
('Pan Integral', 6500.00, '770123456009', true, 5),
('Croissant', 3500.00, '770123456010', true, 5);

INSERT INTO employees (identification, name, role_position, start_date) VALUES 
('1020304050', 'Carlos Mendoza', 'administrador', '2025-01-15'),
('1122334455', 'Ana Rodríguez', 'cajero', '2025-02-01'),
('9876543210', 'Luis Martínez', 'auxiliar', '2025-03-10');
INSERT INTO suppliers (nit, name, phone) VALUES
('123456789', 'Distribuidora Global', '3001234567'),
('987654321', 'Tecnología y Más', '3159876543'),
('456789123', 'Suministros del Norte', '3104567890');

INSERT INTO inventory (id_product, id_supplier, stock, entry_date) VALUES 
(1, 1, 50, '2026-05-10'),
(2, 2, 120, '2026-05-15'),
(3, 3, 15, '2026-05-20'),
(1, 2, 30, '2026-05-25');

INSERT INTO inventory (id_product, id_supplier, stock) VALUE (1,3,0);