package com.turtorial.started.grid.inlineEditing.grid.inline_row_editing;

import com.turtorial.started.grid.inlineEditing.data.pojo.LanguageContribution;


public class LanguageContributionStatus {
	private LanguageContribution lc;
	private boolean editingStatus;
	
	public LanguageContributionStatus(LanguageContribution lc, boolean editingStatus) {
		this.lc = lc;
		this.editingStatus = editingStatus;
	}
	
	public LanguageContribution getLanguageContribution() {
		return lc;
	}
	
	public boolean getEditingStatus() {
		return editingStatus;
	}
	
	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
}
