package br.com.vite.serialization;


import java.util.Map;

import br.com.vite.tile.set.TileSet;

import com.google.gson.JsonObject;

public interface TileSerializer<T> {

	public JsonObject serialize(Map<String, TileSet> tilesets, T t);
	
}
