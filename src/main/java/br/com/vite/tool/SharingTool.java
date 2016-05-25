package br.com.vite.tool;

import br.com.vite.editor.EditorListener;
import br.com.vite.editor.MapEditor;
import br.com.vite.network.client.MapClient;
import br.com.vite.network.client.MapClientProtocol;
import br.com.vite.network.server.MapServer;
import br.com.vite.tile.Tile;

public class SharingTool implements EditorListener {

	MapEditor editor;
	MapServer server;
	MapClient client;

	boolean started = false;
	int delay = 500;
	
	private static final String LOCAL_HOST = "127.0.0.1";
	
	public SharingTool(MapEditor editor) {
		super();
		this.editor = editor;
		editor.setListener(this);
	}

	public void openServerAction() {
		if(started) {
			return;
		}

		server = new MapServer();
		server.start();

		client = new MapClient(LOCAL_HOST, MapServer.PORT, editor);
		client.start(100);
		
		started = true;
	}
	
	public void joinServerAction(String ip) {
		if(started) {
			return;
		}
		
		client = new MapClient(ip, MapServer.PORT, editor);
		client.start(delay);
		started = true;
	}

	public boolean isStarted() {
		return started;
	}

	public MapClientProtocol getMapProtocol() {
		return client.getMapProtocol();
	}
	
	@Override
	public void writeTile(Tile lastSelectedTile, String tileSetId, String id) {
		if(!started || client == null) {
			return;
		}
		
		int i = lastSelectedTile.getX()/lastSelectedTile.getW();
		int j = lastSelectedTile.getY()/lastSelectedTile.getH();
		
		getMapProtocol().sendWriteTile(i, j, tileSetId, id);
	}

	@Override
	public void eraseTile(Tile lastSelectedTile) {
		if(!started || client == null) {
			return;
		}
		
		int i = lastSelectedTile.getX()/lastSelectedTile.getW();
		int j = lastSelectedTile.getY()/lastSelectedTile.getH();
		getMapProtocol().sendEraseTile(i, j);
	}
	
}
