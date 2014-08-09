package br.com.vite.collection;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.vite.map.Map;
import br.com.vite.tile.Tile;
import br.com.vite.tile.generator.TileCreator;

public abstract class MapApplication extends Application {

	private int uniqueId = 0;
	
	protected int mx = 0;
	protected int my = 0;
	
	protected boolean leftPressed = false;
	protected boolean rightPressed = false;
	protected boolean middlePressed = false;
	
	protected Map map;
	
	protected Tile[][] tiles;
	
	public MapApplication(int w, int h) {
		super(w, h);
	}
	
	protected void generateMap(int lines, int columns, TileCreator creator) {
		
		map = new Map(lines, columns);
		
		tiles = map.createTiles(creator);
	}

	protected int genereateUniqueId() {
		return uniqueId++;
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
	
}
