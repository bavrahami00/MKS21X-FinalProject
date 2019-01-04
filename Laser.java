public class Laser {
  private int damage;
  private int xPos;
  private int yPos;
  private Being source;
  public Laser(int d, int x, int y, Being s) {
    damage = d;
    xPos = x;
    yPos = y;
    source = s;
  }
  public void move(int dir) {
    if (dir == 0) {
      yPos--;
    }
    if (dir == 1) {
      xPos++;
    }
    if (dir == 2) {
      yPos++;
    }
    if (dir == 3) {
      xPos--;
    }
  }
}