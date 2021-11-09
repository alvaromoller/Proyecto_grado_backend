select *from product;
Insert into product(name, description, img, brand_id, shop_id, product_type_id) values("producto 1", "descripcion 1", "img 1", 1, 1, 1);
Insert into product(name, description, img, brand_id, shop_id, product_type_id) values("producto 2", "descripcion 2", "img 2", 2, 2, 2);
Insert into product(name, description, img, brand_id, shop_id, product_type_id) values("producto 3", "descripcion 3", "img 3", 3, 3, 3);
Insert into product(name, description, img, brand_id, shop_id, product_type_id) values("producto 4", "descripcion 4", "img 4", 4, 4, 4);

select *from brand;
Insert into brand(name) values("marca 1");
Insert into brand(name) values("marca 2");
Insert into brand(name) values("marca 3");
Insert into brand(name) values("marca 4");

select *from shop;
Insert into shop(name, description, location, img) values("tienda 1", "descripcion 1", "ubicacion 1", "img 1");
Insert into shop(name, description, location, img) values("tienda 2", "descripcion 2", "ubicacion 2", "img 2");
Insert into shop(name, description, location, img) values("tienda 3", "descripcion 3", "ubicacion 3", "img 3");
Insert into shop(name, description, location, img) values("tienda 4", "descripcion 4", "ubicacion 4", "img 4");

select * from product_type;
Insert into product_type(name) values("tipo de producto 1");
Insert into product_type(name) values("tipo de producto 2");
Insert into product_type(name) values("tipo de producto 3");
Insert into product_type(name) values("tipo de producto 4");
