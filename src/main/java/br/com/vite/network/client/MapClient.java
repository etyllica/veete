package br.com.vite.network.client;

import br.com.midnight.client.TCPClient;

public class MapClient extends TCPClient {

	private MapClientProtocol mapProtocol;
	
	public MapClient(String ip, int tcpPort) {
		super(ip, tcpPort, true);
		
		mapProtocol = new MapClientProtocol();
		addProtocol(mapProtocol);
	}

	public MapClientProtocol getMapProtocol() {
		return mapProtocol;
	}
	
}
