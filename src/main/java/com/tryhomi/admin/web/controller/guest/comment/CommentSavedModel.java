

package com.tryhomi.admin.web.controller.guest.comment;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tryhomi.admin.domain.Comment;
import com.tryhomi.admin.support.LocalDateTimeSerializer;
import com.tryhomi.admin.web.support.DomainObjectSavedModel;

import java.time.LocalDateTime;

public class CommentSavedModel extends DomainObjectSavedModel<Long> {

	private String authorName;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime date;
	private String content;

	public CommentSavedModel(Comment comment) {
		super(comment);
		authorName = comment.getAuthorName();
		date = comment.getDate();
		content = comment.getContent();
	}

	public String getAuthorName() {
		return authorName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
}
