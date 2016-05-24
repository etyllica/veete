package br.com.vite.tool;

import br.com.vite.editor.EditorListener;
import br.com.vite.network.client.MapClient;
import br.com.vite.network.client.MapClientProtocol;
import br.com.vite.network.server.MapServer;
import br.com.vite.tile.Tile;

public class SharingTool implements EditorListener {

	MapServer server;
	MapClient client;

	boolean started = false;

	public void shareAction() {
		if(started) {
			return;
		}

		server = new MapServer();
		server.start();

		started = true;	

		System.out.println("Share");
	}

	public boolean isStarted() {
		return started;
	}

	public MapClientProtocol getMapProtocol() {
		return client.getMapProtocol();
	}
	
	@Override
	public void writeTile(Tile lastSelectedTile, String id) {
		if(!started) {
			return;
		}
		
		int x = lastSelectedTile.getX();
		int y = lastSelectedTile.getY();
		getMapProtocol().sendWriteTile(x, y, id);
	}

	@Override
	public void eraseTile(Tile lastSelectedTile) {
		if(!started) {
			return;
		}
		
		int x = lastSelectedTile.getX();
		int y = lastSelectedTile.getY();
		getMapProtocol().sendEraseTile(x, y);
	}
	
}
