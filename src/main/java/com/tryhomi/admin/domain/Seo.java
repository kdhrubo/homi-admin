
package com.tryhomi.admin.domain;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.io.Serializable;

@Embeddable
@SuppressWarnings("serial")
public class Seo implements Serializable {

	@Column(name = "seo_title", length = 500)
	private String title;

	@Column(name = "seo_description")
	@Lob
	private String description;

	@Column(name = "seo_keywords")
	@Lob
	private String keywords;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
