use market;

delimiter //
create procedure get_category_with_products(in p_category_id int)
begin
    select c.id as category_id, c.name as category_name, p.id as product_id, p.name as product_name, p.bar_code, p.state
    from category c
    inner join products p on c.id = p.id_category
	where c.id = p_category_id and p.state = true;
end //
delimiter ;


delimiter //
create procedure pa_reduce_stock(in product int, in quanty_value int )
begin 
	declare stock_fount int;
    select stock into stock_fount 
    from inventory 
    where id_product = product 
    limit 1;
    
    if stock_fount >= quanty_value then
        update inventory set stock = stock - quanty_value 
        where id_product = product;
	elseif stock_fount <= 0 then
		update products set state = false where id = product;
    else
        signal sqlstate '45000' set message_text = 'No hay suficiente stock para este producto';
    end if;
end //
delimiter ;

delimiter //

create procedure pa_calcular_totales_venta(
    in p_id_sale int
)
begin
    declare v_subtotal_calculado decimal(10,2) default 0.00;

    select coalesce(sum(quantity * unit_price), 0.00) into v_subtotal_calculado 
    from sale_detail 
    where id_sale = p_id_sale;

    update sale 
    set total = v_subtotal_calculado * 1.19
    where id = p_id_sale;
end //

delimiter ;

delimiter //
create procedure pa_list_employees_by_role(in rol varchar(20))
begin 
	select * from employees where role_position = rol;
 end //
delimiter ;