package net.riseup.aguestuser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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

class MergeListsTest {

    @Test
    void ex1(){
        assertEquals(
                new ArrayList<>(Arrays.asList(1,1,2,3,4,4)),
                MergeLists.run(
                        new ArrayList<>(Arrays.asList(1, 2, 4)),
                        new ArrayList<>(Arrays.asList(1, 3, 4))
                )
        );
    }

    @Test
    void ex2(){
        assertEquals(
                new ArrayList<>(),
                MergeLists.run(
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
    }

    @Test
    void ex3(){
        assertEquals(
                new ArrayList<>(Collections.singletonList(0)),
                MergeLists.run(
                        new ArrayList<>(),
                        new ArrayList<>(Collections.singletonList(0))
                )
        );
    }

}