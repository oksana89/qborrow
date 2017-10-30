package it.quix.academy.qborrrow;

import it.quix.academy.qborrrow.core.model.QborrrowUserContext;
import it.quix.academy.qborrrow.mail.MailAcademy;
import it.quix.framework.core.manager.ManagerHolder;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailTest {

    public MailTest() {

    }

    private static Log log = LogFactory.getLog(MailTest.class);

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
    public void SimpleMailTest() {

        log.info("Mio test sulla email!!!!");

        MailAcademy mailServiceCustom = applicationContext.getBean(MailAcademy.class);
        mailServiceCustom.sendEmail("matteo.renzi@quix.it", "PROVA SPRING OKSANA", "PROVA INVIO EMAIL");

        log.info("END");
    }
}
