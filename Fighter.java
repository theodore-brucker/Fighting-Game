//=======================================================================
// Author:		Theodore Brucker
// Date:  		12/15/2021
// Language:	Java
// Purpose:		Class that creates fighter objects...
// File:   		Fighter.java
//=======================================================================

import java.util.*;

//Base object that is modified through user input and used to battle
class Fighter{
  String name;

  int hp, atk, rng, arm, attribute_points;

  //Creates a Fighter object
  //Takes a name as input and prompts user for desired distribution of attribute points
  Fighter(String name){
    this.name = name;

    hp = 10;
    atk = 3;
    rng = 0;
    arm = 1;
    attribute_points = 10;

    while(attribute_points > 0){

      String attributeTarget;
      int attributeIncrease;

      Scanner fighterScan = new Scanner(System.in);
      System.out.format("\nyou have %d attribute points to spend.\tenter the attribute you'd like to increase: \"hp\" = hit points, \"atk\" = attack, \"rng\" = range, \"arm\" = armor\n", attribute_points);
      attributeTarget = fighterScan.nextLine();
      if(attributeTarget.equals("hp") || attributeTarget.equals("atk") || attributeTarget.equals("rng") || attributeTarget.equals("arm")){
        System.out.println("\nhow many points would you like to put into this attribute? ");
        try{
        attributeIncrease = fighterScan.nextInt();
        spendAttribute(attributeIncrease, attributeTarget);
        }catch(InputMismatchException e){
          System.out.println("\nnot a valid input");
        }

      }else{
        System.out.println("\nnot a valid input");
        continue;
      }
    }
  }

  //Returns the ASCII value of the first character of the fighters name
  //Used to sort alphabetically
  public int getAscii(){
    String str = name.toLowerCase();
    char first = str.charAt(0);
    return (int)first;
  }

  //Takes an int and string as the desired amount of points spent and where to put them
  //Returns the remaining amount of attributePoints
  public void spendAttribute(int pointsSpent, String attribute){

    if(pointsSpent <= attribute_points){
      switch(attribute){
        case "hp":
          hp += pointsSpent;
          System.out.format("added %d points to %s --- %s now equals %d\n\n", pointsSpent, attribute, attribute, hp);
          break;

        case "atk":
          atk += pointsSpent;
          System.out.format("added %d points to %s --- %s now equals %d\n", pointsSpent, attribute, attribute, atk);
          break;

        case "rng":
          rng += pointsSpent;
          System.out.format("added %d points to %s --- %s now equals %d\n", pointsSpent, attribute, attribute, rng);
          break;

        case "arm":
          arm += pointsSpent;
          System.out.format("added %d points to %s --- %s now equals %d\n", pointsSpent, attribute, attribute, arm);
          break;
      }
      attribute_points -= pointsSpent;
    }else{
      System.out.format("cannot spend %d points --- you have %d left\n", pointsSpent, attribute_points);
    }
  }

  //Takes an int
  //Sets HP stat
  public void reduceHP(int dmg){
    hp -= dmg;
  }

  //Takes an int
  //Sets attack stat
  public void setAtk(int atk){
    this.atk = atk;
  }

  //Takes an int
  //Sets range stat
  public void setRange(int rng){
    this.rng = rng;
  }

  //Takes an int
  //Sets defense stat
  public void setDfc(int arm){
    this.arm = arm;
  }

  //Returns name
  public String getName(){
    return name;
  }

  //Returns hp stat
  public int getHP(){
    return hp;
  }

  //Returns attack stat
  public int getAtk(){
    return atk;
  }

  //Returns range stat
  public int getRng(){
    return rng;
  }

  //Returns armor stat
  public int getArm(){
    return arm;
  }
}
