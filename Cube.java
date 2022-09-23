import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.*;
public class Cube {  
  public static void main(final String[] args){
  
    //System.out.println("This is a Cube with Width and height ");
    //This is mySystem.out.println("Hello World!");
    char cube[][][] = new char[6][3][3];
    /*
      0 = White
      1 = Green
      2 = Yellow
      3 = Blue
      4 = Red 
      5 = Orange
    */
    for (int i = 0; i < 6; i++){
      //width
      for (int x = 0; x < 3; x++){
        //height
        for (int y = 0; y < 3; y++){
          int face = i;
          switch (face) {
            case 0:
              cube[i][x][y] = 'W';
              break;
            case 1:
              cube[i][x][y] = 'G';
              break;
            case 2:
              cube[i][x][y] = 'Y';
              break;
            case 3:
              cube[i][x][y] = 'B';
              break;
            case 4:
              cube[i][x][y] = 'R';
              break;
            case 5:
              cube[i][x][y] = 'O';
              break;
          }
        }
      }
    }

    String input;
    //Scanner for user input
    Scanner sc = new Scanner(System.in);
    //BufferedReader br = new BufferedReader();
    
    char command = ' ';
    try {
      while( command != "x"){
       // System.out.print("Enter a move: ");
       System.out.print();
       input = System.console().readLine();
       System.out.println("You entered: " + input);
       // command = sc.nextLine().charAt(0);
       // System.out.println("\nYou put: " + command);
     }//end While loop
    }//end try
    catch(Exception e) {
      System.out.println(e);
      System.out.println("You faggot! How hard is it to hit the right button?");
    }
    display(cube);
  }

  //Rotating the sides of the cube forwards or backwards.
 public static void display(char temp[][][]){
  for (int i = 0; i < 6; i++){
    //height
    for (int x = 0; x < 3; x++){
      //width
      for (int y = 0; y < 3; y++){
        System.out.print(temp[i][x][y] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
 }

 //public static void 

}
