package br.com.vite.serialization;

import java.util.Map;

import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.collision.CollisionType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SelectedTileSerializer implements TileSerializer<SelectedTile>, TileDeserializer<SelectedTile> {
	
	private int tileWidth = 0;
	
	private int tileHeight = 0;
		
	public SelectedTileSerializer() {
		super();	
	}

	public SelectedTileSerializer(int tileWidth, int tileHeight) {
		super();
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public JsonObject serialize(Map<String, Integer> tilesets, SelectedTile tile) {
		
		JsonObject tileNode = new JsonObject();
        
		tileNode.addProperty("set", tilesets.get(tile.getPath()));
        tileNode.addProperty("xImage", tile.getX());
        tileNode.addProperty("yImage", tile.getY());
        tileNode.addProperty("collision", tile.getCollision().toString());
        
        return tileNode;		
	}

	@Override
	public SelectedTile deserialize(Map<Integer, String> tilesets, JsonObject node) {
				
		int setId = node.get("set").getAsInt();
		
		String path = tilesets.get(setId);
		
        int x = node.get("xImage").getAsInt();
        int y = node.get("yImage").getAsInt();
                
        JsonElement collisionNode = node.get("collision");
        
        CollisionType type = CollisionType.FREE;
        
        if(collisionNode != null) {
        	type = CollisionType.valueOf(node.get("collision").getAsString());
        }
        
        SelectedTile tile = new SelectedTile(path, x, y, tileWidth, tileHeight, type);
        
        return tile;
	}
	
}
