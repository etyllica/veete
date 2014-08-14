package br.com.vite;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.vite.map.Map;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public abstract class MapApplication extends Application {

	private int uniqueId = 0;
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;
	
	protected int columns = 10;
	
	protected int lines = 10;
	
	protected Map map;
		
	protected Tile[][] tiles;
	
		
	private int offsetSpeed = 6;
	
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
	protected ImageTileFloor selectedTile;

	protected ImageTileObject selectedObject;
	
	public MapApplication(int w, int h) {
		super(w, h);
	}

	protected void translateMap(int x, int y) {
		map.setOffset(-x, -y);		
	}	

	protected int genereateUniqueId() {
		return uniqueId++;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

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

		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
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
				
		if(upArrowPressed) {
			map.setOffsetY(map.getOffsetY()+offsetSpeed);
		} else if(downArrowPressed) {
			map.setOffsetY(map.getOffsetY()-offsetSpeed);
		}
		
		if(leftArrowPressed) {
			map.setOffsetX(map.getOffsetX()+offsetSpeed);

		} else if(rightArrowPressed) {
			map.setOffsetX(map.getOffsetX()-offsetSpeed);
		}
		
	}
	
	@Override
	public void draw(Graphic g) {

		map.draw(g, 0, 0);
		
	}
	
}
