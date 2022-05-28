/*
 * Copyright 2014 Tagbangers, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tryhomi.admin.web.controller.guest.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.domain.Comment;
import com.tryhomi.admin.model.CommentCreateRequest;
import com.tryhomi.admin.model.CommentDeleteRequest;
import com.tryhomi.admin.model.CommentUpdateRequest;
import com.tryhomi.admin.service.CommentService;
import com.tryhomi.admin.support.AuthorizedUser;
import com.tryhomi.admin.web.support.DomainObjectDeletedModel;
import com.tryhomi.admin.web.support.RestValidationErrorModel;



@RestController
@RequestMapping("/comments")
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	private static Logger logger = LoggerFactory.getLogger(CommentRestController.class);

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestValidationErrorModel bindException(BindException e) {
		logger.debug("BindException", e);
		return RestValidationErrorModel.fromBindingResult(e.getBindingResult(), messageSourceAccessor);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public CommentSavedModel create(
			@Validated CommentForm form,
			BindingResult result,
			BlogLanguage blogLanguage,
			AuthorizedUser authorizedUser) throws BindException {
		if (result.hasErrors()) {
			throw new BindException(result);
		}

		CommentCreateRequest request = form.toCommentCreateRequest(blogLanguage, authorizedUser);
		Comment comment = commentService.createComment(request, authorizedUser);
		return new CommentSavedModel(comment);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public CommentSavedModel update(
			@PathVariable long id,
			@Validated CommentForm form,
			BindingResult result,
			AuthorizedUser authorizedUser) throws BindException {
		if (result.hasFieldErrors("content")) {
			throw new BindException(result);
		}
		CommentUpdateRequest request = form.toCommentUpdateRequest(id);
		Comment comment = commentService.updateComment(request, authorizedUser);
		return new CommentSavedModel(comment);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DomainObjectDeletedModel<Long> delete(
			@PathVariable long id,
			AuthorizedUser authorizedUser) {
		CommentDeleteRequest request = new CommentDeleteRequest();
		request.setId(id);
		Comment comment = commentService.deleteComment(request, authorizedUser);
		return new DomainObjectDeletedModel<>(comment);
	}
}
