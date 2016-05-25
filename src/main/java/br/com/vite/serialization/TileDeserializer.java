package br.com.vite.serialization;

import java.util.Map;

import br.com.vite.tile.set.TileSet;

import com.google.gson.JsonObject;

public interface TileDeserializer<T> {

	public T deserialize(Map<String, TileSet> tilesets, JsonObject node);
	
}
