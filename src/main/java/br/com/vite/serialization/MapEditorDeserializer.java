package br.com.vite.serialization;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.com.vite.editor.HexagonalMapEditor;
import br.com.vite.editor.IsometricMapEditor;
import br.com.vite.editor.MapEditor;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedObjectTile;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;
import br.com.vite.tile.set.TileSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MapEditorDeserializer implements JsonDeserializer<MapEditor> {
	
	private Map<String, TileSet> tileSets = new HashMap<String, TileSet>();
	
	private Map<Integer, SelectedTile> tileIds = new HashMap<Integer, SelectedTile>();
	private Map<Integer, SelectedObjectTile> objectIds = new HashMap<Integer, SelectedObjectTile>();
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
	private Map<SelectedObjectTile, ImageTileObject> selectedObjects = new HashMap<SelectedObjectTile, ImageTileObject>();
       
    @Override
	public MapEditor deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
    	
    	JsonObject object = element.getAsJsonObject();
    	
    	int columns = object.get(MapEditorSerializer.JSON_COLUMNS).getAsInt();
    	int lines = object.get(MapEditorSerializer.JSON_ROWS).getAsInt();
    	int tileWidth = object.get(MapEditorSerializer.JSON_TILE_WIDTH).getAsInt();
    	int tileHeight = object.get(MapEditorSerializer.JSON_TILE_HEIGHT).getAsInt();
    	
    	MapType mapType = context.deserialize(object.get(MapEditorSerializer.JSON_TYPE), MapType.class);
    	
    	MapEditor editor = createMap(columns, lines, tileWidth, tileHeight, mapType);
    	
    	JsonArray tileSetsNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILESETS);
    	deserializeTileSets(tileSetsNode);
    	
    	JsonArray tilesNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILES);
    	deserializeTiles(tilesNode, tileWidth, tileHeight);
    	
    	JsonArray objectsNode = object.getAsJsonArray(MapEditorSerializer.JSON_OBJECTS);
    	if(objectsNode!=null)
    		deserializeObjects(objectsNode);
    	
    	JsonArray mapNode = object.getAsJsonArray(MapEditorSerializer.JSON_MAP);
    	deserializeMap(editor, mapNode);
    	    	
    	for (TileSet tileSet:tileSets.values()) {
    		editor.addTileSet(tileSet);
    	}
    	
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
    		
    		String id = node.get("id").getAsString();
    		String path = node.get("path").getAsString();
    		
    		int rows = node.get(MapEditorSerializer.JSON_ROWS).getAsInt();
    		int columns = node.get(MapEditorSerializer.JSON_COLUMNS).getAsInt();
    		int tileWidth = node.get(MapEditorSerializer.JSON_TILE_WIDTH).getAsInt();
    		int tileHeight = node.get(MapEditorSerializer.JSON_TILE_HEIGHT).getAsInt();
    		MapType type = MapType.valueOf(node.get(MapEditorSerializer.JSON_TYPE).getAsString());
    		
    		TileSet set = new TileSet(rows, columns, tileWidth, tileHeight, type);
    		set.setPath(path);
    		set.setId(id);
    		set.createTiles();
    		
    		tileSets.put(id, set);
    	}
    }
    
    private void deserializeTiles(JsonArray array, int tileWidth, int tileHeight) {
    	   
    	SelectedTileSerializer serializer = new SelectedTileSerializer(tileWidth, tileHeight);
    	
    	for(int i = 0; i < array.size(); i++) {
    		
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		SelectedTile tile = serializer.deserialize(tileSets, node);
    	    
            int id = node.get("id").getAsInt();
            
    		tileIds.put(id, tile);
    	}
    }
    
    private void deserializeObjects(JsonArray array) {
    	
    	SelectedObjectTileSerializer serializer = new SelectedObjectTileSerializer();
    	
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		SelectedObjectTile tile = serializer.deserialize(tileSets, node);
                		
            int id = node.get("id").getAsInt();
            
    		objectIds.put(id, tile);
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
            	            	
            	ImageTileObject obj = createSelectedObject(objectIds.get(objectId));
            	
            	editor.getTiles()[y][x].setObjectLayer(obj);
                editor.getTiles()[y][x].setCollision(obj.getCollision());
            }
    		
    	}
    }
    
    private ImageTileFloor createSelectedTile(SelectedTile selectedTile) {
				
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			TileSet tileSet = tileSets.get(selectedTile.getSetId());
			
			ImageTileFloor tileFloor = new ImageTileFloor(tileSet.getPath(), tileSet.getId());
			tileFloor.setLayerBounds(selectedTile.getX(), selectedTile.getY(), selectedTile.getWidth(), selectedTile.getHeight());
			tileFloor.setCollision(selectedTile.getCollision());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
    
    private ImageTileObject createSelectedObject(SelectedObjectTile selectedTile) {
		
    	ImageTileObject obj = selectedObjects.get(selectedTile);
		
		if(obj == null) {
			
			TileSet tileSet = tileSets.get(selectedTile.getSetId());
			
			ImageTileObject tileObject = new ImageTileObject(tileSet.getPath(), tileSet.getId());
			tileObject.setLayerBounds(selectedTile.getX(), selectedTile.getY(), selectedTile.getWidth(), selectedTile.getHeight());
			tileObject.setCollision(selectedTile.getCollision());
			tileObject.setLabel(selectedTile.getLabel());
			tileObject.setOffsetX(selectedTile.getOffsetX());
			tileObject.setOffsetY(selectedTile.getOffsetY());
			
			selectedObjects.put(selectedTile, tileObject);
			
			return tileObject;
		}
		
		return obj;
	}
    
}