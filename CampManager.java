//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Camp Badger
// Files: Camper.java, CampTreeNode.java, CamperBST.java, CampManager.java, CampEnrollmentApp.java
// Course: CS 300
//
// Author: Zhengjia Mao
// Email: zmao27@wisc.edu
// Lecturer's Name: Gary DAHL
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Hangpeng Li
// Partner Email: hli578@wisc.edu
// Partner Lecturer's Name: Gary DAHL
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _YES__ Write-up states that pair programming is allowed for this assignment.
// _YES__ We have both read and understand the course Pair Programming Policy.
// _YES__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: ULC Tutors
// Online Sources: GeeksForGeeks
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates an instance of a CamperBST Class and calls the operations inside it to manage
 * Camp Badger.
 * 
 * @author zmao27 && hli578
 *
 */
public class CampManager {

  /**
   * private fields in this class
   */
  private CamperBST campers;
  private final static String[] CABIN_NAMES =
      new String[] {"Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"};

  /**
   * Constructor for the CampManager by initializing the campers field
   */
  public CampManager() {
    campers = new CamperBST();
  }

  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree.
   * 
   * @param newCamper - the camper to be enrolled into the camp
   */
  public void enrollCamper(Camper newCamper) {
    int age = newCamper.getAge();
    if (age >= 8 && age <= 9) {
      newCamper.assignCabin(CABIN_NAMES[0]);
    } else if (age >= 10 && age <= 12) {
      newCamper.assignCabin(CABIN_NAMES[1]);
    } else {
      newCamper.assignCabin(CABIN_NAMES[2]);
    }
    campers.insert(newCamper);

  }

  /**
   * Prints statistics based on the current "state" of the camp. The statistics to be printed is the
   * total number of campers.
   */
  public void printStatistics() {
    System.out.println("Number of Campers: " + campers.size());
  }

  /**
   * Traverses the tree in the designated order by calling it through the CamperBST class.
   * 
   * @param order - the type of traversal for the tree to perform
   * @return the Iterator of Campers from CampBST.traverse()
   */
  public Iterator<Camper> traverse(String order) {
    return campers.traverse(order);
  }

  /**
   * "Unenrolls" a camper by removing them from the tree.
   * 
   * @param delCamper - the camper to be unenrolled the camp
   * @throws NoSuchElementException - if CamperBST.delete throws the exception
   */
  public void unenrollCamper(Camper delCamper) throws NoSuchElementException {
    campers.delete(delCamper);
  }
}
