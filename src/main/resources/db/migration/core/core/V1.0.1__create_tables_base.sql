

CREATE TABLE t_tenant (
	id serial NOT NULL,
	tenant_id varchar(250) NOT NULL,
	tenant_name varchar(250) NOT NULL,
	created_date timestamptz NOT NULL,
	deleted bool NOT NULL DEFAULT false,
	CONSTRAINT uk_tenant_id UNIQUE (tenant_id),
	CONSTRAINT pk_t_tenant_id PRIMARY KEY (id)
);


-- public.t_role definition

-- Drop table

-- DROP TABLE t_role;

CREATE TABLE t_role (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	CONSTRAINT pk_t_role PRIMARY KEY (id)
);



-- public.t_user definition

-- Drop table

-- DROP TABLE t_user;

CREATE TABLE t_user (
	id serial NOT NULL,
	mobile varchar(20) NOT NULL,
	email varchar(100) NULL,
	last_updated_date timestamptz NOT NULL,
	deleted bool NOT NULL DEFAULT false,
	created_date timestamptz NOT NULL,
	last_login_date timestamptz NULL,
	role_id int4 NOT NULL,
	"password" varchar(100) NULL,
	mobile_verified bool NULL DEFAULT false,
	email_verified bool NULL DEFAULT false,
	CONSTRAINT t_user_email_key UNIQUE (email),
	CONSTRAINT t_user_mobile_key UNIQUE (mobile),
	CONSTRAINT t_user_pkey PRIMARY KEY (id)
);




