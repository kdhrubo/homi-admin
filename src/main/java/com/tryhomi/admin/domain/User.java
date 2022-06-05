

package com.tryhomi.admin.domain;



import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({

		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})

public class User {


	public enum Role {
		ADMIN,
		EDITOR,
		AUTHOR,
		VIEWER,
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100, nullable = false, unique = true)
	private String loginId;

	@Column(length = 500, nullable = false)
	private String loginPassword;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "firstName", column = @Column(name = "name_first", length = 50, nullable = false)),
			@AttributeOverride(name = "lastName", column = @Column(name = "name_last", length = 50, nullable = false)),
	})
	private PersonalName name = new PersonalName();

	@Column(length = 500)
	private String nickname;

	@Column(length = 200, nullable = false, unique = true)
	private String email;

	@Lob
	@Column
	private String description;


	@Type(type = "jsonb")
	@Column(name = "user_role", columnDefinition = "jsonb")
	private SortedSet<Role> roles = new TreeSet<>();

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(length = 100)
	private String createdBy;

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@Column(length = 100)
	private String updatedBy;



	public String getGravatarUrl(int size) throws UnsupportedEncodingException {
		String hash = DigestUtils.md5DigestAsHex(email.getBytes("CP1252"));
		return String.format("https://secure.gravatar.com/avatar/%s?size=%d&d=mm", hash, size);
	}


}
