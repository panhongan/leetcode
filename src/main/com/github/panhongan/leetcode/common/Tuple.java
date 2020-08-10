package com.github.panhongan.leetcode.common;

public class Tuple {

    public static class Tuple2<T1, T2> {
        private T1 _1;
        private T2 _2;

        public static <T1, T2> Tuple2 of(T1 t1, T2 t2) {
            Tuple2 tuple2 = new Tuple2();
            tuple2._1 = t1;
            tuple2._2 = t2;
            return tuple2;
        }

        @Override
        public String toString() {
            return "(" + _1 + "," + _2 + ")";
        }
    }
}
