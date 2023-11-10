-- public.merchant definition

-- Drop table

-- DROP TABLE public.merchant;

CREATE TABLE public.merchant (
	"open" bool NOT NULL,
	id uuid NOT NULL,
	merchant_location varchar(50) NULL,
	merchant_name varchar(50) NULL,
	CONSTRAINT merchant_pkey PRIMARY KEY (id)
);

-- public.orderdetail definition

-- Drop table

-- DROP TABLE public.orderdetail;

CREATE TABLE public.orderdetail (
	quantity int4 NULL,
	total_price float8 NULL,
	id uuid NOT NULL,
	order_id uuid NULL,
	product_id uuid NULL,
	CONSTRAINT orderdetail_pkey PRIMARY KEY (id),
	CONSTRAINT fkdubukg3j0fymgci0idnd72k0 FOREIGN KEY (product_id) REFERENCES public.product(id),
	CONSTRAINT fkfxkmvpbfxqect54pd7slj4ll9 FOREIGN KEY (order_id) REFERENCES public.orders(id)
);

-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	completed bool NOT NULL,
	order_time timestamp(6) NULL,
	id uuid NOT NULL,
	user_id uuid NULL,
	destination_address varchar(100) NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id)
);

-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	delete_product bool NOT NULL,
	price float8 NULL,
	id uuid NOT NULL,
	merchant_id uuid NULL,
	product_name varchar(50) NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT fkk47qmalv2pg906heielteubu7 FOREIGN KEY (merchant_id) REFERENCES public.merchant(id)
);

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id uuid NOT NULL,
	email_address varchar(50) NULL,
	"password" varchar(50) NULL,
	username varchar(50) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);