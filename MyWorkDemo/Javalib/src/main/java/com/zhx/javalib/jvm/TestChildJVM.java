package com.zhx.javalib.jvm;

public class TestChildJVM extends TestJVM {
    static {
        System.out.println("static block in TestChildJVM");
    }

    public TestChildJVM() {
        System.out.println("constructor in TestChildJVM");
    }
}
