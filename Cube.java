import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//View ReadMe for info on commands

/*  Steps until project is finished.
 * 2) second step will be to figure out how to identify paterns (possibly states achieved from different moves) and to shorten those paterns 
 * (i.e. D,D,D is the same as D')
 * probably use "indexOf()" with wildcards inside to find patterns of 3. Wild cards will not help in identifying the pattern.
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
    Boolean argu = true;
    if(args.length > 0){
      argu = false;
      for(int i =0; i < args.length; i++){
        command = command.concat(args[i]);
        
      }//End forLoop
    }//End if
    run(cube, command, argu);
    System.out.println("Program Terminated.\n");
  }// End Main
/* Method that displays the cube when called. takes a 3d array as a varriable.
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
      rowTemp[i] = temp[0][i][0];
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
      
      
      //temp[3][0][x] = temp[5][x][0];
      //temp[5][x][0] = temp[1][0][x];
      //temp[1][0][x] = temp[4][x][0];
      //temp[4][x][0] = rowTemp[x];
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
      temp[3][0][x] = temp[4][x][2];
      temp[4][x][2] = temp[1][2][x];
      temp[1][2][x] = temp[5][x][0];
      temp[5][x][0] = rowTemp[x];
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
  public static void reverseSolve(char temp[][][], String commands, boolean run){
    String tempSolve ="";
    String tempChar = "";
    String primecheck = "";
    try {
      tempSolve = commands.substring(commands.length());
      for(int i = commands.length(); i>0;i--){
            tempChar = commands.substring(i-1, i);
          if(tempChar.contains("'") == true){
            tempChar = commands.substring(i-2, i);
          }//End if
        tempChar = returnOposite(tempChar);
        tempSolve = tempSolve.concat(tempChar);
      }//End forLoop
    } catch(Exception e){
      System.out.println(e);
      System.out.println("We are getting stuck in the reverseSolve");
    }
    if(run == true){
      tempSolve = tempSolve.concat("x");
      run(temp, tempSolve, true);
    }else{
      System.out.println(tempSolve);
    }
    
  }//End reverseSolve

//run the main code
  public static void run(char cube[][][], String commands, boolean display){
    String commandList = "";
    String input = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while( !input.matches("x") & !input.matches("X")){
      if(display == true){
        display(cube);
      }
      try { 
        if(commands.length() < 1){
        System.out.print("Enter Data: ");
        input = reader.readLine();
        }else{
          try{
            if(commands.substring(0, 2).contains("'") == true){
              input = commands.substring(0,2);
              commands = commands.substring(2);
            }else{
              input = commands.substring(0, 1);
              commands = commands.substring(1);
            }//end Else
            if( commands == ""){
              commands = "x";
            }//End if
          }//End try block
          catch(IndexOutOfBoundsException e) {
            input = commands.substring(0);
            commands = "";
            display = true;
          }//End Catch
          System.out.println("____________________\n");
        }//End if else
      }//end try
      catch(IOException e) {
        System.out.println(e);
        System.out.println("You faggot! How hard is it to hit the right button?");
      }//End Catch
      catch(StringIndexOutOfBoundsException e){
        System.out.println(e);
        System.out.println("This Error is being called from the \"Try\" block in the run function.");
        input = "x";
        commands = "x";
      }//End catch
      
      if(input == " "){
        input = "x";
      }//End if
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
        case "S":
        case "s":
          System.out.println(commandList);
          reverseSolve(cube, commandList, true);
          break;
        default :
          System.out.println(input);  
          System.out.println("An inccorect command was used. Please use: \t(u, d, l, r, f, or b)\n\t\t(u', d', l', r', f', or b') \n Type \"S\" to solve and \"x\" to exit.");
          break;
      }//End Switch
     
      reverseSolve(cube, commandList, false);
    }//end While loop   
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
        i = "x";
        break;
    }//End Switch
    return i;
  }//End returnOpposite Function
}//End Class