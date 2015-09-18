package br.com.vite.editor;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.map.Map;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectionMapListener;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public abstract class MapEditor implements Drawable, SelectionMapListener {

	protected Map map;

	//Selection
	protected ImageTileFloor selectedTile;

	protected ImageTileObject selectedObject;

	//Input
	protected int mx, my;	

	protected boolean leftPressed = false;
	protected boolean rightPressed = false;
	protected boolean middlePressed = false;
	protected boolean ctrlPressed = false;

	protected int tileWidth = 0;
	protected int tileHeight = 0;

	private boolean drawCurrentTile = true;

	public MapEditor(int columns, int lines) {
		this(columns, lines, 64, 64);		
	}

	public MapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super();

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public void offsetMap(int offsetX, int offsetY) {
		map.setLocation(map.getX()+offsetX, map.getY()+offsetY);
	}

	public void translateMap(int x, int y) {
		map.setLocation(x, y);
	}

	public ImageTileFloor getFloorTile() {
		return selectedTile;
	}

	public void setFloorTile(ImageTileFloor floor) {
		selectedTile = floor;
		map.getFiller().setFloorTile(floor);
	}

	public ImageTileObject getObjectTile() {
		return selectedObject;
	}

	public void setObjectTile(ImageTileObject obj) {
		selectedObject = obj;
		map.getFiller().setObjectTile(obj);
	}

	public void draw(Graphic g) {
		this.draw(g, 0, 0);
	}
	
	public void draw(Graphic g, int x, int y) {
		map.draw(g, x, y);

		if (drawCurrentTile) {
			if (map.isOnTarget()) {
				if(!ctrlPressed)
					map.drawTileFiller(g);
				else
					map.drawObjectFiller(g);
			}
		}
	}

	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_CTRL_LEFT)) {
			ctrlPressed = true;
		} else if(event.isKeyUp(KeyEvent.VK_CTRL_LEFT)) {
			ctrlPressed = false;
		}

		return GUIEvent.NONE;		
	}

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

	public void update(long now) {

		Tile lastSelectedTile = map.updateTarget(mx, my);

		if(map.isOnTarget()) {

			if(rightPressed) {
				//Erase Tile
				lastSelectedTile.setLayer(null);
				lastSelectedTile.setObjectLayer(null);				
			}

			if(!ctrlPressed) {

				if(leftPressed && selectedTile != null) {				
					lastSelectedTile.setLayer(selectedTile);
					lastSelectedTile.setCollision(selectedTile.getCollision());
				} else if(middlePressed) {

				}

			} else {

				if(leftPressed && selectedObject != null) {
					lastSelectedTile.setObjectLayer(selectedObject);					
				}
			}
		}
	}

	public MapType getType() {
		return map.getType();
	}

	public int getOffsetX() {
		return map.getX();
	}

	public int getOffsetY() {
		return map.getY();
	}

	public void swapGridShow() {
		map.getDrawer().swapDrawGrid();
	}

	public void enableGridShow() {
		map.getDrawer().setDrawGrid(false);
	}

	public void disableGridShow() {
		map.getDrawer().setDrawGrid(false);
	}

	public void swapCollisionShow() {
		map.getDrawer().swapDrawCollision();
	}

	public void enableCollisionShow() {
		map.getDrawer().setDrawCollision(true);
	}

	public void disableCollisionShow() {
		map.getDrawer().setDrawCollision(false);
	}

	public void enableCurrentTileShow() {
		drawCurrentTile = true;
	}

	public void disableCurrentTileShow() {
		drawCurrentTile = false;
	}

	public int getTileWidth() {
		return map.getTileWidth();
	}

	public int getTileHeight() {
		return map.getTileHeight();
	}

	public int getColumns() {
		return map.getColumns();
	}

	public int getLines() {
		return map.getLines();
	}

	public Tile[][] getTiles() {
		return map.getTiles();
	}

	public Map getMap() {
		return map;
	}

}
