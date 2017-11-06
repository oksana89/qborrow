package it.quix.academy.qborrrow.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrrow.core.dao.generated.PrestitiAbstractDAO;
import it.quix.academy.qborrrow.core.model.Prestiti;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The DAO for Prestiti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 12/10/2017 12:46:09
 */
public class PrestitiDAO extends PrestitiAbstractDAO {

    private static Log log = LogFactory.getLog(PrestitiDAO.class);

    public PrestitiDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("PrestitiDAO initialized!");
        }
    }

    public void createMio(Prestiti prestiti) throws DAOCreateException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the insert query
            StringBuilder query = new StringBuilder(EOL);
            query.append("INSERT INTO prestiti ").append(EOL);
            query.append("   (BENEFICIARIO, OGGETTO_PRESTATO, DATA_PRESTITO, DATA_SCADENZA_PRESTITO, DATA_RESO) ").append(EOL);
            query.append(" VALUES ").append(EOL);
            query.append(" (?, ?, ?, ?, ?) ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // set prePersist
            prestiti.prePersist(configuration);
            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, prestiti.getSoggettiUsername());
            super.setParameterInteger(statement, p++, prestiti.getOggetti_id());
            super.setParameterDate(statement, p++, prestiti.getDataPrestito());
            super.setParameterDate(statement, p++, prestiti.getScadenzaPrestito());
            super.setParameterDate(statement, p++, prestiti.getDataReso());
            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfCreatedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfCreatedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("An error occurred creating Prestiti on database. No record created. Number of inserted rows: ",
                        numberOfCreatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOCreateException(msg);
            }
        } catch (SQLException ex) {
            String msg = "Unexpeted error on create Prestiti on database.";
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