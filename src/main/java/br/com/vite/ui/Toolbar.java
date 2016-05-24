package br.com.vite.ui;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.vite.tool.SharingTool;

public class Toolbar {

	private SharingTool sharingTool;
	private Button shareButton;
	
	public Toolbar(Application activity) {
		super();
		
		sharingTool = new SharingTool();
		
		shareButton = new Button(16, 42, 64, 64);
		shareButton.setLabel(new ImageLabel("ui/icons/share.png"));
		shareButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(sharingTool, "shareButtonAction"));
		activity.addView(shareButton);
	}
	
	public SharingTool getSharingTool() {
		return sharingTool;
	}
}
