package com.github.panhongan.leetcode;

import java.util.Arrays;

/**
 * 最大无重复字符的串
 *
 * 从头到尾扫描，依次找到无重复字符的串；
 * 比较长度，记录最大长度以及串的开始和结束为止。
 */
public class MaxNonDupStr_3_middle {

    public static String maxNonDupStr(String str) {
        int maxLen = -1;
        int maxStrBeginPos = 0;
        int maxStrEndPos = 1;
        int beginPos = 0;
        int i = 1;

        char[] map = new char[128];
        map[str.charAt(beginPos)] = 1;

        while (i < str.length()) {
            char ch = str.charAt(i);
            if (map[ch] != 1) {
                map[ch] = 1;
                ++i;
            } else {
                int len = i - beginPos;
                if (len > maxLen) {
                    maxLen = len;

                    maxStrBeginPos = beginPos;
                    maxStrEndPos = i;
                }

                Arrays.fill(map, (char) 0);
                beginPos = i;
                map[str.charAt(beginPos)] = 1;

                ++i;
            }
        }

        int len = i - beginPos;
        if (len > maxLen) {
            maxLen = len;

            maxStrBeginPos = beginPos;
            maxStrEndPos = i;
        }

        return str.substring(maxStrBeginPos, maxStrEndPos);
    }

    public static void main(String[] args) {
        String str = "a";
        System.out.println(maxNonDupStr(str));

        str = "aab";
        System.out.println(maxNonDupStr(str));

        str = "abcabcbb";
        System.out.println(maxNonDupStr(str));

        str = "bbbbb";
        System.out.println(maxNonDupStr(str));

        str = "pwwkew";
        System.out.println(maxNonDupStr(str));
    }

}
