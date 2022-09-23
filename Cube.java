import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Cube {  
  public static void main(final String[] args){
    //This cube is a three dimensional array. it is of data type char but that might change later.
    //Maybe I need to declare the cube outside of main so that it is global? 
    char cube[][][] = new char[6][3][3];
    //This is the key for which number to which color
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
 
    //Setting up the input from the user
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    char command = ' ';
    int input;
    try {
      while( command != 'x'){
       
        System.out.print("Enter Data: ");
        input = reader.read();
        command = (char)input;
        System.out.println("\nYou entered: " + command);
     }//end While loop
    }//end try
    catch(IOException e) {
      System.out.println(e);
      System.out.println("You faggot! How hard is it to hit the right button?");
    }
    
    display(cube);
  }

//Mehtod that displays the cube when called. takes a 3d array as a varriable.
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



}
