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

use std::borrow::BorrowMut;

fn merge(v1: Vec<i16>, v2: Vec<i16>) -> Vec<i16> {
    let mut acc: Vec<i16> = Vec::new();

    fn do_merge<'a>(v1: &'a [i16], v2: &'a [i16], acc: &'a mut Vec<i16>) -> &'a Vec<i16> {
        if v1.is_empty() && v2.is_empty() {
            return acc;
        }

        let (to_consume, to_ignore) = if !v1.is_empty() && v2.is_empty() {
            (v1, v2)
        } else if v1.is_empty() && !v2.is_empty() {
            (v2, v1)
        } else if v1[0] < v2[0] {
            (v1, v2)
        } else {
            (v2, v1)
        };

        acc.push(to_consume[0]);
        return do_merge(&to_consume[1..], &to_ignore, acc);
    }

    return do_merge(&v1, &v2, &mut acc).to_vec();
}

fn merge2(v1: Vec<i16>, v2: Vec<i16>) -> Vec<i16> {
    let mut acc: Vec<i16> = Vec::new();
    let mut v1_idx = Box::new(0);
    let mut v2_idx = Box::new(0);

    while *v1_idx < v1.len() || *v2_idx < v2.len() {
        let (min_vec, min_idx) = if *v1_idx == v1.len() {
            (&v2, &mut v2_idx)
        } else if *v2_idx == v2.len() {
            (&v1, &mut v1_idx)
        } else if v2[*v2_idx] < v1[*v1_idx] {
            (&v2, &mut v2_idx)
        } else {
            (&v1, &mut v1_idx)
        };
        acc.push(min_vec[**min_idx]);
        *min_idx.borrow_mut() = **min_idx + 1;
    }

    acc
}

// TODO: do with actual linked lists! (Using RefCell, Cons, etc...)

#[cfg(test)]
mod tests {
    use crate::merge_lists::{merge, merge2};

    #[test]
    fn ex1() {
        assert_eq!(vec![1, 1, 2, 3, 4, 4], merge(vec![1, 2, 4], vec![1, 3, 4]));
    }

    #[test]
    fn ex2() {
        assert_eq!(vec![0i16; 0], merge(vec![], vec![]));
    }

    #[test]
    fn ex3() {
        assert_eq!(vec![0], merge(vec![], vec![0]));
    }

    #[test]
    fn ex1_2() {
        assert_eq!(vec![1, 1, 2, 3, 4, 4], merge2(vec![1, 2, 4], vec![1, 3, 4]));
    }

    #[test]
    fn ex2_2() {
        assert_eq!(vec![0i16; 0], merge2(vec![], vec![]));
    }

    #[test]
    fn ex3_2() {
        assert_eq!(vec![0], merge2(vec![], vec![0]));
    }
}
