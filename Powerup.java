public class Powerup implements HasLocation {
  private int xPos;
  private int yPos;
  private String type;
  public Powerup(int x, int y, String t) {
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
  public void implement(User u) {
    if (type.equals("Life")) {
      u.addLife();
    }
  }
}
