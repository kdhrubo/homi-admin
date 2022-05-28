

package com.tryhomi.admin.web.controller.admin.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tryhomi.admin.domain.Tag;
import com.tryhomi.admin.service.TagService;
import com.tryhomi.admin.web.support.DomainObjectSelect2Model;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TagSelect2Controller {

	@Autowired
	private TagService tagService;

	@RequestMapping(value="/{language}/tags/select")
	public @ResponseBody List<DomainObjectSelect2Model> select(
			@PathVariable String language,
			@RequestParam(required=false) String keyword) {
		TagSearchForm form = new TagSearchForm();
		form.setKeyword(keyword);
		Page<Tag> tags = tagService.getTags(form.toTagSearchRequest());

		List<DomainObjectSelect2Model> results = new ArrayList<>();
		if (tags.hasContent()) {
			for (Tag tag : tags) {
				DomainObjectSelect2Model model = new DomainObjectSelect2Model(tag.getId(), tag.getName());
				results.add(model);
			}
		}
		return results;
	}

	@RequestMapping(value="/{language}/tags/select/{id}", method= RequestMethod.GET)
	public @ResponseBody
	DomainObjectSelect2Model select(
			@PathVariable String language,
			@PathVariable Long id,
			HttpServletResponse response) throws IOException {
		Tag tag = tagService.getTagById(id, language);
		if (tag == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		DomainObjectSelect2Model model = new DomainObjectSelect2Model(tag.getName(), tag.getName());
		return model;
	}
}
