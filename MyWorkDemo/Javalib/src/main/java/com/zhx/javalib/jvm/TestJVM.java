package com.zhx.javalib.jvm;

public class TestJVM {

    public static int value  = 100;

    static {
        System.out.println("static block in TestJVM");
    }

    public TestJVM(){
        System.out.println("constructor in TestJVM");
    }

    public int a() {
        int i = 100;
        int j = 200;
        return add(i, j);
    }

    public int add(int s1, int s2) {
        int a = s1;
        int b = s2;
        return a + b;
    }
}
