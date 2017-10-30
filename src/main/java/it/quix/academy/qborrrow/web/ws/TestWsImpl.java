package it.quix.academy.qborrrow.web.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "it.quix.academy.qborrrow.web.ws.TestWs")
public class TestWsImpl implements TestWs {

    public String getResponse(String name) {

        return "response" + name;
    }

}
