package org.zkoss.jspdemo.bean.binding;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class HelloMVVM {
	private String _textboxStr = "text box";
	private String _labelStr = "label";
	public void setTextboxStr (String textboxStr) {
		_textboxStr = textboxStr;
	}
	public String getTextboxStr () {
		return _textboxStr;
	}
	public void setLabelStr (String labelStr) {
		_labelStr = labelStr;
	}
	public String getLabelStr () {
		return _labelStr;
	}
	@Command @NotifyChange({"textboxStr", "labelStr"})
	public void sayHello () {
		_textboxStr = "Hello";
		_labelStr = "MVVM";
	}
}