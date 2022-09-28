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
    

    try {
      String input;
      
      while( command != 'x' & command != 'X'){
        
        display(cube);
        System.out.print("Enter Data: ");
        input = reader.readLine();
        //***another While loop here for if the string is full of more commands. right now I will only take the first command.


        command = input.charAt(0);
        switch(command){
          case 'u':
          case 'U':
           cube = uMove(cube);
           break;
        }
        System.out.println("\nYou entered: " + command);
     }//end While loop
     System.out.print("Program Terminated.\n");
    }//end try
    catch(IOException e) {
      System.out.println(e);
      System.out.println("You faggot! How hard is it to hit the right button?");
    }
    catch(StringIndexOutOfBoundsException e){
      System.out.println("You Retard! put in an actual command.");
      //***Put in some sort of loop back so the code continues on even if this error happens.
    }
    
    
  }

//Mehtod that displays the cube when called. takes a 3d array as a varriable.
  public static void display(char temp[][][]){
    //Faces
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
// I want to change the way display works. can I call a function from inside the same function? 
//I could have display display faces and simply call it multiple times. 
//***Add in a way to track which moves are happening so that we can reverse it later


//**(Priority #1)** //This guy just needs to be copied for each of the next five moves. 
  public static char[][][] uMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[0][0][i];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[0][0][x] = temp[4][0][x];
      temp[4][0][x] = temp[2][0][x];
      temp[2][0][x] = temp[5][0][x];
      temp[5][0][x] = rowTemp[x];
    }//end forLoop
    return temp;
  }//End 

  public static char[][][] dMove(char temp[][][]){
    return temp;
  }//End

  public static char[][][] rMove(char temp[][][]){
    return temp;
  }//End

  public static char[][][] lMove(char temp[][][]){
    return temp;
  }//End

  public static char[][][] fMove(char temp[][][]){
    return temp;
  }//End

  public static char[][][] bMove(char temp[][][]){
    return temp;
  }//End
}
