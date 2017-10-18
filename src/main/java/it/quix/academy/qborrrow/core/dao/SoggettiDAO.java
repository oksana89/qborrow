package it.quix.academy.qborrrow.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrrow.core.dao.generated.SoggettiAbstractDAO;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The DAO for Soggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 12/10/2017 12:46:09
 */
public class SoggettiDAO extends SoggettiAbstractDAO {

    private static Log log = LogFactory.getLog(SoggettiDAO.class);

    public SoggettiDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("SoggettiDAO initialized!");
        }
    }
    
    /**
     * Updates a record of type Soggetti on table soggetti
     * 
     * @param soggetti The Soggetti to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateWithCompleanno(Soggetti soggetti) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE soggetti SET ").append(EOL);
            query.append(" email = ? , ragione_sociale = ? , nome = ? , cognome = ? , immagine = ? , data_ultima_modifica = ? , data_compleanno ").append(EOL);
            query.append(" WHERE date_format((data_ultima_modifica, '%Y') % 2) = 0 ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            soggetti.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, soggetti.getEmail());
            super.setParameterString(statement, p++, soggetti.getRagione_sociale());
            super.setParameterString(statement, p++, soggetti.getNome());
            super.setParameterString(statement, p++, soggetti.getCognome());
            super.setParameterString(statement, p++, soggetti.getImmagine());
            super.setParameterDate(statement, p++, soggetti.getData_ultima_modifica());
            super.setParameterDate(statement, p++, soggetti.getData_compleanno());

            // Set the primary key
            super.setParameterString(statement, p++, soggetti.getUser_name());

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfUpdatedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfUpdatedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("Error while updating the record of type Soggetti ", soggetti, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Soggetti ", soggetti, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

}