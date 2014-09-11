package br.com.vite.map.selection;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.tile.Tile;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.ProceduralTileSet;

public class OrthogonalCollisionMap extends OrthogonalMapEditor {
	
	private ProceduralTileSet tileSet;
	
	private CollisionMapListener listener;
		
	public OrthogonalCollisionMap(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
	}
	
	@Override
	public void update(long now) {
		
		Tile lastSelectionTile = map.getTargetTile(mx, my);
		
		if(map.isOnMouse()) {
			
			if(leftPressed) {

				int x = lastSelectionTile.getX();
				int y = lastSelectionTile.getY();
				
				int tileWidth = map.getTileWidth();
				int tileHeight = map.getTileHeight();
				
				//ImageTileFloor selectedTile = createSelectedTile(tileSet.getLayer().getPath(), x, y, tileWidth, tileHeight);

				CollisionType type = getCollisionType(x, y);
				
				//Verify Block
				lastSelectionTile.setCollision(type);
				
				notifySelectedTileCollision(CollisionType.BLOCK);
			}
		}
	}
	
	private void notifySelectedTileCollision(CollisionType type) {
		if(listener == null)
			return;
		
		listener.setCollisionTile(type);
	}
	
	private CollisionType getCollisionType(int x, int y) {
				
		return CollisionType.BLOCK;
	}
	
	@Override
	public void draw(Graphic g) {
		tileSet.getLayer().simpleDraw(g, map.getOffsetX(), map.getOffsetY());
		
		map.draw(g, 0, 0);
	}
	
	public void setListener(CollisionMapListener listener) {
		this.listener = listener;
	}

	public void setTileSet(ProceduralTileSet tileSet) {
		this.tileSet = tileSet;
	}

}
