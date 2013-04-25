package com.turtorial.started.tree;

import java.util.LinkedList;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;


public class CategoryTreeNode extends DefaultTreeNode<Category> {
	private static final long serialVersionUID = 1L;
	int count;

	public CategoryTreeNode(Category category, int count) {
		super(category, new LinkedList<TreeNode<Category>>()); // assume not a leaf-node
		this.count = count;
	}

	public String getDescription() {
		return getData().getDescription();
	}

	public int getCount() {
		return count;
	}

	public boolean isLeaf() {
		return getData() != null && getData().getChildren().isEmpty();
	}
}