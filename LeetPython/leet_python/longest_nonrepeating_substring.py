"""
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:

Input: s = ""
Output: 0



Constraints,:/[<

    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.
"""


def longest_in(s: str) -> int:
    if s == "":
        return 0

    longest = 1

    for n in range(0, len(s)):
        last = s[n]
        for ch in s[n + 1:]:
            if ch not in last:
                last += ch
                if len(last) > longest:
                    longest = len(last)
            else:
                last = ch

    return longest
