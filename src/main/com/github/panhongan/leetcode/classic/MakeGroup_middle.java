package com.github.panhongan.leetcode.classic;


import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 提供很多pair, 相同pair的数据合并到一个group。
 * A-B
 * B-C
 * C-D
 *
 * E-F
 * F-G
 *
 * 分组：
 * 0 ： A B C D
 * 1 : E F G
 */

public class MakeGroup_middle {

    public static Map<Integer, Set<String>> group(List<Pair<String, String>> pairs) {
        Map<Integer, Set<String>> groupMap = new HashMap<>();
        Map<String, Integer> productMap = new HashMap<>();
        int groupId = 0;

        for (Pair<String, String> p : pairs) {
            if (!productMap.containsKey(p.getLeft()) && !productMap.containsKey(p.getRight())) {
                int hashCode = groupId++;
                productMap.put(p.getLeft(), hashCode);
                productMap.put(p.getRight(), hashCode);

                Set<String> member = groupMap.getOrDefault(hashCode, new HashSet<>());
                member.add(p.getLeft());
                member.add(p.getRight());
                groupMap.put(hashCode, member);
            } else if (productMap.containsKey(p.getLeft()) && !productMap.containsKey(p.getRight())) {
                int group = productMap.get(p.getLeft());
                groupMap.get(group).add(p.getRight());
                productMap.put(p.getRight(), group);
            } else if (!productMap.containsKey(p.getLeft()) && productMap.containsKey(p.getRight())) {
                int group = productMap.get(p.getRight());
                groupMap.get(group).add(p.getLeft());
                productMap.put(p.getLeft(), group);
            } else {
                int groupLeft = productMap.get(p.getLeft());
                int groupRight = productMap.get(p.getRight());
                if (groupLeft != groupRight) {
                    Set<String> newMembers = new HashSet<>();
                    Set<String> rightMembers = groupMap.get(groupRight);
                    newMembers.addAll(groupMap.get(groupLeft));
                    newMembers.addAll(rightMembers);
                    groupMap.put(groupLeft, newMembers);
                    groupMap.remove(groupRight);

                    // right group的重新指向left
                    for (String s : rightMembers) {
                        productMap.put(s, groupLeft);
                    }
                }
            }
        }

        return groupMap;
    }


    public static void main(String[] args) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(Pair.of("A", "B"));
        list.add(Pair.of("B", "A"));
        list.add(Pair.of("A", "C"));
        list.add(Pair.of("C", "B"));
        list.add(Pair.of("B", "D"));
        list.add(Pair.of("D", "M"));
        list.add(Pair.of("K", "M"));
        list.add(Pair.of("A", "K"));
        list.add(Pair.of("J", "Q"));
        list.add(Pair.of("Q", "I"));
        list.add(Pair.of("I", "Q"));
        list.add(Pair.of("J", "I"));
        list.add(Pair.of("I", "J"));

        list.add(Pair.of("X", "E"));
        list.add(Pair.of("Y", "R"));
        list.add(Pair.of("E", "R"));

        System.out.println(group(list));
    }

}
