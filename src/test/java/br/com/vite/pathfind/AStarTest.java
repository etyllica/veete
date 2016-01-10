package br.com.vite.pathfind;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.map.Map;
import br.com.vite.map.OrthogonalMap;
import br.com.vite.tile.collision.CollisionType;

public class AStarTest {

	private Map map;
	private PathFinder pathFinder;
	
	private static final int COLUMNS = 5;
	private static final int LINES = 5;
	private static final int SIZE = 1;
	
	@Before
	public void setUp() {
		map = new OrthogonalMap(COLUMNS, LINES, SIZE, SIZE);
		map.createTiles();
		
		pathFinder = new AStar(map);
		pathFinder.init();
	}
	
	@Test
	public void testInit() {
		
		//Block the tile [1][1]
		map.getTiles()[1][1].setCollision(CollisionType.BLOCK);
		
		List<PointInt2D> path = pathFinder.calculate(0,0,3,3);
		Assert.assertFalse(path.isEmpty());
		Assert.assertEquals(5, path.size());
		Assert.assertEquals(new PointInt2D(0, 0), path.get(0));
		Assert.assertEquals(new PointInt2D(0, 1), path.get(1));
		Assert.assertEquals(new PointInt2D(1, 2), path.get(2));
		Assert.assertEquals(new PointInt2D(2, 3), path.get(3));
		Assert.assertEquals(new PointInt2D(3, 3), path.get(4));
	}
}
