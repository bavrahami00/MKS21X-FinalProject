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
  public int implement(User u,int score,barrier shields,int shootTime) {
    if (type.equals("Life")) {
      u.addLife();
    }
    else if (type.equals("Score")) {
      score += 150;
    }
    else if (type.equals("Barrier")) {
      shields = new Barrier();
    }
    else if (type.equals("Time")) {
      shootTime = 100;
      return 10000;
    }
    else {
      return 15000;
    }
    return 0;
  }
}
