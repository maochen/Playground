package org.maochen;


public class Account {

    public void print() {
        System.out.println("print() is running ");
    }

    public String getName() {
        return "abc";
    }

    public void throwException() {
        // throw new RuntimeException("Exception");
    }
}