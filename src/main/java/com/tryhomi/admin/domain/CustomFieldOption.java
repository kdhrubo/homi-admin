package com.tryhomi.admin.domain;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@SuppressWarnings("serial")
public class CustomFieldOption implements Serializable {

	@Column(length = 200, nullable = false)
	private String name;

	@Column(length = 3, nullable = false)
	private String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return getName();
	}
}
