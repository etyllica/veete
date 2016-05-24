package br.com.vite.tool;

import br.com.vite.network.client.MapClient;
import br.com.vite.network.client.MapClientProtocol;
import br.com.vite.network.server.MapServer;

public class SharingTool {

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
	
}
