ALTER TABLE t_user ADD tenant_id int4 NOT NULL;
ALTER TABLE t_user ADD CONSTRAINT t_user_fk_tentant_id FOREIGN KEY (id) REFERENCES nirvanacore.t_tenant(id);






