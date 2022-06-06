

CREATE TABLE t_launch_lead (
	id serial NOT NULL,
	email varchar(250) NOT NULL,
	created_date timestamptz NOT NULL,
	CONSTRAINT uk_t_launch_lead_email UNIQUE (email),
	CONSTRAINT pk_t_launch_lead_id PRIMARY KEY (id)
);






