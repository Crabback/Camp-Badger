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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class uses an instance of the CampManager to execute certain commands as read from a text
 * file.
 * 
 * @author zmao27 && hli
 *
 */
public class CampEnrollmentApp {

  /**
   * main method to execute commands
   * 
   * @param args
   * @throws IOException if the file is null
   */
  public static void main(String[] args) throws IOException {
    CampManager cm = new CampManager();

    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
    // local file address testing only
    // List<String> fileLines = Files.readAllLines(Paths.get("/Users/turtleback/CS 300/P09 Camp
    // Badger/src/sim.txt"));

    // use enhanced for loop to iterate through lines
    for (String line : fileLines) {

      if (line.charAt(0) == 'E') { // detects E, do enroll
        try {
          Camper camper = new Camper(line.split(" ")[2], line.split(" ")[1],
              Integer.parseInt(line.split(" ")[3]));
          cm.enrollCamper(camper);
        } catch (IllegalArgumentException e) {
          System.out.println("This person is either too old or too young to be in Camp Badger.");
        }

      } else if (line.charAt(0) == 'R') { // detects R, do unenroll
        try {
          Camper camper = new Camper(line.split(" ")[2], line.split(" ")[1], 10);
          cm.unenrollCamper(camper);
        } catch (NoSuchElementException e) {
          System.out.println("That camper is not enrolled");
        }

      } else if (line.charAt(0) == 'T') { // detects T, do traverse
        System.out.println("--- " + line.split(" ")[1] + " Traversal ---");
        // create a new iterator and iterate through the nodes
        Iterator<Camper> it = cm.traverse(line.split(" ")[1]);
        while (it.hasNext()) {
          Camper current = it.next();
          System.out.println(current.getLastName() + ", " + current.getFirstName() + " Age: "
              + current.getAge() + " Cabin: " + current.getCabin());
        }
        System.out.println("-------------------------");

      } else if (line.charAt(0) == 'S') { // detects S, print the number of campers
        cm.printStatistics();

      } else { // generally will not happen
        System.out.println("invalid statement");
      }
    }
  }

}
