package it.quix.academy.qborrrow.core.model;

import java.io.Serializable;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import flexjson.JSON;

import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.manager.ManagerHolder;

import it.quix.framework.core.model.AbstractModel;

public class QborrrowAbstractModel extends AbstractModel implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1764636486333100576L;

    @Transient
    protected boolean jdbc = false;

    @Transient
    private transient QborrrowManager qborrrowManager;

    @Transient
    private transient SysAttributeHandler sysAttributeHandler;

    protected static String stringSeparator = "|";

    public QborrrowAbstractModel() {
        super();
        this.jdbc = true;
    }

    public QborrrowAbstractModel(QborrrowManager qborrrowManager) {
        super();
        this.jdbc = true;
        this.qborrrowManager = qborrrowManager;
    }

    public QborrrowAbstractModel(boolean jdbc, QborrrowManager qborrrowManager) {
        super();
        this.jdbc = jdbc;
        this.qborrrowManager = qborrrowManager;
    }

    @JSON(include = false)
    @XmlTransient
    public boolean isJdbc() {
        return jdbc;
    }

    public void setJdbc(boolean jdbc) {
        this.jdbc = jdbc;
    }

    @JSON(include = false)
    @XmlTransient
    public QborrrowManager getQborrrowManager() {
        if (qborrrowManager != null) {
            return qborrrowManager;
        }
        return (QborrrowManager) ManagerHolder.getManagerOnThreadLocal("qborrrowManager");
    }

    public void setQborrrowManager(QborrrowManager qborrrowManager) {
        this.qborrrowManager = qborrrowManager;
    }

    @JSON(include = false)
    @XmlTransient
    public SysAttributeHandler getSysAttributeHandler() {
        if (sysAttributeHandler != null) {
            return sysAttributeHandler;
        }
        return (SysAttributeHandler) ManagerHolder.getManagerOnThreadLocal("sysAttributeHandler");
    }

    public void setSysAttributeHandler(SysAttributeHandler sysAttributeHandler) {
        this.sysAttributeHandler = sysAttributeHandler;
    }

}