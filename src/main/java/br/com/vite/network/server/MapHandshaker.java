package br.com.vite.network.server;

import br.com.midnight.protocol.handshake.StringHandShaker;
import br.com.vite.network.client.MapClientProtocol;

public class MapHandshaker extends StringHandShaker {

	@Override
	public String handshakeText(String sessionId) {
		return MapClientProtocol.PREFIX_VEETE+" Hello "+sessionId+"!";
	}

}
