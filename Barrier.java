public class Barrier{

  //each section of the array corresponds to a coordinate point on the terminal
  boolean[][] barrier = new boolean[100][40];

  public Barrier(){//sets up an array for where the true values represent existing barriers
    for(int y = 30; y < 34; y++){
      for(int i = 0; i < 100;i++){
        if((i >= 15 && i <= 25) || (i >= 35 && i <= 45) || (i >= 55 && i <= 65) || (i >= 75 & i <= 85)){
          barrier[i][y] = true;
        }
        else barrier[i][y] = false;
      }
    }
  }

  //destroys the barrier at the selected x and y coordinates
  public void destroy(int x, int y){
    barrier[x][y] = false;
  }

  public boolean barrierExists(int x, int y){
    return barrier[x][y];
  }

}
