package br.com.vite;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.vite.map.Map;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.TileColider;
import br.com.vite.tile.drawer.TileDrawer;
import br.com.vite.tile.filler.TileFiller;
import br.com.vite.tile.generator.TileCreator;

public abstract class MapApplication extends Application {

	private int uniqueId = 0;
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;
	
	protected int columns = 10;
	
	protected int lines = 10;
	
	protected Map map;
	
	protected Tile[][] tiles;
	
	protected TileCreator creator;
	
	protected TileColider colider;
	
	protected TileDrawer drawer;
	
	protected TileFiller filler;
	
	protected Tile lastTile;
	
	protected int offsetX;
	
	protected int offsetY;
	
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
	
	public MapApplication(int w, int h) {
		super(w, h);
	}

	protected void translateMap(int x, int y) {
		offsetX = -x;
		offsetY = -y;
		
		offsetTiles(offsetX, offsetY);
	}
	
	private void offsetTiles(int offsetX, int offsetY) {
		
		drawer.setOffsetX(offsetX);
		drawer.setOffsetY(offsetY);
		
		colider.setOffsetX(offsetX);
		colider.setOffsetY(offsetY);
		
		filler.setOffsetX(offsetX);
		filler.setOffsetY(offsetY);		
	}
	
	protected void generateMap(int lines, int columns, TileCreator creator) {
		
		map = new Map(lines, columns);
		
		tiles = map.createTiles(creator);
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
		
		boolean needUpdate = false;
		
		if(upArrowPressed) {
			offsetY+=offsetSpeed;
			needUpdate = true;
		} else if(downArrowPressed) {
			offsetY-=offsetSpeed;
			needUpdate = true;
		}
		
		if(leftArrowPressed) {
			offsetX+=offsetSpeed;
			needUpdate = true;
		} else if(rightArrowPressed) {
			offsetX-=offsetSpeed;
			needUpdate = true;
		}
		
		if(needUpdate)
			offsetTiles(offsetX, offsetY);
	}
	
	@Override
	public void draw(Graphic g) {

		for(int j=0;j<lines;j++) {

			for(int i=0;i<columns;i++) {

				Tile tile = tiles[j][i];

				drawer.drawTile(tile, g);

			}
		}

		g.setAlpha(45);
		filler.drawFiller(lastTile, g);
		g.setAlpha(100);
	}
	
}
