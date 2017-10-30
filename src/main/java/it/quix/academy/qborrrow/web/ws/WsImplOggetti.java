package it.quix.academy.qborrrow.web.ws;

import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Oggetti;

import javax.jws.WebService;

@WebService(endpointInterface = "it.quix.academy.qborrrow.web.ws.WsOggetti")
public class WsImplOggetti implements WsOggetti {

    public Oggetti getResponse(Integer id) {
        Oggetti oggetto = new Oggetti();
        id = 1;
        oggetto.setId(id);
        return oggetto;
    }

}
