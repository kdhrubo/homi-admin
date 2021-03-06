

package com.tryhomi.admin.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "language"}))
@DynamicInsert
@DynamicUpdate
@SuppressWarnings("serial")
public class Tag extends DomainObject<Long> implements Comparable<Tag> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 200, nullable = false)
	private String name;

	@Column(length = 3, nullable = false)
	private String language;

	@ManyToMany
	@JoinTable(
			name = "post_tag",
			joinColumns = {@JoinColumn(name = "tag_id")},
			inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
	@SortNatural
	private SortedSet<Post> posts = new TreeSet<>();

//	@Formula("(" +
//			"select count(distinct article.id) from article article " +
//			"inner join post post on article.id = post.id " +
//			"inner join article_tag tag on article.id = tag.article_id " +
//			"where tag.tag_id = id " +
//			"and post.status = 'PUBLISHED') ")
//	private int articleCount;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public SortedSet<Post> getPosts() {
		return posts;
	}

	public void setPosts(SortedSet<Post> posts) {
		this.posts = posts;
	}

//	public int getArticleCount() {
//		return articleCount;
//	}

	@Override
	public String print() {
		return getName();
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Tag tag) {
		return new CompareToBuilder()
				.append(getName(), tag.getName())
				.append(getId(), tag.getId())
				.toComparison();
	}
}
