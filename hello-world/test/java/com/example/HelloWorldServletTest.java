package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldServletTest {

    @Test
    public void testHelloWorld() {
        HelloWorldServlet servlet = new HelloWorldServlet();
        assertNotNull(servlet);
    }
}