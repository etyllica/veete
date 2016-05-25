package br.com.vite.map.selection;

import br.com.vite.tile.collision.CollisionType;

public class SelectedTile {

	protected String id;
	
	protected String setId;
	
	private int x;
	
	private int y;
	
	protected int width;
	
	protected int height;
		
	protected CollisionType collision = CollisionType.FREE;

	public SelectedTile(String id, String setId, int x, int y) {
		super();
		
		this.id = id;
		this.setId = setId;
		this.x = x;
		this.y = y;
	}
	
	public SelectedTile(String id, String setId, int x, int y, CollisionType collision) {
		super();
		
		this.id = id;
		this.setId = setId;
		this.x = x;
		this.y = y;
		this.collision = collision;
	}
	
	public SelectedTile(String id, String setId, int x, int y, int width, int height) {
		this(id, setId, x, y);
		
		this.width = width;
		this.height = height;
	}
	
	public SelectedTile(String id, String setId, int x, int y, int width, int height, CollisionType collision) {
		this(id, setId, x, y, width, height);
		this.collision = collision;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public CollisionType getCollision() {
		return collision;
	}

	public void setCollision(CollisionType collision) {
		this.collision = collision;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((setId == null) ? 0 : setId.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectedTile other = (SelectedTile) obj;
		if (setId == null) {
			if (other.setId != null)
				return false;
		} else if (!setId.equals(other.setId))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
