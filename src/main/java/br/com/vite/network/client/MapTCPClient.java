package br.com.vite.network.client;

import br.com.midnight.client.TCPClient;

public class MapTCPClient extends TCPClient {

	private MapClientProtocol simpleProtocol;
	
	public MapTCPClient(String ip, int tcpPort) {
		super(ip, tcpPort, true);
		
		simpleProtocol = new MapClientProtocol();
		
		addProtocol(simpleProtocol);
	}

	public MapClientProtocol getSimpleProtocol() {
		return simpleProtocol;
	}
	
	@Override
	public void run() {
		super.run();
		
		simpleProtocol.sendHandShake();
	}

}
