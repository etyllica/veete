package br.com.vite.editor;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.linear.Point2D;
import br.com.vite.map.Map;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectionMapListener;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public abstract class MapEditor implements Drawable, SelectionMapListener {

	protected MapType type;

	protected Map map;

	protected boolean onMouse = false;

	//Selection

	protected Tile lastSelectedTile;

	protected Point2D target = new Point2D(0, 0);	

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

	public MapEditor(int columns, int lines) {
		this(columns, lines, 64, 64);		
	}

	public MapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super();

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public void offsetMap(int offsetX, int offsetY) {
		map.setOffset(map.getOffsetX()+offsetX, map.getOffsetY()+offsetY);
	}

	public void translateMap(int x, int y) {
		map.setOffset(x, y);
	}

	public void setFloorTile(ImageTileFloor floor) {
		selectedTile = floor;
		map.getFiller().setFloorTile(floor);
	}

	public void setObjectTile(ImageTileObject obj) {
		selectedObject = obj;
		map.getFiller().setObjectTile(obj);
	}

	public void draw(Graphic g) {
		map.draw(g, 0, 0);

		if(onMouse) {
			if(!ctrlPressed)
				map.drawTileFiller(g, lastSelectedTile);
			else
				map.drawObjectFiller(g, lastSelectedTile);
		}
	}

	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_CTRL_LEFT)) {
			ctrlPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_CTRL_LEFT)) {
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

	public Tile getTargetTile(int mx, int my) {

		updateTarget(mx, my);

		lastSelectedTile = map.getTiles()[(int)target.getY()][(int)target.getX()];

		return lastSelectedTile;
	}

	protected abstract void updateTarget(int mx, int my);

	public void update(long now) {

		Tile lastSelectedTile = getTargetTile(mx, my);

		if(onMouse) {

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
		return type;
	}

	public int getOffsetX() {
		return map.getOffsetX();
	}

	public int getOffsetY() {
		return map.getOffsetY();
	}

	public void swapGridShow() {
		map.getDrawer().swapDrawGrid();
	}

	public void swapCollisionShow() {
		map.getDrawer().swapDrawCollision();
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
