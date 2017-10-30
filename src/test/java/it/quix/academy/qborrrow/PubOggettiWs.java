package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.web.ws.TestWsImpl;
import it.quix.academy.qborrrow.web.ws.WsImplOggetti;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

public class PubOggettiWs {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9998/ws/test3", new WsImplOggetti());

    }

}
