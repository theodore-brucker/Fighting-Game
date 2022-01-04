//=======================================================================
// Author:		Theodore Brucker
// Date:  		11/18/2021
// Language:	Java
// Purpose:		Holds ArrayList of fighters and allows for them to be sorted
// File:   		Roster.java
//=======================================================================

import java.util.*;

//Handles holding and sorting "rosters" of fighters
class Roster{
  ArrayList<Fighter> fighters;
  String name;
  int numFighters;

  //Constructor for Roster that takes the name of the roster, and the number of fighters that it will hold
  //Has user create fighters until roster is full, then sorts the fighters by alphabetical order using quicksort
  Roster(String name, int numFighters){
    this.name = name;
    this.numFighters = numFighters;

    fighters = new ArrayList<Fighter>(numFighters);
    String tempName;
    Fighter temp;

    Scanner rosterScan = new Scanner(System.in);
    for(int i = 0; i < numFighters; i++){
      System.out.format("\nplease enter fighter %d's name: ", i+1);
      tempName = rosterScan.nextLine();
      temp = new Fighter(tempName);
      fighters.add(temp);
    }
    quickSort(0, fighters.size()-1);
  }

  //Prints the name of the roster followed by each of its fighters
  //No input or return
  public void printRoster(){
    System.out.format("\nroster %s\n", name);
    for(int i = 0; i < fighters.size(); i++){
      System.out.format(" -%s\n", fighters.get(i).getName());
    }
  }

  //Quicksort that is used to sort the fighters in the roster alphabetically by the first letter in their name
  //Takes the low and high index of the piece of the array being sorted.
  //Base case is when low no longer is less than high
  public void quickSort(int low, int high){
    if(low < high){
      int p = partition(low, high);
      quickSort(low, p-1);
      quickSort(p+1, high);
    }
  }

  //Swaps the fighters in the roster at the 2 given indicies
  //Takes index of the fighters that will be swapped
  public void swapFighters(int index1, int index2){
    Fighter temp = fighters.get(index1);
    fighters.set(index1, fighters.get(index2));
    fighters.set(index2, temp);
  }

  //Partitions the array and p holds the midpoint after the partition
  //Takes the low and high index of the piece of the array being looked at
  //Swaps when necessary and returns the next midpoint
  public int partition(int low, int high){
    int p = low;
    int i;

    for(i = low + 1; i <= high; i++){
      if(fighters.get(i).getAscii() < fighters.get(low).getAscii()){
        swapFighters(++p, i);
      }
    }
    swapFighters(low, p);
    return p;
  }

  //If an index is given, returns the fighter at that position
  //Returns the fighter at a given integer index in the roster
  public Fighter getFighter(int index){
    return fighters.get(index);
  }

  //If no index is given, return the first fighter in the roster
  //Returns the first fighter in the roster
  public Fighter getFighter(){
    return fighters.get(0);
  }

  //Returns the current size of the roster
  public int getSize(){
    return numFighters;
  }

  //Returns the name of the roster
  public String getRName(){
    return name;
  }
}
