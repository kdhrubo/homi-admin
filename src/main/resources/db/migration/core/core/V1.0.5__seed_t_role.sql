INSERT INTO t_role (id,"name") VALUES (1,'ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_role (id,"name") VALUES (2,'ROLE_TENANT_ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_role (id,"name") VALUES (3,'ROLE_TENANT_USER') ON CONFLICT (id) DO NOTHING;
