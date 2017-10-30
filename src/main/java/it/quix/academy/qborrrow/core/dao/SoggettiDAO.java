package it.quix.academy.qborrrow.core.dao;

import it.quix.academy.qborrrow.core.dao.generated.SoggettiAbstractDAO;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The DAO for Soggetti entity.o
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
            super.setParameterString(statement, p++, soggetti.getRagioneSociale());
            super.setParameterString(statement, p++, soggetti.getNome());
            super.setParameterString(statement, p++, soggetti.getCognome());
            super.setParameterString(statement, p++, soggetti.getImmagine());
            super.setParameterDate(statement, p++, soggetti.getDataUltimaModifica());
            super.setParameterDate(statement, p++, soggetti.getDataCompleanno());

            // Set the primary key
            super.setParameterString(statement, p++, soggetti.getUsername());

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

    public Soggetti getWithCompleanno(String user_name) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM soggetti ").append(EOL);
            query.append("WHERE USER_NAME = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            int p = 1;
            // Set the primary key
            super.setParameterString(statement, p++, user_name);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (rs.next()) {
                Soggetti soggettiModel = buildModelFromResultSet1(rs);
                return soggettiModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Soggetti on database with [user_name = ", user_name, "]"));

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method get(String user_name) for Soggetti on database with [user_name = ", user_name, "]");
            if (log.isErrorEnabled()) {
                log.error(msg);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public Soggetti getProfilo(String user_name) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM soggetti ").append(EOL);
            query.append("WHERE USER_NAME = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            int p = 1;
            // Set the primary key
            super.setParameterString(statement, p++, user_name);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (rs.next()) {
                Soggetti soggettiModel = buildModelFromResultSet1(rs);
                return soggettiModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Soggetti on database with [user_name = ", user_name, "]"));

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method get(String user_name) for Soggetti on database with [user_name = ", user_name, "]");
            if (log.isErrorEnabled()) {
                log.error(msg);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    protected Soggetti buildModelFromResultSet1(ResultSet rs) throws SQLException {
        return buildModelCompleanno(rs, qborrrowManager);
    }

    public Soggetti buildModelCompleanno(ResultSet rs, QborrrowManager qborrrowManager) throws SQLException {
        Soggetti soggetti = new Soggetti();

        soggetti.setJdbc(true);
        soggetti.setQborrrowManager(qborrrowManager);
        soggetti.setUsername(getParameterString(rs, "user_name"));
        soggetti.setRagioneSociale(getParameterString(rs, "ragione_sociale"));
        soggetti.setNome(getParameterString(rs, "nome"));
        soggetti.setCognome(getParameterString(rs, "cognome"));
        soggetti.setEmail(getParameterString(rs, "email"));
        soggetti.setImmagine(getParameterString(rs, "immagine"));
        soggetti.setDataUltimaModifica(getParameterDate(rs, "data_ultima_modifica"));
        soggetti.setDataCompleanno(getParameterDate(rs, "data_compleanno"));
        return soggetti;
    }

    /**
     * Updates a record of type Soggetti on table soggetti
     * 
     * @param soggetti The Soggetti to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateCompleanno(Soggetti soggetti) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE soggetti SET ").append(EOL);
            query.append(" email = ? , ragione_sociale = ? , nome = ? , cognome = ? , immagine = ? , data_ultima_modifica = ?,  data_compleanno = ? ").append(
                EOL);
            query.append("  WHERE user_name = ? ").append(EOL);

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
            super.setParameterString(statement, p++, soggetti.getRagioneSociale());
            super.setParameterString(statement, p++, soggetti.getNome());
            super.setParameterString(statement, p++, soggetti.getCognome());
            super.setParameterString(statement, p++, soggetti.getImmagine());
            super.setParameterDate(statement, p++, soggetti.getDataUltimaModifica());
            super.setParameterDate(statement, p++, soggetti.getDataCompleanno());

            // Set the primary key
            super.setParameterString(statement, p++, soggetti.getUsername());

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