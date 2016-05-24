package br.com.vite.map;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.tile.colider.OrthogonalTileColider;
import br.com.vite.tile.creator.OrthogonalTileCreator;
import br.com.vite.tile.drawer.OrthogonalTileDrawer;
import br.com.vite.tile.filler.OrthogonalTileFiller;

public class OrthogonalMap extends Map {
	
	public OrthogonalMap(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);

		type = MapType.ORTHOGONAL;
		
		creator = new OrthogonalTileCreator(tileWidth, tileHeight);
		collider = new OrthogonalTileColider(tileWidth, tileHeight);
		drawer = new OrthogonalTileDrawer(tileWidth, tileHeight);
		filler = new OrthogonalTileFiller(tileWidth, tileHeight);		
	}
	
	public void getIndex(int px, int py, PointInt2D out) {
		int column = (int)((px-x)/tileWidth);
		int line = (int)((py-y)/tileHeight);
		
		out.setLocation(column, line);
	}
	
	public boolean updateTarget(int mouseX, int mouseY, PointInt2D target) {
		
		/*if(mouseY < offsetY || mouseX < offsetX) {
			return false;
		}*/
		
		int column = (int)((mouseX-x)/tileWidth);
		int line = (int)((mouseY-y)/tileHeight);

		boolean overLine = false;
		boolean overColumn = false;
		
		if(line < 0)
			line = 0;
		else if(line >= rows)
			line = rows-1;
		else
			overLine = true;
		
		if(column < 0)
			column = 0;
		else if(column >= columns)
			column = columns-1;
		else
			overColumn = true;
		
		onTarget = overLine&&overColumn;

		target.setLocation(column, line);
		return onTarget;
	}
	
}
