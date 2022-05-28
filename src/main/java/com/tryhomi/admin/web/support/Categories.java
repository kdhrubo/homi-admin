

package com.tryhomi.admin.web.support;

import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.TreeNode;
import com.tryhomi.admin.support.CategoryUtils;
import org.thymeleaf.context.IExpressionContext;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.TreeNode;
import com.tryhomi.admin.support.CategoryUtils;

import java.util.List;

public class Categories {

	private IExpressionContext context;

	private com.tryhomi.admin.support.CategoryUtils CategoryUtils;

	public Categories(IExpressionContext context, CategoryUtils CategoryUtils) {
		this.context = context;
		this.CategoryUtils = CategoryUtils;
	}

	public List<Category> getAllCategories() {
		return CategoryUtils.getAllCategories();
	}

	public List<Category> getAllCategories(boolean includeNoPosts) {
		return CategoryUtils.getAllCategories(includeNoPosts);
	}

	public List<TreeNode<Category>> getNodes() {
		return CategoryUtils.getNodes();
	}

	public List<TreeNode<Category>> getNodes(boolean includeNoPosts) {
		return CategoryUtils.getNodes(includeNoPosts);
	}
}