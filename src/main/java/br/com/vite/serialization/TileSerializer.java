package br.com.vite.serialization;


import java.util.Map;

import com.google.gson.JsonObject;

public interface TileSerializer<T> {

	public JsonObject serialize(Map<String, Integer> tilesets, T t);
	
}
