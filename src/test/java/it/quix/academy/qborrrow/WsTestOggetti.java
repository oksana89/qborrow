package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.web.ws.WsOggetti;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class WsTestOggetti {

    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("http://localhost:9998/ws/test3");
            QName qname = new QName("http://ws.web.qborrrow.academy.quix.it/", "WsImplOggettiService");
            Service service = Service.create(url, qname);
            WsOggetti cli = service.getPort(WsOggetti.class);
            System.out.println(cli.getResponse(1));
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

    }

}
