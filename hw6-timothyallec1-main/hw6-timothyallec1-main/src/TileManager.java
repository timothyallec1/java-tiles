import java.awt.*;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

/**
 * Add your own comments here
 */
public class TileManager {
    private List<Tile> tiles; //DO NOT MODIFY THIS LINE
    //Implement your own code here
    // adds the given tile to the end of the list of tiles

    public TileManager(){
        tiles = new ArrayList<>();
    }
    public void addTile(Tile rect){
        tiles.add(rect);

    }
    //this causes all the tiles to draw themselves on the screen
    public void drawAll(Graphics g){
        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).draw(g);
        }
    }
    //when user left-clicks, moves tile to the top
    public void raise(int x, int y) {
        int finalIndex = -1;

        for(int i = 0; i < tiles.size(); i++){
            if(x >= tiles.get(i).getX() && x <= tiles.get(i).getX() + tiles.get(i).getHeight()
            && y >= tiles.get(i).getY() && y <= tiles.get(i).getY() + tiles.get(i).getHeight()){
                finalIndex = i;
            }
        }
        if(finalIndex > -1){
            tiles.add(tiles.get(finalIndex));
            tiles.remove(finalIndex);
        }
    }

    //when user shift-left-clicks, moves tile to the bottom
    public void lower(int x, int y){
        int finalIndex = -1;
        for(int i = 0; i < tiles.size(); i++){
            if(x >= tiles.get(i).getX() && x <= tiles.get(i).getX() + tiles.get(i).getWidth()
            && y >= tiles.get(i).getY() && y <= tiles.get(i).getY() + tiles.get(i).getY() + tiles.get(i).getHeight()){
                finalIndex =i;
            }
        }
        if(finalIndex > -1){
            tiles.add(0, tiles.get(finalIndex));
            tiles.remove(finalIndex+1);
        }
    }

    //when user right-clicks, deletes the tile
    public void delete(int x, int y){

        int finalIndex = -1;
        for(int i = 0; i < tiles.size(); i++){
            if(x >= tiles.get(i).getX() && x <= tiles.get(i).getX() + tiles.get(i).getWidth()
                    && y >= tiles.get(i).getY() && y <= tiles.get(i).getY() + tiles.get(i).getY() + tiles.get(i).getHeight()){
                finalIndex = i;
            }
        }
        if(finalIndex > -1){
            tiles.remove(finalIndex);
        }

    }
    //when user shift-right clicks, delete all tiles
    public void deleteAll(int x, int y) {
        int finalIndex = -1;
        for (int i = 0; i < tiles.size(); i++) {
            if (x >= tiles.get(i).getX() && x <= tiles.get(i).getX() + tiles.get(i).getWidth()
                    && y >= tiles.get(i).getY() && y <= tiles.get(i).getY() + tiles.get(i).getY() + tiles.get(i).getHeight()) {
                finalIndex = i;
                if (finalIndex > -1) {
                    tiles.remove(finalIndex);
                }
            }
        }
    }
    /*when user types "S", reorders tiles in a random order,
     moves every tile to a random x/y position */
    public void shuffle(int width, int height) {
        Collections.shuffle(tiles);
        for (int i = 0; i < tiles.size(); i++) {
            int tempWidth = tiles.get(i).getWidth();
            int tempHeight = tiles.get(i).getHeight();

            Random random = new Random();

            int randomX = random.nextInt(width - tempWidth);
            tiles.get(i).setX(randomX);

            int randomY = random.nextInt(height = tempHeight);
            tiles.get(i).setY(randomY);
        }
    }

    //*** FOR TESTING PURPOSE ONLY ****//
    //SHOULD USE THIS METHOD ONLY IN J-UNIT TEST CODE
    //DO NOT MODIFY THIS METHOD
    public List<Tile> getTiles() {
        return tiles;
    }
}