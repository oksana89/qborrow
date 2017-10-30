package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.web.ws.TestWs;
import it.quix.academy.qborrrow.web.ws.TestWsImpl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestWsCli {

    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("http://localhost:9999/ws/test");
            QName qname = new QName("http://ws.web.qborrrow.academy.quix.it/", "TestWsImplService");
            Service service = Service.create(url, qname);
            TestWs cli = service.getPort(TestWs.class);
            System.out.println(cli.getResponse("mkyong"));
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

    }

}
