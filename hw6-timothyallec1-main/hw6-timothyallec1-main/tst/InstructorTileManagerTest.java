import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Edited By: Reuella Jacob
 * Last Edited On: 5/26/2021
 * Changes Made: Updated the tests to use JUnit 5 instead of JUnit 6
 */

public class InstructorTileManagerTest {
    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void constructorTest() {
        TileManager tileManager = new TileManager();
        List<Tile> tileList = tileManager.getTiles();
        Assertions.assertTrue(tileList.isEmpty(), "The constructor should initialize an empty list");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void addTileTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(100, 100, 50, 20, Color.RED);
        Tile tile2 = new Tile(35, 50, 20, 20, Color.blue);
        Tile tile3 = new Tile(45, 170, 30, 90, Color.DARK_GRAY);
        List<Tile> tileList = tileManager.getTiles();

        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        Assertions.assertEquals(tile, tileList.get(0), "Tiles should be added to the end of the list");
        Assertions.assertEquals(tile2, tileList.get(1), "Tiles should be added to the end of the list");
        Assertions.assertEquals(tile3, tileList.get(2), "Tiles should be added to the end of the list");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void raiseTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(140, 30, 38, 100, Color.RED);
        Tile tile2 = new Tile(35, 50, 20, 20, Color.BLUE);
        Tile tile3 = new Tile(35, 40, 30, 90, Color.DARK_GRAY);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);
        int end = tileList.size() - 1;
        List<Tile> copyTileList = new ArrayList<>(tileList);

        //Check empty coordinate
        tileManager.raise(150, 131);
        Assertions.assertEquals(copyTileList, tileList, "The list should not be changed if no tile is clicked");

        //Left boundary of tile
        tileManager.raise(140, 30);
        Assertions.assertEquals(tile, tileList.get(end), "The tile clicked should be moved to the end of the list");

        //Check tile on top of z-ordering
        tileManager.raise(55, 70);
        Assertions.assertEquals(tile3, tileList.get(end), "The tile clicked should move to the end of the list");

        //Check Right Boundary
        tileManager.raise(178, 130);
        Assertions.assertEquals(tile, tileList.get(end), "The tile clicked should move to the end of the list");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void lowerTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(200, 200, 40, 50, Color.GREEN);
        Tile tile2 = new Tile(0, 5, 26, 31, Color.GRAY);
        Tile tile3 = new Tile(194, 180, 22, 60, Color.DARK_GRAY);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);
        List<Tile> copyTileList = new ArrayList<>(tileList);

        //Check empty coordinate
        tileManager.lower(193, 199);
        Assertions.assertEquals(copyTileList, tileList, "The list should not be changed if no tile is clicked");

        //Left boundary of tile
        tileManager.lower(194, 240);
        Assertions.assertEquals(tile3, tileList.get(0), "The tile clicked should be moved to the start of the list");

        //Check tile on top of z-ordering
        tileManager.lower(206, 200);
        Assertions.assertEquals(tile, tileList.get(0), "The tile clicked should move to the end of the list");

        //Check Right Boundary
        tileManager.lower(26, 31);
        Assertions.assertEquals(tile2, tileList.get(0), "The tile clicked should move to the end of the list");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void deleteTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(0, 0, 26, 110, Color.GRAY);
        Tile tile2 = new Tile(250, 0, 30, 20, Color.YELLOW);
        Tile tile3 = new Tile(250, 0, 30, 20, Color.ORANGE);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);
        List<Tile> copyTileList = new ArrayList<>(tileList);

        //Check empty coordinate
        tileManager.delete(0, 111);
        Assertions.assertEquals(copyTileList, tileList, "The list should not be changed if no tile is clicked");

        //Left boundary of tile
        tileManager.delete(0, 0);
        Assertions.assertFalse(tileList.contains(tile), "The tile clicked should be removed from the list");

        //Check tile on top of z-ordering
        tileManager.delete(250, 0);
        Assertions.assertFalse(tileList.contains(tile3), "The tile clicked should be removed from the list");

        //Check Right Boundary
        tileManager.delete(280, 20);
        Assertions.assertFalse(tileList.contains(tile2), "The tile clicked should be removed from the list");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void deleteAllTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(400, 1, 54, 51, Color.GRAY);
        Tile tile2 = new Tile(30000, 30000, 300000, 2000000, Color.YELLOW);
        Tile tile3 = new Tile(370, 35, 35, 100, Color.BLUE);
        Tile tile4 = new Tile(2500, 0, 35, 20, Color.ORANGE);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);
        tileManager.addTile(tile4);
        List<Tile> copyTileList = new ArrayList<>(tileList);

        //Check empty coordinate
        tileManager.delete(0, 5);
        Assertions.assertEquals(copyTileList, tileList, "The list should not be changed if no tile is clicked");

        //Check coordinate
        tileManager.deleteAll(400, 40);
        Assertions.assertTrue(tileList.contains(tile2));
        Assertions.assertTrue(tileList.contains(tile4));
        Assertions.assertFalse(tileList.contains(tile));
        Assertions.assertFalse(tileList.contains(tile3));
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void listShuffledTest() {
        TileManager tileManager = new TileManager();
        List<Tile> tileList = tileManager.getTiles();
        //Initialize random list of tiles
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            tileManager.addTile(new Tile(random.nextInt(301), random.nextInt(201),
                    random.nextInt(30) + 20, random.nextInt(30) + 20, Color.BLUE));
        }
        List<Tile> copyTileList = new ArrayList<>(tileList);

        //Shuffle Tiles
        tileManager.shuffle(300, 200);

        Assertions.assertNotEquals(tileList, copyTileList, "List should be shuffled");
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void shuffleChangesPositionWithinBoundsTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(200, 10, 50, 50, Color.GRAY);
        tileManager.addTile(tile);

        tileManager.shuffle(300, 200);

        Assertions.assertTrue(tile.getX() <= 250, "Position should not go out of bounds");
        Assertions.assertTrue(tile.getY() <= 150, "Position should not go out of bounds");
    }
}
