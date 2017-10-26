package com.peaceful.leedcode.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/3/30.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(findLongestSustring("abcdefgabcdef"));
    }

    // abcdabcdef
    private static String findLongestSustring(String src) {
        Queue sub = new Queue();
        int max = 0;
        String result = null;
        int i = 0, j = 0;
        while (i < src.length() && j < src.length()) {
            if (sub.contains(src.charAt(j))) {
                if (sub.length() <= max) {

                } else {
                    max = sub.length();
                    result = sub.getStr();
                }
                i++;
                j = i;
                sub.clear();
            } else {
                sub.put(src.charAt(j));
                j++;
            }
        }
        return "max length:" + max + " result:" + result;
    }

    private static class Queue {
        List<Character> characters = new ArrayList<Character>();

        void put(Character character) {
            characters.add(character);
        }

        int length() {
            return characters.size();
        }

        boolean contains(char character) {
            return characters.contains(character); // 可以通过Map优化查询速率
        }

        String getStr() {
            StringBuilder builder = new StringBuilder();
            for (Character c : characters) {
                builder.append(c);
            }
            return builder.toString();
        }

        void clear() {
            characters.clear();

        }
    }


}
