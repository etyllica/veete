package br.com.vite.network.client;

import br.com.midnight.client.TCPClient;
import br.com.vite.editor.MapEditor;

public class MapClient extends TCPClient {

	private MapClientProtocol mapProtocol;
	
	public MapClient(String ip, int tcpPort, MapEditor editor) {
		super(ip, tcpPort, true);
		
		mapProtocol = new MapClientProtocol(editor);
		addProtocol(mapProtocol);
	}

	public MapClientProtocol getMapProtocol() {
		return mapProtocol;
	}
	
}
