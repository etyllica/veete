package br.com.vite.map.selection;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.tile.Tile;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.set.TileSet;

public class OrthogonalFloorSelection extends OrthogonalMapEditor {
	
	private TileSet tileSet;
	
	private SelectionMapListener listener;
	
	private OrthogonalCollisionMap collisionMap;
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
	
	public OrthogonalFloorSelection(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
	}
	
	public OrthogonalFloorSelection(int tileWidth, int tileHeight, TileSet tileSet) {
		super(tileSet.getColumns(), tileSet.getLines(), tileWidth, tileHeight);
		
		setTileSet(tileSet);		
	}

	public Map<SelectedTile, ImageTileFloor> getSelectedTiles() {
		return selectedTiles;
	}

	public void setSelectedTiles(Map<SelectedTile, ImageTileFloor> selectedTiles) {
		this.selectedTiles = selectedTiles;
	}
	
	@Override
	public void update(long now) {
		
		map.updateTarget(mx, my);
				
		if(map.isOnTarget()) {
						
			if(leftPressed) {
				
				int x = map.getLastTarget().getX();
				int y = map.getLastTarget().getY();
				
				int tileWidth = map.getTileWidth();
				int tileHeight = map.getTileHeight();
								
				ImageTileFloor selectedTile = null;
				
				handleCollisionMap(map.getLastTarget());
								
				selectedTile = createSelectedTile(tileSet.getLayer().getPath(), x, y, 
						tileWidth, tileHeight, map.getLastTarget().getCollision());
								
				notifySelectedFloorTile(selectedTile);				
			}
		}
	}
	
	private void handleCollisionMap(Tile tile) {
		
		if(collisionMap == null)
			return;
		
		if(collisionMap.isActiveSelection()) {
			
			CollisionType selectedType = getCollisionType();
			
			tile.setCollision(selectedType);
			
			collisionMap.setActiveSelection(false);
		}
		
	}
	
	private CollisionType getCollisionType() {
		if(collisionMap == null) {
			return CollisionType.FREE;
		}
		
		return collisionMap.getSelectedType();
	}
	
	private void notifySelectedFloorTile(ImageTileFloor selectedTile) {
		if(listener == null)
			return;
		
		listener.setFloorTile(selectedTile);
	}
		
	private ImageTileFloor createSelectedTile(String path, int x, int y, int width, int height) {
		
		SelectedTile selectedTile = new SelectedTile(path, x, y, width, height);
		
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			ImageTileFloor tileFloor = new ImageTileFloor(path);
			tileFloor.setLayerBounds(x, y, map.getTileWidth(), map.getTileHeight());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
	
	private ImageTileFloor createSelectedTile(String path, int x, int y, int width, int height, CollisionType collision) {
		
		ImageTileFloor floor = createSelectedTile(path, x, y, width, height);
		
		floor.setCollision(collision);
		
		return floor;
	}
	
	@Override
	public void draw(Graphic g) {
		tileSet.getLayer().simpleDraw(g, map.getOffsetX(), map.getOffsetY());
		
		super.draw(g);
	}
	
	public void setListener(SelectionMapListener listener) {
		this.listener = listener;
	}

	public TileSet getTileSet() {
		return tileSet;
	}

	public void setTileSet(TileSet tileSet) {
		this.tileSet = tileSet;
		
		updateCollisions(tileSet);
	}
	
	private void updateCollisions(TileSet tileSet) {
		
		for (int j = 0; j < tileSet.getLines(); j++) {
			for (int i = 0; i < tileSet.getColumns(); i++) {
				map.getTiles()[j][i].setCollision(tileSet.getCollision()[j][i]);		
			}
		}
		
	}

	public OrthogonalCollisionMap getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(OrthogonalCollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}
	
}
