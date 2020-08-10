package com.github.panhongan.leetcode;

import com.github.panhongan.leetcode.common.Tuple.Tuple2;

/**
 * 最大子序列和
 */
public class MaxSumOfSumSeq_53_simple {


    public static int maxSum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (currSum > 0) {
                currSum += arr[i];
            } else {
                currSum = arr[i];
            }

            if (currSum > maxSum) {
                maxSum = currSum;
            }
        }

        return maxSum;
    }

    public static Tuple2 maxSumAndPos(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        int maxBegin = -1;
        int maxEnd = -1;
        int[] beginPos = new int[arr.length];
        int beginCount = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (currSum > 0) {
                currSum += arr[i];
            } else {
                currSum = arr[i];
                beginPos[beginCount++] = i; // 记录每一段的开始
            }

            if (currSum > maxSum) {
                maxSum = currSum;
                maxEnd = i;
            }
        }

        // 找到不大于且最接近maxEnd的值
        for (int i = beginCount - 1; i >= 0; --i) {
            if (beginPos[i] <= maxEnd) {
                maxBegin = beginPos[i];
                break;
            }
        }

        return Tuple2.of(maxSum, Tuple2.of(maxBegin, maxEnd));
    }

    public static void main(String[] args) {
        int[] arr = {0, -1, 0, -4, -10};
        System.out.println(maxSum(arr));
        System.out.println(maxSumAndPos(arr));
    }
}

