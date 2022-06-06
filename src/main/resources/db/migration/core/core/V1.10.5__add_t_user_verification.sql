ALTER TABLE t_user ADD verification_token varchar(32) ;
ALTER TABLE t_user ADD verification_expiry_date timestamptz;
ALTER TABLE t_user ADD enabled boolean default false;





