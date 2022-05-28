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

package com.tryhomi.admin.web.controller.admin.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tryhomi.admin.domain.Blog;
import com.tryhomi.admin.service.BlogService;
import com.tryhomi.admin.service.SetupService;
import com.tryhomi.admin.web.support.HttpForbiddenException;


import javax.validation.Valid;

@Controller
@RequestMapping("/setup")
public class SetupController {

	@Autowired
	private SetupService setupService;
	@Autowired
	private BlogService blogService;

	@ModelAttribute("form")
	public SetupForm setupForm() {
		return new SetupForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setup() {
		Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
		if (blog != null) {
			throw new HttpForbiddenException();
		}
		return "setup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(
			@Valid @ModelAttribute("form") SetupForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
		if (blog != null) {
			throw new HttpForbiddenException();
		}
		if (result.hasErrors()) {
			return "setup";
		}
		setupService.setup(form.buildSetupRequest());
		return "redirect:/_admin/login";
	}
}
