package com.github.panhongan.leetcode.classic;

/**
 * 一个线程打印0，一个线程打印奇数，一个线程打印偶数
 * 给定n, 打印：0 1 0 2 0 3 0 4 ... 0 n-1 0 n
 */
public class MultiThreadPrintNumByOrder_middle {

    public static class A {

        private volatile boolean firstPrintZero = true;

        private int num;

        private volatile int count = 1;

        public A(int num) {
            this.num = num;
        }

        public int getCount() {
            return count;
        }

        public boolean isFirstPrintZero() {
            return firstPrintZero;
        }

        public void setFirstPrintZero() {
            firstPrintZero = false;
        }

        public synchronized void printZero() {
            System.out.print("0 ");
        }

        public synchronized void printNum() {
            System.out.print(count + " ");
            ++count;
        }

        public boolean isNotOver() {
            return (count <= num);
        }
    }

    public static class ZeroRunnable implements Runnable {
        private A a;
        private Object conditionOdd;
        private Object conditionNonOdd;
        private Object conditionZero;

        public ZeroRunnable(A a, Object conditionOdd, Object conditionNonOdd, Object conditionZero) {
            this.a = a;
            this.conditionOdd = conditionOdd;
            this.conditionNonOdd = conditionNonOdd;
            this.conditionZero = conditionZero;
        }

        @Override
        public void run() {
            try {
                while (a.isNotOver()) {
                    if (a.isFirstPrintZero()) {
                        a.printZero();
                        a.setFirstPrintZero();
                    } else {
                        synchronized (conditionZero) {
                            if (a.getCount() > 1 || !a.isFirstPrintZero()) {
                                conditionZero.wait();
                            }

                            if (a.isNotOver()) {
                                a.printZero();
                            }
                        }
                    }


                    if (a.getCount() % 2 == 1) {
                        synchronized (conditionOdd) {
                            conditionOdd.notify();
                        }
                    } else {
                        synchronized (conditionNonOdd) {
                            conditionNonOdd.notify();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class OddRunnable implements Runnable {
        private A a;
        private Object conditionOdd;
        private Object conditionZero;

        public OddRunnable(A a, Object conditionOdd, Object conditionZero) {
            this.a = a;
            this.conditionOdd = conditionOdd;
            this.conditionZero = conditionZero;
        }

        @Override
        public void run() {
            try {
                while (a.isNotOver()) {
                    synchronized (conditionOdd) {
                        if (a.getCount() == 1 || a.getCount() % 2 == 0) {
                            conditionOdd.wait();
                        }

                        if (a.isNotOver()) {
                            a.printNum();

                            synchronized (conditionZero) {
                                conditionZero.notify();
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class NonOddRunnable implements Runnable {
        private A a;
        private Object conditionNonOdd;
        private Object conditionZero;

        public NonOddRunnable(A a, Object conditionNonOdd, Object conditionZero) {
            this.a = a;
            this.conditionNonOdd = conditionNonOdd;
            this.conditionZero = conditionZero;
        }

        @Override
        public void run() {
            try {
                while (a.isNotOver()) {
                    synchronized (conditionNonOdd) {
                        if (a.getCount() % 2 == 1) {
                            conditionNonOdd.wait();
                        }

                        if (a.isNotOver()) {
                            a.printNum();

                            synchronized (conditionZero) {
                                conditionZero.notify();
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        A a = new A(6);
        Object conditionOdd = new Object();
        Object conditionNonOdd = new Object();
        Object conditionZero = new Object();
        Thread zeroThread = new Thread(new ZeroRunnable(a, conditionOdd, conditionNonOdd, conditionZero));
        Thread oddThread = new Thread(new OddRunnable(a, conditionOdd, conditionZero));
        Thread nonOddThread = new Thread(new NonOddRunnable(a, conditionNonOdd, conditionZero));

        oddThread.start();
        nonOddThread.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        zeroThread.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
