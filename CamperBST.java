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
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This Class is an implementation of a binary search tree that uses campers
 * 
 * @author zmao27 && hli578
 *
 */
public class CamperBST {

  /**
   * Private fields only in this class
   */
  public CampTreeNode root;
  private int size;
  private LinkedList<Camper> traversedLList;

  /**
   * Constructor for an empty CampTreeNode
   */
  public CamperBST() {
    root = null;
    size = 0;
  }

  /**
   * Accessor of size
   * 
   * @return the current size of the CamperBST
   */
  public int size() {
    return size;
  }

  /**
   * Check whether the tree is empty
   * 
   * @return true if the tree is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Insert a new camper into the tree by order
   * 
   * @param newCamper
   */
  public void insert(Camper newCamper) {
    root = insertHelp(root, newCamper);
    size++;
    System.out.println("Enrollment of " + newCamper.getFirstName() + " " + newCamper.getLastName()
        + " Successful!");
  }

  /**
   * Recursive helper method to insert.
   * 
   * @param current, The "root" of the subtree we are inserting into, ie the node we are currently
   *        at.
   * @param newCamper, the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {

    CampTreeNode passInNode = new CampTreeNode();
    passInNode.setData(newCamper);

    // base case of the recursion, empty "root" of the subtree
    if (current == null) {
      current = passInNode;
      passInNode.setLeftNode(null);
      passInNode.setRightNode(null);
      return current;
    }

    // either cases, resurse down the tree
    if (newCamper.compareTo(current.getData()) < 0) {
      //the key is smaller than the "root" of the subtree
      current.setLeftNode(insertHelp(current.getLeftNode(), newCamper));
    }
    if (newCamper.compareTo(current.getData()) > 0) {
      //the key is bigger than the "root" of the subtree
      current.setRightNode(insertHelp(current.getRightNode(), newCamper));
    }
    return current;
  }


  /**
   * Prints the contents of this tree in alphabetical order, based on the string "lastName,
   * firstName"
   */
  public void print() {
    printHelp(root);
  }

  /**
   * Recursive helper method to print
   * 
   * @param current
   */
  private void printHelp(CampTreeNode current) {
    if (current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }


  /**
   * Deletes a Camper into the binary search tree if it exists.
   * 
   * @param key, the camper to be deleted from the tree
   * @throws NoSuchElementException if it is thrown by deleteHelp
   */
  public void delete(Camper key) throws NoSuchElementException {
    root = deleteHelp(root, key);
    size--;
    System.out.println(
        "Unenrollment of " + key.getFirstName() + " " + key.getLastName() + " Successful!");
  }

  /**
   * Recursive helper method to delete.
   * 
   * @param current, The "root" of the subtree we are deleting from, * ie the node we are currently
   *        at.
   * @param key, the camper to be deleted from the tree
   * @return the root of the modified subtree we deleted from
   * @throws NoSuchElementException if the camper is not in the tree
   */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {
    // base Case, empty tree
    if (current == null) {
      throw new NoSuchElementException("That camper is not enrolled");
    }
    // compare key and nodes, and recurse down the tree
    if (key.compareTo(current.getData()) < 0) {
      // the key is smaller than the "root" of the subtree
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
    } else if (key.compareTo(current.getData()) > 0) {
      // the key is bigger than the "root" of the subtree
      current.setRightNode(deleteHelp(current.getRightNode(), key));
    } else {// the node is the one to be deleted
      if (current.getLeftNode() == null) {
        return current.getRightNode();
      } else if (current.getRightNode() == null) {
        return current.getLeftNode();
      } else {
        // if the node has two children, find the successor by using findSuccessor()
        // (got help from GeeksForGeeks)
        current.setData(findSuccessor(current.getRightNode()));
        current.setRightNode(deleteHelp(current.getRightNode(), current.getData()));
      }
    }
    return current;
  }

  /**
   * Helper to find the successor (got help from GeeksForGeeks)
   * 
   * @param subRoot
   * @return successor's data to be placed in the original root
   */
  public Camper findSuccessor(CampTreeNode subRoot) {
    Camper data = subRoot.getData();
    while (subRoot.getLeftNode() != null) {
      data = subRoot.getLeftNode().getData();
      subRoot = subRoot.getLeftNode();
    }
    return data;
  }


  /**
   * returns an iterator of camper in the correct order as designated
   * 
   * @param order
   * @return an iterator of camper in the correct order as designated
   */
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if (traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
      // clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Recursive helper method to traverse. Will take the current CampTreeNode’s data and add it to
   * traversedLList based on the given order. Then continue to recurse on the correct subtree.
   * 
   * @param current, the root of the current subtree we are traversing
   * @param order, the type of traversal to perform
   */
  private void traverseHelp(CampTreeNode current, String order) {
    // detects the expected order
    if (order.equals("PREORDER")) {
      if (current == null) {
        return;
      }
      // visit, left, right
      traversedLList.add(current.getData());
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);
    }
    // detects the expected order
    else if (order.equals("INORDER")) {
      if (current == null) {
        return;
      }
      // left, visit, right
      traverseHelp(current.getLeftNode(), order);
      traversedLList.add(current.getData());
      traverseHelp(current.getRightNode(), order);
    }
    // detects the expected order
    else if (order.equals("POSTORDER")) {
      if (current == null) {
        return;
      }
      // left, right, visit
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);
      traversedLList.add(current.getData());
    } else {
      // do nothing
    }
  }

}
