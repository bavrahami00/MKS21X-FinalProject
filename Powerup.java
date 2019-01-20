public class Powerup implements HasLocation {
  private int xPos;
  private int yPos;
  private int type;
  public Powerup(int x, int y, int t) {
    xPos = x;
    yPos = y;
    type = t;
  }
  //Extra life, temporary immunity, recreates barriers, rapid fire, increase score
  public int getXPos() {
    return xPos;
  }
  public int getYPos() {
    return yPos;
  }
  public int move() {
    xPos--;
    return xPos;
  }
  public int getType() {
    return type;
  }
}
