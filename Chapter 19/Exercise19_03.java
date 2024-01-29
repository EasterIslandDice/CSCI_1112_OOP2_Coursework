/*
Author: William Flanagan
Date: 1/29/24

Description: Generics Skill - Fundamental
 */
import java.util.ArrayList;

public class Exercise19_03 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(14);
    list.add(42);
    list.add(25);
    
    ArrayList<Integer> newList = removeDuplicates(list);
    
    System.out.print(newList);
  }


public static <E extends Comparable<E>> ArrayList<E> removeDuplicates(ArrayList<E> list) {
  for (int i = 0; i < list.size() - 1; i++) {
    for (int j = i + 1; j < list.size(); j++) {
      if (list.get(i).compareTo(list.get(j)) == 0)
        list.remove(j);
    }
  }
  return list;
} 
  
}