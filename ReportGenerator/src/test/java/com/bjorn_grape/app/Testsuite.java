package com.bjorn_grape.app;

import org.junit.Test;

public class Testsuite {

    public void assertEq(Object a, Object b)
    {
        assert a.equals(b);
    }

    @Test
    public void testReadFile() {


    }

    @Test
    public void testSomething2() {

        String a = "dd";
        String b = "dd";
        assertEq(a,b);
    }

    @Test
    public void testSomething3() {

        assert true;
    }
}
