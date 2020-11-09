// Ryan Ahn
// November 4, 2020
// Lab 1: Tiles
// Tile Manager: Stores the list of tiles as ArrayList.
// The methods inside this class encompass functionality of making changes to the list through mouse clicks.
// - A left-click raises a tile to the top of the Z-order.
// - A Shift-left-click lowers a tile to the bottom of the Z-order.
// - A right-click (or a Ctrl-left-click for Mac people) deletes a tile.
// - A Shift-right-click (or a Shift-Ctrl-left-click for Mac people) deletes all tiles touching the mouse point.


import java.util.*;
import java.awt.*;

class TileManager{


   // Creates an ArrayList: A list of tiles.
   private ArrayList<Tile> tiles = new ArrayList<Tile>();
   
   /* There is no need for a constructor ("public TileManager()") as the constructor would notv take
   in any variables and there will be no need to initialize/declare variables. */
   
   // This method adds a tile given (the parameter) to the end of the ArrayList/list of tiles.
   public void addTile(Tile rect){
   
      tiles.add(rect);
      
   }
   
   
   /* This method "draws" all of the tiles in the ArrayList/list of tiles.
   This method will place/draw all te tiles on the screen using the given graphical pen (parameter). */
   public void drawAll(Graphics g){
   
      for (int a = 0; a < tiles.size(); a++){
         (tiles.get(a)).draw(g);
      }
   
   }

   
   /* This is a method that is called from other methods to avoid repetition.
   This method is used in the classes "raise", "lower", "delete", and "deleteAll".
   This method takes in the x, y coordinates that the mouse/cursor has clicked and
   also takes in tile, and checks if the mouse/cursor on the screen clicked on that tile,
   and returns true if it did, and false if it didn't.*/
   private boolean mouseTiles(int x, int y, Tile p){
   
      if ((x >= p.getX()) && (x < (p.getX() + p.getWidth())) && (y >= p.getY()) && (y < (p.getY() + p.getHeight()))){
         return true;
      }
      else{
         return false;
      }
   
   }
   
   /* In this method, the x, y coordinates of where the user left-clicked with the mouse/cursor
   are passed, then it checks if the user left-clicked on a tile using the "mouseTiles" method above,
   and then takes the topmost of the tiles the user left-clicked on and moves it to the very top of the tiles
   essentially, moving that tile to the end of the ArrayList/list. */
   /* The "break" stops the code from going any further after a wanted answer is given so that it does not
   give unwanted results. If the "break" is not there, the code can continue after an answer and will
   result in the code executing different/excess things than we want. */
   // If the user did not click on any tiles, then nothing happens.
   public void raise(int x, int y){
   
      for (int i = (tiles.size() - 1); i >= 0; i--){
         if (mouseTiles(x, y, tiles.get(i)) == true){
            tiles.add(tiles.get(i));
            tiles.remove(i);
            break;
         }
      }

   }
   
   
   /* In this method, the x, y coordinates of where the user Shift-left-clicked with the mouse/cursor
   are passed, then it checks if the user Shift-left-clicked on a tile using the "mouseTiles" method,
   and then takes the topmost of the tiles the user Shift-left-clicked on and moves it to the very bottom of the tiles
   essentially, moving that tile to the front of the ArrayList/list. */
   /* The "break" stops the code from going any further after a wanted answer is given so that it does not
   give unwanted results. If the "break" is not there, the code can continue after an answer and will
   result in the code executing different/excess things than we want. */
   // If the user did not click on any tiles, then nothing happens.
   public void lower(int x, int y){
   
      for (int j = (tiles.size() - 1); j >= 0; j--){
         if (mouseTiles(x, y, tiles.get(j)) == true){
            tiles.add(0, tiles.get(j));
            tiles.remove(j + 1);
            break;
         }
      }

   }
 
 
   /* In this method, the x, y coordinates of where the user right-clicked with the mouse/cursor
   are passed, then it checks if the user right-clicked on a tile using the "mouseTiles" method,
   and then takes the topmost of the tiles the user right-clicked on and deletes it,
   essentially, removing that tile from the ArrayList/list. */
   /* The "break" stops the code from going any further after a wanted answer is given so that it does not
   give unwanted results. If the "break" is not there, the code can continue after an answer and will
   result in the code executing different/excess things than we want. */
   // If the user did not click on any tiles, then nothing happens.
   public void delete(int x, int y){
   
      for (int k = (tiles.size() - 1); k >= 0; k--){
         if (mouseTiles(x, y, tiles.get(k)) == true){
            tiles.remove(k);
            break;
         }
      }

   }


   /* In this method, the x, y coordinates of where the user Shift-right-clicked with the mouse/cursor
   are passed, then it checks if the user Shift-right-clicked on a tile using the "mouseTiles" method above,
   and then takes all of the tiles the user Shift-right-clicked on and deletes all of them,
   essentially, removing those tile from the ArrayList/list. */
   // If the user did not click on any tiles, then nothing happens.
   public void deleteAll(int x, int y){
   
      for (int b = (tiles.size() - 1); b >= 0; b--){
         if (mouseTiles(x, y, tiles.get(b)) == true){
            tiles.remove(b);
         }
      }
   
   }


   /* In this method, when the user types "s", it reorders the tiles in the ArrayList/list,
   essentially changing the order of which tiles come first (go under/over) on the screen,
   and also changes the location of every tile to a new random position by moving all the tiles
   to a new random x/y pixel position. All the tiles (pixles of the tiles) that are rearranged
   and moved fit within the given (parameters) width and height. */
   // "(int)(Math.random() * ((max - min) + 1)) + min" is used to get a true new random position.
   /* It is assummed that the passed width and height aren't too small to not fit all the pixels
   of each tile in the screen. */
   public void shuffle(int width, int height){
      
      // This command reorders/rearranges the tiles ArrayList/list randomly.
      Collections.shuffle(tiles);
      
      int xPos;
      int yPos;
      
      for (int m = 0; m < tiles.size(); m++){
         xPos = (int)(Math.random() * ((width - tiles.get(m).getWidth()) + 1));
         yPos = (int)(Math.random() * ((height - tiles.get(m).getHeight()) + 1));
         /* This while loop checks if the new random coordinate of the tile is the same as the
         old coordinate, and if it is, it will continue to come up with random coordinates until
         it comes up with a completely different new random coordinate than the old one.*/
         while((xPos == tiles.get(m).getX()) && (yPos == tiles.get(m).getY())){
            xPos = (int)(Math.random() * ((width - tiles.get(m).getWidth()) + 1));
            yPos = (int)(Math.random() * ((height - tiles.get(m).getHeight()) + 1));
         }
         // The two lines below set the new random coordinates for the tiles.
         tiles.get(m).setX(xPos);
         tiles.get(m).setY(yPos);
      }
      
   }
   
   
}