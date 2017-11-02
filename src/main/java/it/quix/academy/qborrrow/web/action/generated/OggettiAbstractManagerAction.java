package it.quix.academy.qborrrow.web.action.generated;

import flexjson.JSONSerializer;
import it.quix.academy.qborrrow.core.manager.QborrrowException;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Oggetti;
import it.quix.academy.qborrrow.core.model.Prestiti;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.search.OggettiSearch;
// import it.quix.academy.qborrrow.core.handler.OggettiHandler;
import it.quix.academy.qborrrow.web.action.QborrrowManagerAction;
import it.quix.framework.core.composer.ExcelComposer;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.model.AttributeView;
import it.quix.framework.core.validation.exception.ValidationException;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;

/**
 * Action class for the Oggetti model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class OggettiAbstractManagerAction extends QborrrowManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(OggettiAbstractManagerAction.class);

    private List<Oggetti> oggettiList = new ArrayList<Oggetti>();

    /**
     * QborrrowManager injected by Spring
     */
    @Resource(name = "qborrrowManager")
    private QborrrowManager qborrrowManager;

    /**
     * SysAttributeHandler injected by Spring
     */
    @Resource(name = "sysAttributeHandler")
    private SysAttributeHandler sysAttributeHandler;

    /**
     * Search filters
     */
    private OggettiSearch oggettiSearch = new OggettiSearch();

    /**
     * Edit model
     */
    private Oggetti oggetti = null;

    /**
     * Method for prepare the list task called if the PrepareInterceptor is applied to the ActionConfig.
     * This method run after injection of value form Session and before injection of parameter from querystring.
     * This method is very useful for any situation where you need to ensure some logic runs before the actual
     * execute method runs.
     */
    public void prepareList() {
        // Read reset parameter. This method is invoked before injection of parameter,
        // this parameter must be read by ActionContext class.
        String[] reset = (String[]) ActionContext.getContext().getParameters().get("reset");
        // if reset = true then reset the search model
        if (reset != null && reset.length > 0 && reset[0].equals("true")) {
            if (log.isInfoEnabled()) {
                log.info("Reset the oggettiSearch model");
            }
            oggettiSearch = new OggettiSearch();
            oggettiSearch.setRowPerPage(10);
        }
        // if first call the search model must be initialized
        if (oggettiSearch == null) {
            oggettiSearch = new OggettiSearch();
            oggettiSearch.setRowPerPage(10);
            if (log.isInfoEnabled()) {
                log.info("oggettiSearch initialized");
            }
        }
    }

    /**
     * List task.
     * This method find oggetti that satisfy search filters.
     * 
     * @throws QborrrowException if an error occurs
     */
    public String list() throws QborrrowException {
        try {
            // Validate the search model
            getQborrrowManager().validateOggettiSearch(oggettiSearch);
            // Perform count of record that satisfy search filters
            long total = getQborrrowManager().countOggetti(oggettiSearch);
            // If there are results ...
            List<Oggetti> oggettiList = null;
            if (total > 0) {
                // Search the results to display
                do {
                    oggettiList = getQborrrowManager().getOggettiList(oggettiSearch);
                    if (oggettiList.isEmpty() && oggettiSearch.getPage() > 0) {
                        if (log.isInfoEnabled()) {
                            log.info("The request page " + oggettiSearch.getPage() + " was empty."
                                + ((oggettiSearch.getPage() > 1) ? " Try with page " + (oggettiSearch.getPage() - 1) + "." : ""));
                        }
                        oggettiSearch.setPage(oggettiSearch.getPage() - 1);
                    }
                } while (0 < oggettiSearch.getPage() && oggettiList.isEmpty());
            }

            // Compose the response
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", oggettiList);
            map.put("count", total);
            return manageSerialize(map, new JSONSerializer().include("list"));
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "list");
        } catch (Exception e) {
            return manageException("Error on list Oggetti", e);
        }
    }

    public String listMieiOggettiStruts() {
        oggettiSearch = new OggettiSearch();
        oggettiSearch.setPage(0);
        oggettiSearch.setRowPerPage(10);
        oggettiList = getQborrrowManager().getOggettiList(oggettiSearch);
        return "listMieiOggetti";
    }

    /**
     * XLS Export task.
     * This method export all oggetti in xls format.
     * 
     * @throws Exception if an error occurs
     */
    public String exportXls() throws Exception {
        int page = oggettiSearch.getPage();
        oggettiSearch.setPage(-1);
        // retrieve the list from DB
        List<Oggetti> oggettiList = getQborrrowManager().getOggettiList(oggettiSearch);
        long countOggetti = getQborrrowManager().countOggetti(oggettiSearch);

        // create the file
        File tmpXlsFile = File.createTempFile("OggettiList", ".xls");
        // populate the file
        ExcelComposer c = new ExcelComposer() {

            @Override
            protected Object translate(Field field, Object value) {
                if (field.getName().equals("categoria")) {
                    String stringValue = (String) value;
                    try {
                        AttributeView av =
                            sysAttributeHandler.getAttribute("QBO000_categoria", getUserContext().getLanguageForSysAttribute(), getUserContext()
                                .getOrganizationForSysSysAttribute(), stringValue, getUserContext());
                        if (av != null) {
                            return av.getDescription();
                        }
                    } catch (DAOFinderException dfe) {
                        log.debug("Cannot find sysAttribute with type = QBO000_categoria and code = stringValue", dfe);
                    }
                    return stringValue;
                }
                if (field.getName().equals("soggetti")) {
                    String stringValue = "";
                    Soggetti soggetti = (Soggetti) value;
                    if (soggetti != null) {
                        stringValue = String.valueOf(soggetti.getUsername());
                    }
                    return stringValue;
                }
                return super.translate(field, value);
            }
        };

        c.create(getUserContext(), oggettiList, Oggetti.class, "excel.oggetti", "excel.oggetti.name", 1, 2, countOggetti, oggettiSearch, tmpXlsFile);
        oggettiSearch.setPage(page);
        return sendFileToClient(tmpXlsFile, "application/vnd.ms-excel");
    }

    /**
     * Edit task.
     * This method edit one oggetti instance.
     */
    public String edit() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            oggetti = getQborrrowManager().getOggetti(oggetti.getId());
            List<Prestiti> listPrestito = getQborrrowManager().getPrestitiListByOggetti(oggetti.getId());
            if (listPrestito.size() > 0) {
                Prestiti prestito = listPrestito.get(0);
                oggetti.setPrestito(prestito);
                oggetti.setOggettoPrestato(true);
            }
            return manageSerialize(oggetti);
        } catch (Exception e) {
            return manageException("Error on edit Oggetti", e);
        }
    }

    /**
     * Save task.
     * This method save one oggetti instance.
     */
    public String save() {
        if (oggetti == null) {
            // New Oggetti and all fields are empty. Create a new empty Oggetti to avoid NPE on validators.
            oggetti = new Oggetti();
        }
        /*
         * List<Prestiti> listPrestito = getQborrrowManager().getPrestitiListByOggetti(oggetti.getId());
         * if (listPrestito.size() > 0) {
         * Prestiti prestito = listPrestito.get(0);
         * oggetti.setPrestito(prestito);
         * oggetti.setOggettoPrestato(true);
         * }
         */
        try {
            getQborrrowManager().saveOggetti(oggetti);
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Oggetti", e);
        }
    }

    /**
     * Delete task.
     * This method delete one oggetti instance.
     */
    public String delete() {
        try {
            getQborrrowManager().deleteOggetti(oggetti.getId());
            return manageOkMessage();
        } catch (Exception e) {
            return manageException("Error on delete Oggetti", e);
        }
    }

    /**
     * @see #qborrrowManager
     * @return the qborrrowManager
     */
    public QborrrowManager getQborrrowManager() {
        return qborrrowManager;
    }

    /**
     * @see #qborrrowManager
     * @param qborrrowManager the qborrrowManager to set
     */
    public void setQborrrowManager(QborrrowManager qborrrowManager) {
        this.qborrrowManager = qborrrowManager;
    }

    /**
     * @see #SysAttributeHandler
     * @return the SysAttributeHandler
     */
    public SysAttributeHandler getSysAttributeHandler() {
        return sysAttributeHandler;
    }

    /**
     * @see #SysAttributeHandler
     * @param sysAttributeHandler the SysAttributeHandler to set
     */
    public void setSysAttributeHandler(SysAttributeHandler sysAttributeHandler) {
        this.sysAttributeHandler = sysAttributeHandler;
    }

    /**
     * @see #oggettiSearch
     * @return the oggettiSearch
     */
    public OggettiSearch getOggettiSearch() {
        return oggettiSearch;
    }

    /**
     * @see #oggettiSearch
     * @param oggettiSearch the oggettiSearch to set
     */
    public void setOggettiSearch(OggettiSearch oggettiSearch) {
        this.oggettiSearch = oggettiSearch;
    }

    /**
     * @see #oggetti
     * @return the oggetti
     */
    public Oggetti getOggetti() {
        return oggetti;
    }

    /**
     * @see #oggetti
     * @param oggetti the oggetti to set
     */
    public void setOggetti(Oggetti oggetti) {
        this.oggetti = oggetti;
    }

    /**
     * @return the oggettiList
     */
    public List<Oggetti> getOggettiList() {
        return oggettiList;
    }

    /**
     * @param oggettiList the oggettiList to set
     */
    public void setOggettiList(List<Oggetti> oggettiList) {
        this.oggettiList = oggettiList;
    }
}
