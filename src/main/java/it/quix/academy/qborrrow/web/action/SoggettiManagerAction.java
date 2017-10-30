package it.quix.academy.qborrrow.web.action;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.Font;

import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import javax.annotation.Resource;

import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.framework.core.validation.api.InvalidConstraint;
import it.quix.framework.core.validation.exception.ValidationException;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.search.SoggettiSearch;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.manager.QborrrowException;
// import it.quix.academy.qborrrow.core.handler.SoggettiHandler;
import it.quix.academy.qborrrow.web.action.generated.SoggettiAbstractManagerAction;
import it.quix.framework.web.annotation.QwScope;
import it.quix.framework.web.result.IncludeResultAware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * Action class for the Soggetti model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettiManagerAction extends SoggettiAbstractManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(SoggettiManagerAction.class);

    /**
     * Edit task.
     * This method edit one soggetti instance.
     */
    public String editSoggettiWithCompleanno() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Soggetti soggetti = new Soggetti();
            soggetti = getQborrrowManager().getSoggettiWithCompleanno(getSoggetti().getUsername());
            return manageSerialize(soggetti);
        } catch (Exception e) {
            return manageException("Error on edit Soggetti", e);
        }
    }

    public String editProfilo() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // QborrrowManagerAction qma = new QborrrowManagerAction();
            Soggetti soggetti = new Soggetti();
            soggetti = getQborrrowManager().getProfilo(getUserContext().getUserText());
            return manageSerialize(soggetti);
        } catch (Exception e) {
            return manageException("Error on edit Soggetti", e);
        }
    }

    /**
     * Save task.
     * This method save one soggetti instance.
     */
    public String saveWithCompleanno() {
        if (getSoggetti() == null) {
            // New Soggetti and all fields are empty. Create a new empty Soggetti to avoid NPE on validators.
            setSoggetti(new Soggetti());
        }
        try {
            getQborrrowManager().saveSoggettiCompleanno(getSoggetti());
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Soggetti", e);
        }
    }

    public String saveProfile() {
        if (getSoggetti() == null) {
            // New Soggetti and all fields are empty. Create a new empty Soggetti to avoid NPE on validators.
            setSoggetti(new Soggetti());
        }
        try {
            getQborrrowManager().saveProfile(getSoggetti());
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Soggetti", e);
        }
    }

    public String profilo() {
        return "profilo";

    }

}
