package br.com.vite.serialization;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.vite.editor.MapEditor;
import br.com.vite.map.selection.SelectedObjectTile;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MapEditorSerializer implements JsonSerializer<MapEditor> {

	public static final String JSON_VERSION = "version";
	public static final String JSON_TYPE = "type";
	public static final String JSON_COLUMNS = "columns";
	public static final String JSON_LINES = "lines";
	public static final String JSON_TILE_WIDTH = "tile_width";
	public static final String JSON_TILE_HEIGHT = "tile_height";
	
	public static final String JSON_TILESETS = "tilesets";
	public static final String JSON_OBJECTS = "objects";
	public static final String JSON_TILES = "tiles";
	public static final String JSON_MAP = "map";
	public static final String JSON_ID = "id";
	public static final String JSON_FLOOR_ID = "floor_id";
	public static final String JSON_OBJECT_ID = "obj_id";
	
	private static final int MAP_VERSION = 1;
	
    private int uniqueId = 0;
    
    private int uniqueObjectId = 0;
    
    private int tileSetId = 0;
   
    private Map<SelectedTile, Integer> uniqueIds;
    
    private Map<SelectedObjectTile, Integer> objectIds;
        
    private Map<String, Integer> tileSets;
    
    @Override
    public JsonElement serialize(MapEditor editor, Type type,
            JsonSerializationContext context) {

        final int lines = editor.getLines();
        final int columns = editor.getColumns();
       
        JsonObject element = new JsonObject();
       
        element.add(JSON_VERSION, new JsonPrimitive(MAP_VERSION));
        element.add(JSON_TYPE, context.serialize(editor.getType()));
        element.add(JSON_COLUMNS, context.serialize(columns));
        element.add(JSON_LINES, context.serialize(lines));
        element.add(JSON_TILE_WIDTH, context.serialize(editor.getTileWidth()));
        element.add(JSON_TILE_HEIGHT, context.serialize(editor.getTileHeight()));
               
        tileSets = new HashMap<String, Integer>();
        
        uniqueIds = new HashMap<SelectedTile, Integer>();
        
        objectIds = new HashMap<SelectedObjectTile, Integer>();
        
        JsonArray array = serializeTileArray(editor.getTiles());
        
        element.add(JSON_TILESETS, serializeTileSets());
        
        element.add(JSON_TILES, serializeFloorIds());
        
        element.add(JSON_OBJECTS, serializeObjectIds());
                      
        element.add(JSON_MAP, array);

        return element;
    }
    
    private JsonArray serializeTileSets() {
        
        JsonArray array = new JsonArray();
        
        for(Entry<String, Integer> entry: tileSets.entrySet()) {
        	
        	JsonObject tilesetNode = new JsonObject();
        	
        	tilesetNode.addProperty(JSON_ID, entry.getValue());
        	tilesetNode.addProperty("path", entry.getKey());
        	
        	array.add(tilesetNode);
        }
        
        return array;
    }
       
    private JsonArray serializeFloorIds() {
       
    	SelectedTileSerializer serializer = new SelectedTileSerializer();
    	
        JsonArray array = new JsonArray();
       
        for(Entry<SelectedTile, Integer> entry: uniqueIds.entrySet()) {
           
        	SelectedTile tile = entry.getKey();
        	
            JsonObject tileNode = serializer.serialize(tileSets, tile);
           
            tileNode.addProperty(JSON_ID, entry.getValue());
                       
            array.add(tileNode);
        }
       
        return array;
    }
    
    private JsonArray serializeObjectIds() {
        
    	SelectedObjectTileSerializer serializer = new SelectedObjectTileSerializer();
    	
        JsonArray array = new JsonArray();
       
        for(Entry<SelectedObjectTile, Integer> entry: objectIds.entrySet()) {
           
        	SelectedObjectTile tile = entry.getKey();
        	
            JsonObject tileNode = serializer.serialize(tileSets, tile);
           
            tileNode.addProperty(JSON_ID, entry.getValue());            
                       
            array.add(tileNode);
        }
       
        return array;
    }
   
    private JsonArray serializeTileArray(Tile[][] tiles) {
       
        int columns = tiles[0].length;
        int lines = tiles.length;
       
        JsonArray array = new JsonArray();

        for(int j = 0; j < lines; j++) {
           
            for(int i = 0; i < columns; i++) {
               
                Tile tile = tiles[j][i];
               
                if(tile.getLayer() != null) {
                	JsonObject tileNode = serializeTileFloor(tile, i, j);
                	  
                	if(tile.getObjectLayer() != null) {
                		serializeTileObject(tile, i, j, tileNode);
                    }
                	                	
                	array.add(tileNode);
                	
                } else if(tile.getObjectLayer() != null) {
                	array.add(serializeTileObject(tile, i, j));
                }
                
            }
        }
       
        return array;
    }
    
    private JsonObject serializeTileFloor(Tile tile, int i, int j) {
    	 
         JsonObject tileNode = new JsonObject();
        
         SelectedTile selection = new SelectedTile(tile.getLayer().getPath(), tile.getLayer().getX(), tile.getLayer().getY(), tile.getCollision());
        
         tileNode.addProperty("x", i);
         tileNode.addProperty("y", j);
         tileNode.addProperty(JSON_FLOOR_ID, getUniqueId(selection));
                 
         return tileNode;
    }
    
    private JsonObject serializeTileObject(Tile tile, int i, int j) {
    	
    	JsonObject tileNode = new JsonObject();
    	
        return serializeTileObject(tile, i, j, tileNode);
    }
    
    private JsonObject serializeTileObject(Tile tile, int i, int j, JsonObject tileNode) {
    	    	
    	ImageTileObject layer = tile.getObjectLayer();
    	
    	tileNode.addProperty("x", i);
        tileNode.addProperty("y", j);
                
        SelectedObjectTile selection = new SelectedObjectTile(layer);
        
        tileNode.addProperty(JSON_OBJECT_ID, getUniqueObjectId(selection));  
                
        return tileNode;
    }
   
    private int getUniqueObjectId(SelectedObjectTile obj) {
    	    	
    	if(!objectIds.containsKey(obj)) {
    	
    		int id = uniqueObjectId;
    		    		
    		objectIds.put(obj, id);
    		uniqueObjectId++;
            
            generateTileSetId(obj.getPath());
            
            return id;
        }
       
        return objectIds.get(obj);
    	
    }
    
    private int getUniqueId(SelectedTile selectedTile) {
           	
        if(!uniqueIds.containsKey(selectedTile)) {
        	int id = uniqueId;        	
            uniqueIds.put(selectedTile, id);
            uniqueId++;
            
            generateTileSetId(selectedTile.getPath());
            
            return id;
        }
       
        return uniqueIds.get(selectedTile);
    }
       
    private void generateTileSetId(String path) {
    	
    	if(!tileSets.containsKey(path)) {
        	tileSets.put(path, tileSetId);
        	tileSetId++;
        }    	
    }
    
}