package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.web.ws.TestWsImpl;

import javax.xml.ws.Endpoint;

public class PubTestWs {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/test", new TestWsImpl());

    }

}
