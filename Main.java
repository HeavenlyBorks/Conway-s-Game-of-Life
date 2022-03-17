// import com.diogonunes.jcolor.Ansi.*;
// import com.diogonunes.jcolor.Attribute.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // scanner
    Scanner input = new Scanner(System.in);

    // final AnsiFormat text = new AnsiFormat(TEXT_COLOR(196));
    // final AnsiFormat optionNumber = new AnsiFormat(TEXT_COLOR(208));
    // final AnsiFormat option = new AnsiFormat(TEXT_COLOR(220));
    
    // initializing the grid
    System.out.println("Welcome to Conway's Game of Life!\nPlease note: It's recommended your board be between 10-25 cells wide/tall,\notherwise it might not look very good :(");
    System.out.print("How wide do you want the board to be? ");
    int dimensionX = input.nextInt();
    System.out.print("How tall do you want the board to be? ");
    int dimensionY = input.nextInt();
    System.out.print("How long should the pause between iterations be (in seconds)? ");
    double sleep = input.nextDouble();
    Being[][] grid = new Being[dimensionY][dimensionX];
    for (int i = 0; i < dimensionY; i++) {
      for (int j = 0; j < dimensionX; j++) {
        grid[i][j] = new Being(j, i);
      }
    }

    grid[0][0].alive = true;
    grid[1][1].alive = true;
    grid[1][2].alive = true;
    grid[2][0].alive = true;
    grid[2][1].alive = true;

    // initial draw
    System.out.print("\033[2J");
    for (Being[] row : grid) {
      for (Being tile : row) {
        if (tile.alive) { System.out.print("\033[31mo\033[0m "); } else { System.out.print("\033[0m~ "); }
      }
      System.out.print("\n");
    }

    boolean loop = true;
    while (loop) {
      
      try {
        Thread.sleep((int) (sleep * 1000));
      } catch (InterruptedException e) {
        loop = false;
      }
      
      // neighbor reset
      for (Being[] row : grid) {
        for (Being tile : row) {
          tile.neighbors = 0;
        }
      }
      
      // neighbor checker
      for (int i = 0; i < dimensionY; i++) {
        //System.out.println("Checking y=" + i);
        for (int j = 0; j < dimensionX; j++) {
          //System.out.println("Checking x=" + j);
          //System.out.println(j);
          int x = grid[i][j].x;
          int y = grid[i][j].y;
  
          int iY = -1;
          int iX = -1;
          // row check
          while (iY < 2) {
            y = grid[i][j].y;
            y += iY;
            if (y < 0) {
              y += dimensionY;
            } else if (y > dimensionY - 1) {
              y -= dimensionY;
            }
            // column check
            while(iX < 2) {
              x = grid[i][j].x;
              x += iX;
              //System.out.println(x);
              if (x < 0) {
                x += dimensionX;
              } else if (x > dimensionX - 1) {
                x -= dimensionX;
              }
              if (!(x == grid[i][j].x && y == grid[i][j].y)) {
                //System.out.println("Checking " + x + ", " + y);
                if (grid[y][x].alive) {
                  grid[i][j].neighbors += 1;
                }
              }
              iX++;
            }
            iX = -1;
            iY++;
          }
        }
      }
  
      // draw the grid
      System.out.print("\033[2J");
      for (Being[] row : grid) {
        for (Being tile : row) {
          tile.check();
          if (tile.alive) { System.out.print("\033[31mo\033[0m "); } else { System.out.print("\033[0m~ "); }
        }
        System.out.print("\n");
      }
    }
  }
}
