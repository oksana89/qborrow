package it.quix.academy.qborrrow.web.rs;

import it.quix.academy.qborrrow.core.dao.OggettiDAO;
import it.quix.academy.qborrrow.core.dao.SoggettiDAO;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Oggetti;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.search.OggettiSearch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/oggetti")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestRs {

    @Resource(name = "qborrrowManager")
    private QborrrowManager qborrrowManager;

    @GET
    public Response getOggetti() {
        getQborrrowManager().getMieiOggettiList(null);

        return Response.ok().build();

    }

    @GET
    @Path("/{id}")
    public Oggetti getOggetto(@PathParam("id") Integer id) {
        Oggetti ogg = new Oggetti();
        ogg.setId(id);
        // OggettiDAO oggDAO= new OggettiDAO();
        return ogg;
    }

    @POST
    public Oggetti postOggetto(Oggetti Oggetto) {
        Oggetti ogg = new Oggetti();
        ogg.setId(88);
        ogg.setTitolo("PROVA");
        return ogg;
    }

    @PUT
    public Oggetti putOggetto(Oggetti oggetto) {
        return oggetto;
    }

    @DELETE
    public String deleteOggetti() {
        return "{\"messagge\": \"OK\"}";
    }

    /**
     * @return the qborrrowManager
     */
    public QborrrowManager getQborrrowManager() {
        return qborrrowManager;
    }

    /**
     * @param qborrrowManager the qborrrowManager to set
     */
    public void setQborrrowManager(QborrrowManager qborrrowManager) {
        this.qborrrowManager = qborrrowManager;
    }

}
