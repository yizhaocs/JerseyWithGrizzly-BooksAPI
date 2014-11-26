package com.fuhu;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookResourceTest extends JerseyTest{
    protected Application configure() {
        // Reference: 23.4.1. JerseyTest Features
        // Link: https://jersey.java.net/documentation/latest/test-framework.html
        // The code in the example above enables test traffic logging (inbound and outbound headers) as well as dumping the HTTP message entity as part of the traffic logging.
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig().packages("com.fuhu");
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetBook() {
        Book response = target("books").path("1").request().get(Book.class);
        assertNotNull(response);
    }

    @Test
    public void testGetBooks(){
        Collection<Book> response = target("books").request().get(new GenericType<Collection<Book>>(){});
        assertEquals(2,response.size());
    }
}

