package br.com.vite.map.selection;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.tile.collision.CollisionTileSet;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.ProceduralTileSet;

public class OrthogonalCollisionMap extends OrthogonalMapEditor {
	
	private ProceduralTileSet tileSet;
	
	private CollisionType selectedType = CollisionType.FREE;
	
	private boolean activeSelection = false;
			
	public OrthogonalCollisionMap(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
		
		tileSet = new CollisionTileSet(tileWidth, tileHeight);
	}
	
	@Override
	public void update(long now) {
		
		//Necessary to update Filler
		map.getTargetTile(mx, my);
		
		if(map.isOnMouse()) {
			
			if(leftPressed) {

				selectedType = getCollisionType(mx, my);
				
				activeSelection = true;
			}
			
		}		
		
	}
		
	private CollisionType getCollisionType(int x, int y) {
				
		if(colide(x, y, 0, 0)) {
			return CollisionType.FREE;
		}
		
		if(colide(x, y, 1, 0)) {
			return CollisionType.BLOCK;
		}
		
		if(colide(x, y, 0, 1)) {
			return CollisionType.HALF_TOP_LEFT;
		}
		
		if(colide(x, y, 1, 1)) {
			return CollisionType.HALF_TOP_RIGHT;
		}
		
		if(colide(x, y, 0, 2)) {
			return CollisionType.HALF_BOTTOM_LEFT;
		}
		
		if(colide(x, y, 1, 2)) {
			return CollisionType.HALF_BOTTOM_RIGHT;
		}
		
		if(colide(x, y, 2, 0)) {
			return CollisionType.UPPER_LEFT;
		}
		
		if(colide(x, y, 3, 0)) {
			return CollisionType.UPPER;
		}
		
		if(colide(x, y, 4, 0)) {
			return CollisionType.UPPER_RIGHT;
		}
				
		if(colide(x, y, 2, 2)) {
			return CollisionType.LOWER_LEFT;
		}
		
		if(colide(x, y, 3, 2)) {
			return CollisionType.LOWER;
		}
		
		if(colide(x, y, 4, 2)) {
			return CollisionType.LOWER_RIGHT;
		}
		
		//For now
		if(colide(x, y, 2, 1)) {
			return CollisionType.BLOCK;
		}
		
		if(colide(x, y, 3, 1)) {
			return CollisionType.BLOCK;
		}
		
		if(colide(x, y, 4, 1)) {
			return CollisionType.BLOCK;
		}
		
		return CollisionType.FREE;
	}
	
	private boolean colide(int x, int y, int column, int line) {
		
		boolean colideX = false;
		boolean colideY = false;
		
		int minX = map.getOffsetX()+(column)*tileWidth;
		int maxX = map.getOffsetX()+(column+1)*tileWidth;
		
		if(x >= minX && x < maxX) {
			colideX = true;
		}

		int minY = map.getOffsetY()+(line)*tileHeight;
		int maxY = map.getOffsetY()+(line+1)*tileHeight;
		
		if(y >= minY && y < maxY) {
			colideY = true;
		}
		
		return colideX && colideY;
	}
		
	@Override
	public void draw(Graphic g) {
		tileSet.getLayer().simpleDraw(g, map.getOffsetX(), map.getOffsetY());
		
		map.draw(g, 0, 0);
	}
	
	public void setTileSet(ProceduralTileSet tileSet) {
		this.tileSet = tileSet;
	}

	public CollisionType getSelectedType() {
		return selectedType;
	}

	public boolean isActiveSelection() {
		return activeSelection;
	}

	public void setActiveSelection(boolean activeSelection) {
		this.activeSelection = activeSelection;
	}
	
}
