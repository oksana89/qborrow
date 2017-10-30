package it.quix.academy.qborrrow.web.ws;

import it.quix.academy.qborrrow.core.model.Oggetti;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface WsOggetti {

    // Oggetti oggetto = new Oggetti();

    @WebMethod
    Oggetti getResponse(Integer Id);

}
