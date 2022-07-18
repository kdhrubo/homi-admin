package com.tryhomi.admin.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
	
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String TENANT_ADMIN = "ROLE_TENANT_ADMIN";
	public static final String TENANT_USER = "ROLE_TENANT_USER";

	
	@Id
	private Integer id;
	
	private String name;


    
}
