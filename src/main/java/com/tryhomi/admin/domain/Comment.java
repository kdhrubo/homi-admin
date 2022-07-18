

package com.tryhomi.admin.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment extends DomainObject<Long> implements Comparable<Comment> {

	public static final String SHALLOW_GRAPH_NAME = "COMMENT_SHALLOW_GRAPH";
	public static final String DEEP_GRAPH_NAME = "COMMENT_DEEP_GRAPH";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	private User author;

	@Column(length = 200, nullable = false)
	private String authorName;

	@Column(nullable = false)
	private LocalDateTime date;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private boolean approved;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String print() {
		return this.getClass().getName() + " " + getId();
	}

	public int compareTo(Comment comment) {
		return new CompareToBuilder()
				.append(getDate(), comment.getDate())
				.append(getId(), comment.getId())
				.toComparison();
	}
}
