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
  public int implement(User u, int score, Barrier shields, int shootTime) {
    if (type == 0) {
      u.addLife();
    }
    else if (type == 1) {
      score += 150;
    }
    else if (type == 2) {
      shields = new Barrier();
    }
    else if (type == 3) {
      shootTime = 100;
      return 10000;
    }
    else {
      return 15000;
    }
    return 0;
  }
}
