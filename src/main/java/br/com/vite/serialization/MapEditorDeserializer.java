package br.com.vite.serialization;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.com.vite.editor.HexagonalMapEditor;
import br.com.vite.editor.IsometricMapEditor;
import br.com.vite.editor.MapEditor;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MapEditorDeserializer implements JsonDeserializer<MapEditor> {
	
	private Map<Integer, String> tileSets = new HashMap<Integer, String>();
	
	private Map<Integer, SelectedTile> tileIds = new HashMap<Integer, SelectedTile>();
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
	private Map<SelectedTile, ImageTileObject> selectedObjects = new HashMap<SelectedTile, ImageTileObject>();
       
    @Override
	public MapEditor deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
    	
    	JsonObject object = element.getAsJsonObject();
    	
    	int columns = object.get(MapEditorSerializer.JSON_COLUMNS).getAsInt();
    	int lines = object.get(MapEditorSerializer.JSON_LINES).getAsInt();
    	int tileWidth = object.get(MapEditorSerializer.JSON_TILE_WIDTH).getAsInt();
    	int tileHeight = object.get(MapEditorSerializer.JSON_TILE_HEIGHT).getAsInt();
    	
    	MapType mapType = context.deserialize(object.get(MapEditorSerializer.JSON_TYPE), MapType.class);
    	
    	MapEditor editor = createMap(columns, lines, tileWidth, tileHeight, mapType);
    	
    	JsonArray tileSetsNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILESETS);
    	deserializeTileSets(tileSetsNode);
    	
    	JsonArray tilesNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILES);
    	deserializeTiles(tilesNode, tileWidth, tileHeight);
    	
    	JsonArray mapNode = object.getAsJsonArray(MapEditorSerializer.JSON_MAP);
    	deserializeMap(editor, mapNode);
    	
		return editor;
	}
        
    private MapEditor createMap(int columns, int lines, int tileWidth, int tileHeight, MapType type) {
    	
    	switch(type) {
    	default:
    	case ORTHOGONAL:
    		return new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);
    	case ISOMETRIC:
    		return new IsometricMapEditor(columns, lines, tileWidth, tileHeight);
    	case HEXAGONAL:
    		return new HexagonalMapEditor(columns, lines, tileWidth, tileHeight);
    	}    	
    }
    
    private void deserializeTileSets(JsonArray array) {
    
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		int id = node.get("id").getAsInt();
    		String path = node.get("path").getAsString();
    		
    		tileSets.put(id, path);
    	}
    }
    
    private void deserializeTiles(JsonArray array, int tileWidth, int tileHeight) {
    	    	
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		int setId = node.get("set").getAsInt();
    		
    		String path = tileSets.get(setId);
    		
            int x = node.get("xImage").getAsInt();
            int y = node.get("yImage").getAsInt();
            
            
            JsonElement collisionNode = node.get("collision");
            
            CollisionType type = CollisionType.FREE;
            
            if(collisionNode != null) {
            	type = CollisionType.valueOf(node.get("collision").getAsString());
            }
            
            SelectedTile tile = new SelectedTile(path, x, y, tileWidth, tileHeight, type);
    		
            int id = node.get("id").getAsInt();
            
    		tileIds.put(id, tile);
    	}
    }
    
    private void deserializeMap(MapEditor editor, JsonArray array) {
    	
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		    		    		
            int x = node.get("x").getAsInt();
            int y = node.get("y").getAsInt();
            
            if(node.has(MapEditorSerializer.JSON_FLOOR_ID)) {
            	
            	int layerId = node.get(MapEditorSerializer.JSON_FLOOR_ID).getAsInt();
            	
            	ImageTileFloor floor = createSelectedTile(tileIds.get(layerId));
            	
            	editor.getTiles()[y][x].setLayer(floor);
                editor.getTiles()[y][x].setCollision(floor.getCollision());
            }
            
            if(node.has(MapEditorSerializer.JSON_OBJECT_ID)) {
            	
            	int objectId = node.get(MapEditorSerializer.JSON_OBJECT_ID).getAsInt();
            	int w = node.get("w").getAsInt();
            	int h = node.get("h").getAsInt();
            	
            	ImageTileObject obj = createSelectedObject(tileIds.get(objectId), w, h);
            	
            	editor.getTiles()[y][x].setObjectLayer(obj);
                editor.getTiles()[y][x].setCollision(obj.getCollision());
            }            
    		
    	}
    }
    
    private ImageTileFloor createSelectedTile(SelectedTile selectedTile) {
				
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			ImageTileFloor tileFloor = new ImageTileFloor(selectedTile.getPath());
			tileFloor.setLayerBounds(selectedTile.getX(), selectedTile.getY(), selectedTile.getWidth(), selectedTile.getHeight());
			tileFloor.setCollision(selectedTile.getCollision());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
    
    private ImageTileObject createSelectedObject(SelectedTile selectedTile, int w, int h) {
		
    	ImageTileObject obj = selectedObjects.get(selectedTile);
		
		if(obj == null) {
		
			ImageTileObject tileObject = new ImageTileObject(selectedTile.getPath());
			tileObject.setLayerBounds(selectedTile.getX(), selectedTile.getY(), w, h);
			tileObject.setCollision(selectedTile.getCollision());
			
			selectedObjects.put(selectedTile, tileObject);
			
			return tileObject;
		}
		
		return obj;
	}
    
}