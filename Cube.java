import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//View ReadMe for info on commands


/*  Steps until project is finished.
 * 1) Next step will be to add in all the prime moves and to fix reverseSolve so that it know how to recognize prime moves
 * I am going to cheat on the primes and simply have them use the normal moves three times but only log one move for the commandList
 * 
 * 2) second step will be to figure out how to identify paterns (possibly states achieved from different moves) and to shorten those paterns 
 * (i.e. D,D,D is the same as D')
 * probably use "indexOf()" with wildcards inside to find patterns of 3. Wild cards will not help in identifying the pattern.
 * 
 * 3) lastly, I will add in an args check to feed moves to the while loop. 
 */
public class Cube {  
  public static void main(final String[] args){
    //This cube is a three dimensional array. it is of data type char but that might change later.
    //Maybe I need to declare the cube outside of main so that it is global? 
    /*This is the key for which number to which color
      0 = White
      1 = Green
      2 = Yellow
      3 = Blue
      4 = Red 
      5 = Orange
    */
    char cube[][][] = new char[6][3][3];
    for (int i = 0; i < 6; i++){
      //width
      for (int x = 0; x < 3; x++){
        //height
        for (int y = 0; y < 3; y++){
          switch (i) {
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
    String command = "";
    run(cube, command);
    System.out.println("Program Terminated.\n");
  }// End Main


/* Mehtod that displays the cube when called. takes a 3d array as a varriable.
 * I could have theoretically done this with less lines of code by recursively calling a simple display function that displays one face at a time. 
 * It makes no difference for this project but could allow for more control of future renditions. 
*/ 
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

//U move
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
    faceRotation(temp, 3);
    return temp;
  }//End 
//D Move
  public static char[][][] dMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[0][2][i];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[0][2][x] = temp[5][2][x];
      temp[5][2][x] = temp[2][2][x];
      temp[2][2][x] = temp[4][2][x];
      temp[4][2][x] = rowTemp[x];
    }//End forLoop
    faceRotation(temp, 1);
    return temp;
  }//End
//R Move
  public static char[][][] rMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[0][i][2];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[0][x][2] = temp[1][x][2];
      temp[1][x][2] = temp[2][x][2];
      temp[2][x][2] = temp[3][x][2];
      temp[3][x][2] = rowTemp[x];
    }//End forLoop
    faceRotation(temp, 4);
    return temp;
  }//End
//L Move
  public static char[][][] lMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[0][i][2];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[0][x][0] = temp[3][x][0];
      temp[3][x][0] = temp[2][x][0];
      temp[2][x][0] = temp[1][x][0];
      temp[1][x][0] = rowTemp[x];
    }//End forLoop
    faceRotation(temp, 5);
    return temp;
  }//End
//F Move
  public static char[][][] fMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[3][0][i];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[3][0][x] = temp[5][x][0];
      temp[5][x][0] = temp[1][0][x];
      temp[1][0][x] = temp[4][x][0];
      temp[4][x][0] = rowTemp[x];
    }//End forLoop
    faceRotation(temp, 0);
    return temp;
  }//End
//B Move
  public static char[][][] bMove(char temp[][][]){
    char rowTemp[] = new char[3];
    for(int i=0; i<3; i++){
      rowTemp[i] = temp[3][0][i];
    }//End forLoop
    for(int x=0; x<3; x++){
      temp[3][2][x] = temp[4][x][2];
      temp[4][x][2] = temp[1][2][x];
      temp[1][2][x] = temp[5][x][2];
      temp[5][x][2] = rowTemp[x];
    }//End forLoop
    faceRotation(temp, 2);
    return temp;
  }//End

//Rotate faces function
  public static char[][][] faceRotation(char tempCube[][][], int faceToRotate){
    char temp = tempCube[faceToRotate][0][0];
    tempCube[faceToRotate][0][0] = tempCube[faceToRotate][2][0];
    tempCube[faceToRotate][2][0] = tempCube[faceToRotate][2][2];
    tempCube[faceToRotate][2][2] = tempCube[faceToRotate][0][2];
    tempCube[faceToRotate][2][2] = temp;
    temp = tempCube[faceToRotate][0][1];
    tempCube[faceToRotate][0][1] = tempCube[faceToRotate][1][0];
    tempCube[faceToRotate][1][0] = tempCube[faceToRotate][2][1];
    tempCube[faceToRotate][2][1] = tempCube[faceToRotate][1][2];
    tempCube[faceToRotate][1][2] = temp;
    return tempCube;
  }
//Reversing all moves
  public static void reverseSolve(char temp[][][], String commands){
    String tempSolve ="";
    String tempChar = "";
    tempSolve = commands.substring(commands.length());
    for(int i = commands.length(); i>0 ;i--){
      if(commands.substring(i-2, i).contains("?'")){
        tempChar = commands.substring(i-2, i);
      }else{
        tempChar = commands.substring(i-1, i);
      }
      tempChar = returnOposite(tempChar);
      tempSolve = tempSolve.concat(tempChar);
    }
    tempSolve = tempSolve.concat("x");
    run(temp, tempSolve);
  }
//run the main code
  public static void run(char cube[][][], String commands){
    String commandList = "";
    String input = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    while( !input.matches("x") & !input.matches("X")){
      display(cube);

      try {
        
        if(commands.length() < 1){
        System.out.print("Enter Data: ");
        input = reader.readLine();
        //display(cube);
        }else{
          input = commands.substring(0, 1);
          commands = commands.substring(1);
          System.out.println("____________________");
        }

        //Check for which command has been called 
        switch(input){
          case "u'":
          case "U'":
            commandList = commandList.concat(input);
            cube = uMove(cube);
            cube = uMove(cube);
            cube = uMove(cube);
            break;
          case "d'":
          case "D'":
            commandList = commandList.concat(input);
            cube = dMove(cube);
            cube = dMove(cube);
            cube = dMove(cube);
            break;
          case "r'":
          case "R'":
            commandList = commandList.concat(input);
            cube = rMove(cube);
            cube = rMove(cube);
            cube = rMove(cube);
            break;
          case "l'":
          case "L'":
            commandList = commandList.concat(input);
            cube = lMove(cube);
            cube = lMove(cube);
            cube = lMove(cube);
            break;
          case "f'":
          case "F'":
            commandList = commandList.concat(input);
            cube = fMove(cube);
            cube = fMove(cube);
            cube = fMove(cube);
            break;
          case "b'":
          case "B'":
            commandList = commandList.concat(input);
            cube = bMove(cube);
            cube = bMove(cube);
            cube = bMove(cube);
            break;

          case "u":
          case "U":
            commandList = commandList.concat(input);
            cube = uMove(cube);
            break;
          case "d":
          case "D":
            commandList = commandList.concat(input);
            cube = dMove(cube);
            break;
          case "r":
          case "R":
            commandList = commandList.concat(input);
            cube = rMove(cube);
            break;
          case "l":
          case "L":
            commandList = commandList.concat(input);
            cube = lMove(cube);
            break;
          case "f":
          case "F":
            commandList = commandList.concat(input);
            cube = fMove(cube);
            break;
          case "b":
          case "B":
            commandList = commandList.concat(input);
            cube = bMove(cube);
            break;
          case "x":
          case "X":
            break;
          case "Solve":
          case "solve":
            System.out.println(commandList);
            reverseSolve(cube, commandList);
            break;
          default :
            System.out.println("That is not a real command! Try again");
            break;
        }
    }//end try
    catch(IOException e) {
      System.out.println(e);
      System.out.println("You faggot! How hard is it to hit the right button?");
    }
    catch(StringIndexOutOfBoundsException e){
      System.out.println(e);
    }//end catch
   }//end While loop
   //display(cube);
   System.out.println("While loop ended");
   
  }//End Run Function

//Retrun opposite of a given command
  public static String returnOposite(String i){
    switch(i){
      case "u'":
      case "U'":
        i = "u";
        break;
      case "d'":
      case "D'":
        i = "d";
        break;
      case "r'":
      case "R'":
        i = "r";
        break;
      case "l'":
      case "L'":
        i = "l";
        break;
      case "f'":
      case "F'":
        i = "f";
        break;
      case "b'":
      case "B'":
        i = "b";
        break;
      case "u":
      case "U":
        i = "u'";
        break;
      case "d":
      case "D":
        i = "d'";
        break;
      case "r":
      case "R":
        i = "r'";
        break;
      case "l":
      case "L":
        i = "l'";
        break;
      case "f":
      case "F":
        i = "f'";
        break;
      case "b":
      case "B":
        i = "b'";
        break;
      case "x":
      case "X":
        break;
      default :
        i = " ";
        break;
    }//End Switch
    return i;
  }//End returnOpposite Function

}//End Class
