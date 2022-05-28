

package com.tryhomi.admin.web.controller.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.service.CategoryService;
import com.tryhomi.admin.web.support.DomainObjectSelect2Model;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategorySelectController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/{language}/categories/select")
	public @ResponseBody List<DomainObjectSelect2Model> select(
			@PathVariable String language,
			@RequestParam(required=false) String keyword) {
		CategorySearchForm form = new CategorySearchForm();
		form.setKeyword(keyword);
		form.setLanguage(language);
		Page<Category> categories = categoryService.getCategories(form.toCategorySearchRequest());

		List<DomainObjectSelect2Model> results = new ArrayList<>();
		if (categories.hasContent()) {
			for (Category category : categories) {
				DomainObjectSelect2Model model = new DomainObjectSelect2Model(category.getId(), category.getName());
				results.add(model);
			}
		}
		return results;
	}

	@RequestMapping(value="/{language}/categories/select/{id}", method= RequestMethod.GET)
	public @ResponseBody
	DomainObjectSelect2Model select(
			@PathVariable String language,
			@PathVariable Long id,
			HttpServletResponse response) throws IOException {
		Category category = categoryService.getCategoryById(id, language);
		if (category == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		DomainObjectSelect2Model model = new DomainObjectSelect2Model(category.getId(), category.getName());
		return model;
	}
}
