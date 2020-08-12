package com.github.panhongan.leetcode.classic;

/**
 * 给定字符串s，对于任意子串s1,
 * f = len(s1) * s1出现的次数
 * 求出f的最大值
 */

public class MaxSubstrLenMultiTimes_hard {

    public static int max(String str) {
        int maxValue = 0;

        for (int i = 0; i < str.length() - 1; ++i) {
            for (int j = i + 1; j <= str.length(); ++j) {
                String subStr = str.substring(i,j);
                //System.out.println(subStr);

                // 统计substr出现的次数
                int count = 0;

                int pos = str.indexOf(subStr, 0);
                while (pos >= 0) {
                    ++count;
                    pos = str.indexOf(subStr, pos + 1);
                }

                //System.out.println(subStr + "=" + count);
                if (maxValue < count * subStr.length()) {
                    maxValue = count * subStr.length();
                }
            }
        }

        return maxValue;
    }


    public static void main(String[] args) {
        String str = "aaaaaa";
        System.out.println(max(str));

        str = "abcabcddd";
        System.out.println(max(str));
    }

}
