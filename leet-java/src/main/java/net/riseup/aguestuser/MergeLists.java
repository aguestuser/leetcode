package net.riseup.aguestuser;

import java.util.ArrayList;

/*** EASY ***

 https://leetcode.com/problems/merge-two-sorted-lists/

 Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

 [Example 1:]

 Input: l1 = [1,2,4], l2 = [1,3,4]
 Output: [1,1,2,3,4,4]

 [Example 2:]

 Input: l1 = [], l2 = []
 Output: []

 [Example 3:]

 Input: l1 = [], l2 = [0]
 Output: [0]

 ***/

public class MergeLists {
    public static ArrayList<Integer> run(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        return merge(l1, l2, new ArrayList<>());
    }

    static ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2, ArrayList<Integer> acc) {
        ArrayList<Integer> minList;

        if(l1.isEmpty() && l2.isEmpty()){
            return acc;
        } else if (l1.isEmpty()) {
            minList = l2;
        }  else if (l2.isEmpty()) {
            minList = l1;
        } else if (l2.get(0) < l1.get(0)) {
            minList = l2;
        } else {
            minList = l1;
        }

        acc.add(minList.remove(0));
        return merge(l1, l2, acc);
    }
}
