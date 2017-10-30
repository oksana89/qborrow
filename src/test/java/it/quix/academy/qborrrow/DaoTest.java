package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.core.dao.SoggettiDAO;
import it.quix.academy.qborrrow.core.model.QborrrowUserContext;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.search.SoggettiSearch;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.manager.ManagerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

    public DaoTest() {

    }

    private static Log log = LogFactory.getLog(BaseTest.class);

    protected static ApplicationContext applicationContext;

    @BeforeClass
    public static void setUpClass() throws Exception {

        try {
            String[] contextFileNames = { "qborrrow-test-spring.xml" };
            log.debug("loading application contexts=" + Arrays.toString(contextFileNames));
            applicationContext = new ClassPathXmlApplicationContext(contextFileNames);

            QborrrowUserContext uc = new QborrrowUserContext();
            it.quix.framework.core.manager.UserContextHolder.setUserContext(uc);

            ManagerHolder.setManagerOnThreadLocal("qborrrowManager", applicationContext.getBean("qborrrowManager"));

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assert.assertNull(ex);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        applicationContext = null;
    }

    @Test
    public void createSoggetto() {
        Soggetti soggetto = new Soggetti();
        soggetto.setUsername("oksana45");
        soggetto.setEmail("oksi.kom89@gmail.com");
        soggetto.setRagioneSociale("Komarnytska");
        soggetto.setNome("Oksana");
        soggetto.setCognome("Komarnytska");
        soggetto.setImmagine("immagine");
        soggetto.setDataUltimaModifica(new Date());

        SoggettiDAO soggettoDAO = applicationContext.getBean(SoggettiDAO.class);
        try {
            soggettoDAO.create(soggetto);
        } catch (DAOCreateException e) {

            log.error("Eccezione creazione soggetto!", e);
        }

    }

    @Test
    public void readSoggetto() {
        SoggettiSearch soggettosearch = new SoggettiSearch();
        List<Soggetti> list = new ArrayList<Soggetti>();
        SoggettiDAO soggettoDAO = applicationContext.getBean(SoggettiDAO.class);
        list = soggettoDAO.getList(soggettosearch);
        for (Soggetti soggetti : list) {
            soggetti.toString();
            log.info(soggetti.toString());
        }

    }

    @Test
    public void updateSoggetto() {
        SoggettiSearch soggettoSearch = new SoggettiSearch();
        soggettoSearch.setEmail("oksi.kom89@gmail.com");
        List<Soggetti> list = new ArrayList<Soggetti>();
        SoggettiDAO soggettoDAO = applicationContext.getBean(SoggettiDAO.class);
        list = soggettoDAO.getList(soggettoSearch);
        Soggetti soggetti = new Soggetti();

        if (list != null && list.size() > 0) {
            soggetti = list.get(0);
            soggetti.setEmail("oksana@l.com");
            try {
                soggettoDAO.update(soggetti);
            } catch (DAOStoreException e) {
                // TODO Auto-generated catch block
                log.error("Errore", e);
            }
        }
    }

}
