package com.tryhomi.admin.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({

		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})

public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name")
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Type(type = "jsonb")
	@Column(name = "user_role", columnDefinition = "jsonb")
	private SortedSet<Role> roles = new TreeSet<>();

	@Column(name = "deleted")
	private boolean deleted;
	

	@LastModifiedDate
	@Column(name = "last_updated_date")
	private LocalDateTime lastUpdatedDate;

	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;

	@OneToOne
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	private Tenant tenant;

	@Column(name = "verification_token")
	private String verificationToken;
	@Column(name = "verification_expiry_date")
	private LocalDateTime verificationTokenExpiry;
	@Column(name = "enabled")
	private boolean enabled;

	public String getGravatarUrl(int size) throws UnsupportedEncodingException {
		String hash = DigestUtils.md5DigestAsHex(email.getBytes("CP1252"));
		return String.format("https://secure.gravatar.com/avatar/%s?size=%d&d=mm", hash, size);
	}


	/*
	public enum Role {
		ADMIN,
		EDITOR,
		AUTHOR,
		VIEWER,
	}*/
}
