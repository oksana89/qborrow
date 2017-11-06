package it.quix.academy.qborrrow.core.dao.generated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrrow.Configuration;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Prestiti;
import it.quix.academy.qborrrow.core.search.PrestitiSearch;
import it.quix.framework.core.dao.AbstractJDBCDAO;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The Abstract DAO for Prestiti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 12/10/2017 12:46:09
 */
public abstract class PrestitiAbstractDAO extends AbstractJDBCDAO {

    private static final Log log = LogFactory.getLog(PrestitiAbstractDAO.class);

    @Resource(name = "qborrrowManager")
    protected QborrrowManager qborrrowManager;

    @Resource(name = "configuration")
    protected Configuration configuration;

    public PrestitiAbstractDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Create a new record of type Prestiti on table prestiti
     * 
     * @param prestiti The Prestiti to create on database
     * @throws DAOCreateException if no record is created on database
     */
    public void create(Prestiti prestiti) throws DAOCreateException {
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

    /**
     * Updates a record of type Prestiti on table prestiti
     * 
     * @param prestiti The Prestiti to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void update(Prestiti prestiti) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE prestiti SET ").append(EOL);
            query.append(" beneficiario =? , data_prestito = ? , data_scadenza_prestito = ? , data_reso = ? ").append(EOL);
            query.append("  WHERE  oggetto_prestato = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            prestiti.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, prestiti.getSoggettiUsername());
            super.setParameterDate(statement, p++, prestiti.getDataPrestito());
            super.setParameterDate(statement, p++, prestiti.getScadenzaPrestito());
            super.setParameterDate(statement, p++, prestiti.getDataReso());
            // super.setParameterString(statement, p++, prestiti.getSoggettiUsername());
            // Set the primary key

            // super.setParameterString(statement, p++, prestiti.getSoggettiUsername());
            super.setParameterInteger(statement, p++, prestiti.getOggetti_id());

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
                    FrameworkStringUtils.concat("Error while updating the record of type Prestiti ", prestiti, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Prestiti ", prestiti, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Return true if oldPrestiti and newPrestiti are different
     * 
     * @param oldPrestiti first model to compare
     * @param newPrestiti second model to compare
     * @return if the first model is different to the second model
     */
    protected boolean isDirty(Prestiti oldPrestiti, Prestiti newPrestiti) {
        if (oldPrestiti.getDataPrestito() != null && !oldPrestiti.getDataPrestito().equals(newPrestiti.getDataPrestito()))
            return true;
        if (oldPrestiti.getDataPrestito() == null && newPrestiti.getDataPrestito() != null)
            return true;
        if (oldPrestiti.getDataPrestito() != null && !oldPrestiti.getDataPrestito().equals(newPrestiti.getDataPrestito()))
            return true;
        if (oldPrestiti.getDataPrestito() == null && newPrestiti.getDataPrestito() != null)
            return true;
        if (oldPrestiti.getSoggetti() != null && !oldPrestiti.getSoggetti().equals(newPrestiti.getSoggetti()))
            return true;
        if (oldPrestiti.getSoggetti() == null && newPrestiti.getSoggetti() != null)
            return true;
        if (oldPrestiti.getOggetti() != null && !oldPrestiti.getOggetti().equals(newPrestiti.getOggetti()))
            return true;
        if (oldPrestiti.getOggetti() == null && newPrestiti.getOggetti() != null)
            return true;

        return false;
    }

    /**
     * Update on database the newPrestiti only if is different to the oldPrestiti
     * 
     * @param oldPrestiti the old model, used to find difference to the newPrestiti
     * @param newPrestiti the model to store to database if it is different to oldPrestiti
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateIfDirty(Prestiti oldPrestiti, Prestiti newPrestiti) throws DAOStoreException {
        if (isDirty(oldPrestiti, newPrestiti)) {
            update(newPrestiti);
        }
    }

    /**
     * Clone the model pass as parameter into a new model. This method don't copy the toMany fields (List, Set, ...)
     * 
     * This method is static to be also called outside of the Manager
     * 
     * @param prestiti the model to be cloned
     * @return the clone of the model pass as parameter
     */
    public static Prestiti cloneWithoutChildren(Prestiti prestiti) {
        if (!prestiti.isJdbc()) {
            throw new ModelJdbcException(
                "The method PrestitiAbstractDAO.cloneWithoutChildren(Prestiti prestiti) can be executed only on jdbc model. The Prestiti pass as parameter isn't jdbc model.");
        }

        Prestiti newPrestiti = new Prestiti();
        newPrestiti.setJdbc(true);

        newPrestiti.setQborrrowManager(prestiti.getQborrrowManager());
        newPrestiti.setSoggetti(prestiti.getSoggetti());
        newPrestiti.setOggetti(prestiti.getOggetti());
        newPrestiti.setDataPrestito(prestiti.getDataPrestito());
        newPrestiti.setScadenzaPrestito(prestiti.getDataPrestito());
        newPrestiti.setDataReso(prestiti.getDataReso());
        return newPrestiti;
    }

    /**
     * Return a record of Prestiti on table prestiti
     * 
     * @param soggetti_user_name - user_name - Nome utente
     * @param oggetti_id - id - id oggetto
     * @return the object Prestiti
     * @throws DAOFinderException if no record found with passed params
     */
    public Prestiti get(String soggetti_user_name, Integer oggetti_id) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE BENEFICIARIO = ? AND OGGETTO_PRESTATO = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggetti_user_name);
            super.setParameterInteger(statement, p++, oggetti_id);

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
                Prestiti prestitiModel = buildModelFromResultSet(rs);
                return prestitiModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Prestiti on database with [soggetti_user_name = ", soggetti_user_name,
                " oggetti_id = ", oggetti_id, "]"));

        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils.concat(
                    "Error on method get(String soggetti_user_name, Integer oggetti_id) for Prestiti on database with [soggetti_user_name = ",
                    soggetti_user_name, " oggetti_id = ", oggetti_id, "]");
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

    /**
     * Fill all object (Prestiti) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @return the Prestiti object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    protected Prestiti buildModelFromResultSet(ResultSet rs) throws SQLException {
        return buildModelFromResultSet(rs, qborrrowManager);
    }

    /**
     * Fill all object (Prestiti) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrrowManager the manager to set
     * @return the Prestiti object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Prestiti buildModelFromResultSet(ResultSet rs, QborrrowManager qborrrowManager) throws SQLException {
        Prestiti prestiti = new Prestiti();

        prestiti.setJdbc(true);
        prestiti.setQborrrowManager(qborrrowManager);

        prestiti.setSoggetti_username(getParameterString(rs, "beneficiario"));
        prestiti.setOggetti_id(getParameterInteger(rs, "oggetto_prestato"));
        prestiti.setDataPrestito(getParameterDate(rs, "data_prestito"));
        prestiti.setScadenzaPrestito(getParameterDate(rs, "data_scadenza_prestito"));
        prestiti.setDataReso(getParameterDate(rs, "data_reso"));
        return prestiti;
    }

    /**
     * Delete a record of Prestiti on table prestiti
     * 
     * @param soggetti_user_name - user_name - Nome utente
     * @param oggetti_id - id - id oggetto
     *
     * @throws DAODeleteException if no record deleted
     */
    public void delete(String soggetti_user_name, Integer oggetti_id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE BENEFICIARIO = ? AND OGGETTO_PRESTATO = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggetti_user_name);
            super.setParameterInteger(statement, p++, oggetti_id);

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfDeletedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfDeletedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("No record of Prestiti deleted with params [soggetti_user_name = ", soggetti_user_name, " oggetti_id = ",
                        oggetti_id, "]");
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils.concat("Error during delete records on Prestiti with params [soggetti_user_name = ", soggetti_user_name, " oggetti_id = ",
                    oggetti_id, "]");
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

    /**
     * Get list of Prestiti by Soggetti
     * 
     * @param Soggetti
     * @return a Prestiti list
     */
    public List<Prestiti> getPrestitiListBySoggetti(String soggetti_user_name) {
        List<Prestiti> list = new ArrayList<Prestiti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE beneficiario = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggetti_user_name);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            while (rs.next()) {
                Prestiti prestiti = buildModelFromResultSet(rs);
                list.add(prestiti);
            }
            return list;
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on find Prestiti with soggetti_user_name = ", soggetti_user_name, "on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Delete Prestiti list by Soggetti
     * 
     * @param soggetti_user_name - user_name - Nome utente
     * @throws DAODeleteException if no record deleted
     */
    public void deletePrestitiListBySoggetti(String soggetti_user_name) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE beneficiario = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggetti_user_name);

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfDeletedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfDeletedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("An error occurred delete Prestiti with soggetti_user_name = ", soggetti_user_name,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on delete Prestiti with soggetti_user_name = ", soggetti_user_name, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Get list of Prestiti by Oggetti
     * 
     * @param Oggetti
     * @return a Prestiti list
     */
    public List<Prestiti> getPrestitiListByOggetti(Integer oggetti_id) {
        List<Prestiti> list = new ArrayList<Prestiti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE oggetto_prestato = ?  ").append(EOL);
            query.append("order by data_prestito DESC").append(EOL);
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
            super.setParameterInteger(statement, p++, oggetti_id);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            while (rs.next()) {
                Prestiti prestiti = buildModelFromResultSet(rs);
                list.add(prestiti);
            }
            return list;
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on find Prestiti with oggetti_id = ", oggetti_id, "on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Delete Prestiti list by Oggetti
     * 
     * @param oggetti_id - id - id oggetto
     * @throws DAODeleteException if no record deleted
     */
    public void deletePrestitiListByOggetti(Integer oggetti_id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE oggetto_prestato = ?  ").append(EOL);
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
            super.setParameterInteger(statement, p++, oggetti_id);

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfDeletedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfDeletedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("An error occurred delete Prestiti with oggetti_id = ", oggetti_id,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on delete Prestiti with oggetti_id = ", oggetti_id, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Execute the count query on prestiti filtered for searchModel PrestitiSearch
     * 
     * @param PrestitiSearch search model
     * @return nRow the number of records found
     */
    public Long count(PrestitiSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM prestiti ").append(EOL);
            query.append(" WHERE 1 = 1 ");
            Map<Integer, Object> parameters = new HashMap<Integer, Object>();
            query.append(getWhereQuery(parameters, search));
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            setParameters(statement, parameters);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            long nRow = 0;
            if (rs.next()) {
                nRow = rs.getLong("TOT");
            }
            return nRow;
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error(ex.getMessage(), ex);
            }
            throw new SystemException(ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retur a list of Prestiti filtered for searchModel PrestitiSearch
     * 
     * @param PrestitiSearch search model
     * @return a list of Prestiti
     */
    public List<Prestiti> getList(PrestitiSearch search) {
        List<Prestiti> list = new ArrayList<Prestiti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT * ").append(EOL);
            query.append(" FROM prestiti ").append(EOL);
            query.append(" WHERE 1= 1 ").append(EOL);
            Map<Integer, Object> parameters = new HashMap<Integer, Object>();
            query.append(getWhereQuery(parameters, search));
            addOrderClause(search, query);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Set the parameters
            setParameters(statement, parameters);
            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            int count = 0;
            skipFirstRows(rs, search);
            while (rs.next()) {
                Prestiti prestiti = buildModelFromResultSet(rs);
                list.add(prestiti);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;
        } catch (SQLException ex) {
            String msg = "Unexpeted error on find Prestiti  on database.";
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Return the StringBuilder that compose where clause on query
     * 
     * @param parameters
     * @param PrestitiSearch the search model
     * @return the StringBuilder that compose where clause on query
     */
    protected StringBuilder getWhereQuery(Map<Integer, Object> parameters, PrestitiSearch search) {
        StringBuilder whereClause = new StringBuilder("");
        int p = 1;

        if (search.getData_prestitoFrom() != null) {
            whereClause.append("AND data_prestito >= ? ");
            parameters.put(new Integer(p), search.getData_prestitoFrom());
            p++;
        }
        if (search.getData_prestitoTo() != null) {
            whereClause.append("AND data_prestito <= ? ");
            parameters.put(new Integer(p), search.getData_prestitoTo());
            p++;
        }
        if (search.getData_scadenza_prestitoFrom() != null) {
            whereClause.append("AND data_scadenza_prestito >= ? ");
            parameters.put(new Integer(p), search.getData_scadenza_prestitoFrom());
            p++;
        }
        if (search.getData_scadenza_prestitoTo() != null) {
            whereClause.append("AND data_scadenza_prestito <= ? ");
            parameters.put(new Integer(p), search.getData_scadenza_prestitoTo());
            p++;
        }
        if (search.getSoggetti() != null) {
            whereClause.append("AND beneficiario = ?  ");
            parameters.put(new Integer(p), search.getSoggetti().getUsername());
            p++;
        } else {
            if (search.getSoggetti_user_name() != null) {
                whereClause.append("AND beneficiario = ? ");
                parameters.put(new Integer(p), search.getSoggetti_user_name());
                p++;
            }

        }
        if (search.getOggetti() != null) {
            whereClause.append("AND oggetto_prestato = ?  ");
            parameters.put(new Integer(p), search.getOggetti().getId());
            p++;
        } else {
            if (search.getOggetti_id() != null) {
                whereClause.append("AND oggetto_prestato = ? ");
                parameters.put(new Integer(p), search.getOggetti_id());
                p++;
            }

        }
        return whereClause;
    }

    /**
     * Return the StringBuilder that compose orderBy clause on query
     * 
     * @param PrestitiSearch the search model
     * @param the query to order
     */
    protected void addOrderClause(PrestitiSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY beneficiario ASC ");
                break;
            case 2:
                query.append(" ORDER BY beneficiario DESC ");
                break;
            case 3:
                query.append(" ORDER BY data_prestito ASC ");
                break;
            case 4:
                query.append(" ORDER BY data_prestito DESC ");
                break;
            case 5:
                query.append(" ORDER BY data_scadenza_prestito ASC ");
                break;
            case 6:
                query.append(" ORDER BY data_scadenza_prestito DESC ");
                break;

            default:
                query.append(" ORDER BY data_prestito ASC, data_scadenza_prestito ASC");
                break;
        }
    }

    protected void skipFirstRows(ResultSet rs, PrestitiSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, PrestitiSearch search) {
        return !(search.getPage() < 0 || count < search.getRowPerPage());
    }

    public void setQborrrowManager(QborrrowManager qborrrowManager) {
        this.qborrrowManager = qborrrowManager;
    }

    public QborrrowManager getQborrrowManager() {
        return qborrrowManager;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}