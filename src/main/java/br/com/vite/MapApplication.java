package br.com.vite;

import br.com.etyllica.context.Application;
import br.com.etyllica.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.vite.editor.MapEditor;
import br.com.vite.tile.layer.ImageTileObject;

public abstract class MapApplication extends Application implements UpdateIntervalListener {
		
	protected MapEditor editor;
	
	private int normalSpeed = 6;
	private int fastSpeed = 50;
	
	private int offsetSpeed = normalSpeed;
	
	//Input
	protected int mx = 0;
	protected int my = 0;

	protected boolean leftPressed = false;
	protected boolean rightPressed = false;
	protected boolean middlePressed = false;
	
	protected boolean upArrowPressed = false;
	protected boolean leftArrowPressed = false;
	protected boolean downArrowPressed = false;
	protected boolean rightArrowPressed = false;
	
	//Selection
	protected ImageTileObject selectedObject;
	
	public MapApplication(int w, int h) {
		super(w, h);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		editor.updateKeyboard(event);

		if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)) {
			upArrowPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_UP_ARROW)) {
			upArrowPressed = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_DOWN_ARROW)) {
			downArrowPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_DOWN_ARROW)) {
			downArrowPressed = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			leftArrowPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_LEFT_ARROW)) {
			leftArrowPressed = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			rightArrowPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_RIGHT_ARROW)) {
			rightArrowPressed = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_G)) {
			editor.swapGridShow();
		}
		
		if(event.isKeyDown(KeyEvent.TSK_C)) {
			editor.swapCollisionShow();
		}
		
		if(event.isAnyKeyDown(KeyEvent.TSK_SHIFT_LEFT, KeyEvent.TSK_SHIFT_RIGHT)) {
			offsetSpeed = fastSpeed;
		} else if(event.isKeyUp(KeyEvent.TSK_SHIFT_LEFT)||event.isKeyUp(KeyEvent.TSK_SHIFT_RIGHT)) {
			offsetSpeed = normalSpeed;
		}

		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		editor.updateMouse(event);
		
		my = event.getY();
		mx = event.getX();
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			leftPressed = true;
		}else if(event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
			leftPressed = false;
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_RIGHT)) {
			rightPressed = true;
		}else if(event.isButtonUp(MouseButton.MOUSE_BUTTON_RIGHT)) {
			rightPressed = false;
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_MIDDLE)) {
			middlePressed = true;
		}else if(event.isButtonUp(MouseButton.MOUSE_BUTTON_MIDDLE)) {
			middlePressed = false;
		}
				
		return GUIEvent.NONE;
	}
	
	@Override
	public void timeUpdate(long now) {
		
		editor.update(now);
		
		if(upArrowPressed) {
			offsetMap(0, offsetSpeed);
		} else if(downArrowPressed) {
			offsetMap(0, -offsetSpeed);
		}
		
		if(leftArrowPressed) {
			offsetMap(offsetSpeed, 0);			
		} else if(rightArrowPressed) {
			offsetMap(-offsetSpeed, 0);
		}
		
	}
	
	protected void offsetMap(int offsetX, int offsetY) {
		editor.offsetMap(offsetX, offsetY);
	}
	
	@Override
	public void draw(Graphic g) {
		editor.draw(g);
	}
	
}
