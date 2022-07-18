

package com.tryhomi.admin.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedEntityGraphs({
		@NamedEntityGraph(name = Blog.DEEP_GRAPH_NAME,
				attributeNodes = {
						@NamedAttributeNode("languages")})
})
@Table(name = "blog")
@DynamicInsert
@DynamicUpdate
public class Blog extends DomainObject<Long> {

	public static final long DEFAULT_ID = 1;

	public static final String DEEP_GRAPH_NAME = "BLOG_DEEP_GRAPH";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 200, nullable = false, unique = true)
	private String code;

	@Column(length = 3, nullable = false)
	private String defaultLanguage;

	@Embedded
	private GoogleAnalytics googleAnalytics;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private Set<BlogLanguage> languages = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public GoogleAnalytics getGoogleAnalytics() {
		return googleAnalytics;
	}

	public void setGoogleAnalytics(GoogleAnalytics googleAnalytics) {
		this.googleAnalytics = googleAnalytics;
	}

	public Set<BlogLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<BlogLanguage> languages) {
		this.languages = languages;
	}

	public BlogLanguage getLanguage(String language) {
		for (BlogLanguage blogLanguage : getLanguages()) {
			if (blogLanguage.getLanguage().equals(language)) {
				return blogLanguage;
			}
		}
		return null;
	}

	public String getTitle() {
		return getTitle(getDefaultLanguage());
	}

	public String getTitle(String language) {
		return getLanguage(language).getTitle();
	}

	public boolean isMultiLanguage() {
		return (getLanguages().size() > 1);
	}

	@Override
	public String print() {
		return getTitle();
	}
}
