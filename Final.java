//=======================================================================
// Author:		Theodore Brucker
// Date:  		12/15/2021
// Language:	Java
// Purpose:		Top level object that will take user input for variety of commands
// File:   		Final.java
//=======================================================================
import java.util.*;
import java.lang.NumberFormatException;
import java.io.*;

//Highest tier class that uses both Roster.java and Fighter.java to create a battle
class Final{

  //Takes 2 rosters as input and has them fight, declaring a winner for each round, or a draw
  //Posts each battle winner to battle_record.txt
  public static String fight(Roster rOne, Roster rTwo){

    int numRounds;
    int numTurns = 0;

    if(rOne.getSize() >= rTwo.getSize()){
      numRounds = rOne.getSize();
    }else{
      numRounds = rTwo.getSize();
    }

    for(int i = 0; i < numRounds; i++){
      System.out.format("Round %d is between %s and %s\n", i, rOne.getFighter(i).getName(), rTwo.getFighter(i).getName());
      if(rOne.getFighter(i).getRng() >= rTwo.getFighter(i).getRng()){
        System.out.format(" - - - %s has higher range, so they attack first\n", rOne.getFighter(i).getName());

        while(rOne.getFighter(i).getHP() > 0 && rTwo.getFighter(i).getHP() > 0){
          if(numTurns >= 20){
            System.out.println(" - - - neither side wins, draw");
            break;
          }

          System.out.format(" - - - %s attacks %s for %d damage, reduced by %d due to armor\n", rOne.getFighter(i).getName(), rTwo.getFighter(i).getName(), rOne.getFighter(i).getAtk() - rTwo.getFighter(i).getArm(), rTwo.getFighter(i).getArm());
          rTwo.getFighter(i).reduceHP(rOne.getFighter(i).getAtk() - rTwo.getFighter(i).getArm());

          System.out.format(" - - - %s attacks %s for %d damage, reduced by %d due to armor\n", rTwo.getFighter(i).getName(), rOne.getFighter(i).getName(), rTwo.getFighter(i).getAtk() - rOne.getFighter(i).getArm(), rOne.getFighter(i).getArm());
          rOne.getFighter(i).reduceHP(rTwo.getFighter(i).getAtk() - rOne.getFighter(i).getArm());
          numTurns++;
        }
        if(rOne.getFighter(i).getHP() <= 0){
          System.out.format(" - - - Fighter %s from side %s has been victorious\n", rTwo.getFighter(i).getName(), rTwo.getRName());
          return rTwo.getFighter(i).getName();
        }
        if(rTwo.getFighter(i).getHP() <= 0){
          System.out.format(" - - - Fighter %s from side %s has been victorious\n", rOne.getFighter(i).getName(), rOne.getRName());
          return rOne.getFighter(i).getName();
        }
      }

      if(rOne.getFighter(i).getRng() <= rTwo.getFighter(i).getRng()){
        System.out.format(" - - - %s has higher range, so they attack first\n", rTwo.getFighter(i).getName());

        while(rOne.getFighter(i).getHP() > 0 && rTwo.getFighter(i).getHP() > 0){
          System.out.format(" - - - %s attacks %s for %d damage, reduced by %d due to armor\n", rTwo.getFighter(i).getName(), rOne.getFighter(i).getName(), rTwo.getFighter(i).getAtk() - rOne.getFighter(i).getArm(), rOne.getFighter(i).getArm());
          rOne.getFighter(i).reduceHP(rTwo.getFighter(i).getAtk() - rOne.getFighter(i).getArm());

          System.out.format(" - - - %s attacks %s for %d damage, reduced by %d due to armor\n", rOne.getFighter(i).getName(), rTwo.getFighter(i).getName(), rOne.getFighter(i).getAtk() - rTwo.getFighter(i).getArm(), rTwo.getFighter(i).getArm());
          rTwo.getFighter(i).reduceHP(rOne.getFighter(i).getAtk() - rTwo.getFighter(i).getArm());
        }

        if(rOne.getFighter(i).getHP() <= 0){
          System.out.format(" - - - Fighter %s from side %s has been victorious\n", rTwo.getFighter(i).getName(), rTwo.getRName());
          return rTwo.getFighter(i).getName();
        }

        if(rTwo.getFighter(i).getHP() <= 0){
          System.out.format(" - - - Fighter %s from side %s has been victorious\n", rOne.getFighter(i).getName(), rOne.getRName());
          return rOne.getFighter(i).getName();
        }
      }
    }
    return "";
  }

  //Main function where user decides on which action to take, create roster, print, or battle.
  //Exit with 0
  //Catches incorrect inputs in all cases
  public static void main(String[] args){

      ArrayList<Roster> rosters = new ArrayList<Roster>(2);

      File battle_record = new File("battle_record.txt");

      int rosterSize;
      int escape = 1;
      int numRosters = 0;
      String rosterName = "";

    while(escape != 0){
      Scanner finalScan = new Scanner(System.in);
      System.out.println("\nselect an action \n1 - create new roster \n2 - battle your rosters \n3 - print rosters \n0 - end program");
      try{
        escape = finalScan.nextInt();
      }catch(InputMismatchException e){
        System.out.println("\nnot a valid input");
        continue;
      }

      switch(escape){
        case 0:
          System.out.println("\nending program");
          break;

        case 1:
          if(numRosters < 2){
            Scanner case1Scan = new Scanner(System.in);

            try{
            System.out.println("\nenter roster name (string): ");
            rosterName = case1Scan.nextLine();

            System.out.println("\nenter roster size (integer): ");
            rosterSize = case1Scan.nextInt();

            Roster rost = new Roster(rosterName, rosterSize);
            rosters.add(rost);
            numRosters++;
          }catch (InputMismatchException e){
            System.out.println("\n not a valid input");
          }
          }else{
            System.out.println("\nboth rosters already made . . . try fight");
          }
          break;
        case 2:
          if(numRosters == 2){
            System.out.println("\nstarting fight between rosters - - -");
            String winner = fight(rosters.get(0), rosters.get(1));
            try{
              FileWriter w = new FileWriter("battle_record.txt");
              w.write(winner + " was victorious");
              w.close();
            }catch(IOException e){
              System.out.println("error writing to file");
            }
          }else{
            System.out.println("please create 2 rosters before starting a fight - - -");
          }
          break;
        case 3:
          if(numRosters == 0){
            System.out.println("\ncreate a roster before using this command - - -");
          }else{
            for(int i = 0; i < numRosters; i++){
              rosters.get(i).printRoster();
            }
          }
          break;
      }
    }
  }
}
