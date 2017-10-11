package it.quix.academy.qborrrow.core.dao;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrrow.core.dao.generated.PrestitiAbstractDAO;

/**
 * The DAO for Prestiti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 14:58:54
 */
public class PrestitiDAO extends PrestitiAbstractDAO {

    private static Log log = LogFactory.getLog(PrestitiDAO.class);

    public PrestitiDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("PrestitiDAO initialized!");
        }
    }

}