

package com.tryhomi.admin.web.controller.admin.comment;

import com.tryhomi.admin.domain.Comment;
import com.tryhomi.admin.exception.ServiceException;
import com.tryhomi.admin.service.CommentService;
import com.tryhomi.admin.support.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping(value="/{language}/comments/bulk-unapprove", method=RequestMethod.POST)
public class CommentBulkUnapproveController {

	@Autowired
	private CommentService commentService;

	private static Logger logger = LoggerFactory.getLogger(CommentBulkUnapproveController.class);

	@ModelAttribute("query")
	public String query(@RequestParam(required = false) String query) {
		return query;
	}

	@RequestMapping
	public String unapprove(
			@Valid @ModelAttribute("form") CommentBulkUnapproveForm form,
			BindingResult errors,
			String query,
			AuthorizedUser authorizedUser,
			RedirectAttributes redirectAttributes,
			Model model) {
		redirectAttributes.addAttribute("query", query);

		if (errors.hasErrors()) {
			logger.debug("Errors: {}", errors);
			return "redirect:/_admin/{language}/comments/index";
		}

		Collection<Comment> unapprovedComments = null;
		try {
			//unapprovedComments = commentService.bulkUnapproveComment(form.toCommentBulkUnapproveRequest(), authorizedUser);
		} catch (ServiceException e) {
			return "redirect:/_admin/{language}/comments/index";
		}

		redirectAttributes.addFlashAttribute("unapprovedComments", unapprovedComments);
		return "redirect:/_admin/{language}/comments/index";
	}
}
