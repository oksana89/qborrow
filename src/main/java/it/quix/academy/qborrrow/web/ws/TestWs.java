package it.quix.academy.qborrrow.web.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface TestWs {

    @WebMethod
    String getResponse(String name);

}
