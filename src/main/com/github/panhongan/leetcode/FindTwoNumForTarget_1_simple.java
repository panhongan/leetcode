package com.github.panhongan.leetcode;

import com.github.panhongan.leetcode.common.Tuple.Tuple2;

public class FindTwoNumForTarget_1_simple {

    public static Tuple2<Integer, Integer> findTwoNum(int[] arr, int target) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < target) {
                int left = target - arr[i];

                for (int j = i + 1; j < arr.length; ++j) {
                    if (arr[j] == left) {
                        return Tuple2.of(i, j);
                    }
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(findTwoNum(arr, 9));
        System.out.println(findTwoNum(arr, 8));
        System.out.println(findTwoNum(arr, 10));
        System.out.println(findTwoNum(arr, 11));
        System.out.println(findTwoNum(arr, 13));
        System.out.println(findTwoNum(arr, 18));
        System.out.println(findTwoNum(arr, 26));
    }
}
