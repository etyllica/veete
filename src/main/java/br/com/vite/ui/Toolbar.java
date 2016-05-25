package br.com.vite.ui;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.vite.editor.MapEditor;
import br.com.vite.tool.SharingTool;

public class Toolbar {

	private static final int BUTTON_SIZE = 64;
	private static final int OFFSET = 8;
	
	private SharingTool sharingTool;
	private Button shareButton;
	private Button joinButton;
	
	private String ip = "127.0.0.1";
	
	public Toolbar(Application activity, MapEditor editor) {
		super();
		
		sharingTool = new SharingTool(editor);
		
		int x = 16;
		int y = 42;
		
		shareButton = new Button(x, y, BUTTON_SIZE, BUTTON_SIZE);
		shareButton.setLabel(new ImageLabel("ui/icons/share.png"));
		shareButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(sharingTool, "openServerAction"));
		activity.addView(shareButton);
		
		joinButton = new Button(x+BUTTON_SIZE+OFFSET, y, BUTTON_SIZE, BUTTON_SIZE);
		joinButton.setLabel(new ImageLabel("ui/icons/share.png"));
		joinButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(sharingTool, "joinServerAction", ip));
		activity.addView(joinButton);
	}
	
	public SharingTool getSharingTool() {
		return sharingTool;
	}
}
