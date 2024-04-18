import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/* these JUnit tests were completed by Timothy Allec on 11/17/22 with the collaboration
of Vincent, and Phinehas
 */
public class TileManagerTest {
    //MUST PROPERLY TEST ALL PUBLIC METHODS (except drawAll) in TileManager class
    //MUST CALL getTiles method to test the internal state of tiles in TileManager class
    //MUST RUN TEST WITH COVERAGE and SHOW minimum of 85% COVERAGE for TileManager to get the full credit in testing

    @Test (timeout = 5000)
    public void constructorTest() { //Example test provided by the instructor
        TileManager tileManager = new TileManager();
        List<Tile> tileList = tileManager.getTiles(); //use getTiles method to get the state of your tiles
        assertTrue("The constructor should initialize an empty list", tileList.isEmpty());
    }

    @Test (timeout = 5000)
    // for addTile
    public void addTest() { //Example test provided by instructor (uncomment below after you implement addTile method)
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(100, 100, 50, 20, Color.RED);
        Tile tile2 = new Tile(35, 50, 20, 20, Color.blue);
        Tile tile3 = new Tile(45, 170, 30, 90, Color.DARK_GRAY);
        List<Tile> tileList = tileManager.getTiles();

        tileManager.addTile(tile);
        assertEquals("Tiles should be added to the end of the list", tile, tileList.get(0));
        tileManager.addTile(tile2);
        assertEquals("Tiles should be added to the end of the list", tile2, tileList.get(1));
        tileManager.addTile(tile3);
        assertEquals("Tiles should be added to the end of the list", tile3, tileList.get(2));
    }

    // this tests the raiseTest method
    @Test (timeout = 5000)
    public void test1() {
        TileManager tileManager = new TileManager();
        Tile t1 = new Tile(50,50,20,20, Color.green);
        Tile t2 = new Tile(100,150, 20,40, Color.red);
        Tile t3 = new Tile(70, 45, 50,80, Color.lightGray);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(t1);
        tileManager.addTile(t2);
        tileManager.addTile(t3);
        int endList = tileList.size()-1;
        List<Tile> originalTiles= new ArrayList<>(tileList);
        // array list containing the original tile list

        //test for an empty coordinate. nothing should change
        tileManager.raise(110, 20);
        assertEquals(originalTiles, tileList); // nothing should change
        // test for top left of tile
        tileManager.raise(100,150);
        assertEquals(t2, tileList.get(endList));
        // test for bottom right of a tile
        tileManager.raise(70,80);
        assertEquals(t3,tileList.get(endList));

    }

    // this tests the lowerTest method
    @Test (timeout = 5000)
    public void test2() {
        TileManager tileManager = new TileManager();
        Tile t1 = new Tile(50,50,20,20, Color.green);
        Tile t2 = new Tile(100,150, 20,40, Color.red);
        Tile t3 = new Tile(70, 45, 50,80, Color.lightGray);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(t1);
        tileManager.addTile(t2);
        tileManager.addTile(t3);
        List<Tile> originalTiles = new ArrayList<>(tileList);
        // check an empty coordinate
        tileManager.lower(0,110);
        assertEquals(originalTiles, tileList); // nothing should change, lists should be equal

        // check top left of tile
        tileManager.lower(70,45);
        assertEquals(t3, tileList.get(0));

        // check tile on top of the order
        tileManager.lower(50,50);
        assertEquals(t1,tileList.get(0));

        // check bottom right of tile
        tileManager.lower(20,40);
        assertEquals(t1, tileList.get(0));
    }
    // this test is for the delete method
    @Test
    public void test3(){
        TileManager tileManager = new TileManager();
        Tile t1 = new Tile(50,50,20,20, Color.green);
        Tile t2 = new Tile(100,150, 20,40, Color.red);
        Tile t3 = new Tile(70, 45, 50,80, Color.lightGray);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(t1);
        tileManager.addTile(t2);
        tileManager.addTile(t3);
        List<Tile> originalTiles = new ArrayList<>(tileList);

        // check empty coordinate
        tileManager.delete(0,100);
        assertEquals(originalTiles, tileList); // list should not change
        // check top left of tile
        tileManager.delete(50,50);
        assertTrue(!tileList.contains(t1)); //tile clicked on should be removed from list
        // check on top of the ordering
        tileManager.delete(70,45);
        assertTrue(!tileList.contains(t3));
        // check bottom right of tile
        tileManager.delete(70,80);
        assertTrue(!tileList.contains(t3)); // tile should be deleted from the list


    }
    // this test is for the deleteAll method
    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void test4(){
        TileManager tileManager = new TileManager();
        Tile t1 = new Tile(200,1,60,60, Color.green);
        Tile t2 = new Tile(40000,40000, 600000,6000000, Color.red);
        Tile t3 = new Tile(170, 35, 35,100, Color.lightGray);
        Tile t4 = new Tile(4000,0,100,100, Color.black);
        List<Tile> tileList = tileManager.getTiles();
        tileManager.addTile(t1);
        tileManager.addTile(t2);
        tileManager.addTile(t3);
        tileManager.addTile(t4);

        List<Tile> originalTiles = new ArrayList<>(tileList);

        // check any empty coordinate
        tileManager.delete(0,5);
        assertEquals(originalTiles,tileList);
        // check if it deletes all of them
        tileManager.deleteAll(200,40);
        assertTrue(tileList.contains(t2));
        assertTrue(tileList.contains(t4));
        assertFalse(tileList.contains(t1));
        assertFalse(tileList.contains(t3));
    }
//     this test is for the shuffle method
    @Test
    public void test5(){
        TileManager tileManager = new TileManager();
        List<Tile> tileList = tileManager.getTiles();
        // randomize a list of tiles
        Random r = new Random();
        for(int i = 0; i < 20; i++){
            tileManager.addTile
                    (new Tile(r.nextInt(301), //x
                    r.nextInt(201),           //y
                    r.nextInt(50) ,           //width
                    r.nextInt(50),            //height
                    Color.RED));
        }
        List<Tile> originalTiles = new ArrayList<>(tileList);

        //shuffle
        tileManager.shuffle(300,200);
        assertNotEquals(originalTiles, tileList);
    }
    @org.junit.jupiter.api.Test
    @Timeout(value = 10, unit = SECONDS)
    public void shuffleChangesPositionWithinBoundsTest() {
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(400, 20, 80, 60, Color.RED);
        tileManager.addTile(tile);

        tileManager.shuffle(300, 200);

        assertTrue(tile.getX() <= 200);
        assertTrue(tile.getY() <= 200);
    }
}