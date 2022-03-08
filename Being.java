public class Being {
  public int x;
  public int y;
  public boolean alive = false;
  public boolean next = false;

  public int neighbors = 0;

  public Being(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void check() {
    if (neighbors == 3 || (neighbors == 2 && alive)) {
      alive = true;
    } else {
      alive = false;
    }
  }

  public void implement() { alive = next; }
}