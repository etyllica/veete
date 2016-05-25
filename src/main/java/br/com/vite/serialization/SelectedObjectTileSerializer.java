package br.com.vite.serialization;

import java.util.Map;

import br.com.vite.map.selection.SelectedObjectTile;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.TileSet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SelectedObjectTileSerializer implements TileSerializer<SelectedObjectTile>, TileDeserializer<SelectedObjectTile> {

	public JsonObject serialize(Map<String, TileSet> tilesets, SelectedObjectTile tile) {
		
		JsonObject tileNode = new JsonObject();
		
		tileNode.addProperty(MapEditorSerializer.JSON_SET, tile.getSetId());
		tileNode.addProperty("label", tile.getLabel());
		
		tileNode.addProperty("id", tile.getId());
		tileNode.addProperty("w", tile.getWidth());
		tileNode.addProperty("h", tile.getHeight());
		tileNode.addProperty("xImage", tile.getX());
        tileNode.addProperty("yImage", tile.getY());
        tileNode.addProperty("dx", tile.getOffsetX());
		tileNode.addProperty("dy", tile.getOffsetY());
		        
        tileNode.addProperty("collision", tile.getCollision().toString());
        
        return tileNode;
		
	}

	@Override
	public SelectedObjectTile deserialize(Map<String, TileSet> tilesets, JsonObject node) {

		String setId = node.get("set").getAsString();
		String path = tilesets.get(setId).getPath();

		String id = node.get("id").getAsString();
		
        int x = node.get("xImage").getAsInt();
        int y = node.get("yImage").getAsInt();
                    
        JsonElement collisionNode = node.get("collision");
        
        CollisionType type = CollisionType.FREE;
        
        if(collisionNode != null) {
        	type = CollisionType.valueOf(node.get("collision").getAsString());
        }
        
        SelectedObjectTile tile = new SelectedObjectTile(id, path, x, y, type);
        
        String label = node.get("label").getAsString();
        tile.setLabel(label);
        
        int w = node.get("w").getAsInt();        
        tile.setWidth(w);
        
        int h = node.get("h").getAsInt();
        tile.setHeight(h);
        
        int dx = node.get("dx").getAsInt();
        tile.setOffsetX(dx);
        
        int dy = node.get("dy").getAsInt();
        tile.setOffsetY(dy);
		
		return tile;
		
	}
	
}
