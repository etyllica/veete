package br.com.vite.tile;

import br.com.etyllica.core.Drawable;

public abstract class TileLayer implements Drawable{

	private long uniqueId;
	
	public TileLayer(long uniqueId){
		super();
		
		this.uniqueId = uniqueId;
	}

	public long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
		
}
