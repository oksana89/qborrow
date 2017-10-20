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
import it.quix.academy.qborrrow.core.model.Oggetti;
import it.quix.academy.qborrrow.core.search.OggettiSearch;
import it.quix.framework.core.dao.AbstractJDBCDAO;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The Abstract DAO for Oggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 12/10/2017 12:46:09
 */
public abstract class OggettiAbstractDAO extends AbstractJDBCDAO {

    private static final Log log = LogFactory.getLog(OggettiAbstractDAO.class);

    @Resource(name = "qborrrowManager")
    protected QborrrowManager qborrrowManager;

    @Resource(name = "configuration")
    protected Configuration configuration;

    public OggettiAbstractDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Create a new record of type Oggetti on table oggetti
     * 
     * @param oggetti The Oggetti to create on database
     * @throws DAOCreateException if no record is created on database
     */
    public void create(Oggetti oggetti) throws DAOCreateException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the insert query
            StringBuilder query = new StringBuilder(EOL);
            query.append("INSERT INTO oggetti ").append(EOL);
            query.append("   (ID, TITOLO, DESCRIZIONE, IMMAGINE, CATEGORIA, DATA_ULTIMA_MODIFICA, PROPRIETARIO) ").append(EOL);
            query.append(" VALUES ").append(EOL);
            query.append(" (?, ?, ?, ?, ?, ?, ?) ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // set prePersist
            oggetti.prePersist(configuration);
            // Set the parameters
            int p = 1;
            super.setParameterInteger(statement, p++, oggetti.getId());
            super.setParameterString(statement, p++, oggetti.getTitolo());
            super.setParameterString(statement, p++, oggetti.getDescrizione());
            super.setParameterString(statement, p++, oggetti.getImmagine());
            super.setParameterString(statement, p++, oggetti.getCategoria());
            super.setParameterDate(statement, p++, oggetti.getDataUltimaModifica());
            super.setParameterString(statement, p++, oggetti.getSoggettiUsername());

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
                    FrameworkStringUtils.concat("An error occurred creating Oggetti on database. No record created. Number of inserted rows: ",
                        numberOfCreatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOCreateException(msg);
            }
        } catch (SQLException ex) {
            String msg = "Unexpeted error on create Oggetti on database.";
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
     * Updates a record of type Oggetti on table oggetti
     * 
     * @param oggetti The Oggetti to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void update(Oggetti oggetti) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE oggetti SET ").append(EOL);
            query.append(" titolo = ? , descrizione = ? , immagine = ? , categoria = ? , data_ultima_modifica = ? , proprietario = ?  ").append(EOL);
            query.append("  WHERE id = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            oggetti.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, oggetti.getTitolo());
            super.setParameterString(statement, p++, oggetti.getDescrizione());
            super.setParameterString(statement, p++, oggetti.getImmagine());
            super.setParameterString(statement, p++, oggetti.getCategoria());
            super.setParameterDate(statement, p++, oggetti.getDataUltimaModifica());
            super.setParameterString(statement, p++, oggetti.getSoggettiUsername());

            // Set the primary key
            super.setParameterInteger(statement, p++, oggetti.getId());

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
                    FrameworkStringUtils.concat("Error while updating the record of type Oggetti ", oggetti, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Oggetti ", oggetti, " on database.");
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
     * Return true if oldOggetti and newOggetti are different
     * 
     * @param oldOggetti first model to compare
     * @param newOggetti second model to compare
     * @return if the first model is different to the second model
     */
    protected boolean isDirty(Oggetti oldOggetti, Oggetti newOggetti) {
        if (oldOggetti.getId() != null && !oldOggetti.getId().equals(newOggetti.getId()))
            return true;
        if (oldOggetti.getId() == null && newOggetti.getId() != null)
            return true;
        if (oldOggetti.getTitolo() != null && !oldOggetti.getTitolo().equals(newOggetti.getTitolo()))
            return true;
        if (oldOggetti.getTitolo() == null && newOggetti.getTitolo() != null)
            return true;
        if (oldOggetti.getDescrizione() != null && !oldOggetti.getDescrizione().equals(newOggetti.getDescrizione()))
            return true;
        if (oldOggetti.getDescrizione() == null && newOggetti.getDescrizione() != null)
            return true;
        if (oldOggetti.getImmagine() != null && !oldOggetti.getImmagine().equals(newOggetti.getImmagine()))
            return true;
        if (oldOggetti.getImmagine() == null && newOggetti.getImmagine() != null)
            return true;
        if (oldOggetti.getCategoria() != null && !oldOggetti.getCategoria().equals(newOggetti.getCategoria()))
            return true;
        if (oldOggetti.getCategoria() == null && newOggetti.getCategoria() != null)
            return true;
        if (oldOggetti.getDataUltimaModifica() != null && !oldOggetti.getDataUltimaModifica().equals(newOggetti.getDataUltimaModifica()))
            return true;
        if (oldOggetti.getDataUltimaModifica() == null && newOggetti.getDataUltimaModifica() != null)
            return true;
        if (oldOggetti.getSoggetti() != null && !oldOggetti.getSoggetti().equals(newOggetti.getSoggetti()))
            return true;
        if (oldOggetti.getSoggetti() == null && newOggetti.getSoggetti() != null)
            return true;

        return false;
    }

    /**
     * Update on database the newOggetti only if is different to the oldOggetti
     * 
     * @param oldOggetti the old model, used to find difference to the newOggetti
     * @param newOggetti the model to store to database if it is different to oldOggetti
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateIfDirty(Oggetti oldOggetti, Oggetti newOggetti) throws DAOStoreException {
        if (isDirty(oldOggetti, newOggetti)) {
            update(newOggetti);
        }
    }

    /**
     * Clone the model pass as parameter into a new model. This method don't copy the toMany fields (List, Set, ...)
     * 
     * This method is static to be also called outside of the Manager
     * 
     * @param oggetti the model to be cloned
     * @return the clone of the model pass as parameter
     */
    public static Oggetti cloneWithoutChildren(Oggetti oggetti) {
        if (!oggetti.isJdbc()) {
            throw new ModelJdbcException(
                "The method OggettiAbstractDAO.cloneWithoutChildren(Oggetti oggetti) can be executed only on jdbc model. The Oggetti pass as parameter isn't jdbc model.");
        }

        Oggetti newOggetti = new Oggetti();
        newOggetti.setJdbc(true);

        newOggetti.setQborrrowManager(oggetti.getQborrrowManager());
        newOggetti.setId(oggetti.getId());
        newOggetti.setTitolo(oggetti.getTitolo());
        newOggetti.setDescrizione(oggetti.getDescrizione());
        newOggetti.setImmagine(oggetti.getImmagine());
        newOggetti.setCategoria(oggetti.getCategoria());
        newOggetti.setDataUltimaModifica(oggetti.getDataUltimaModifica());
        newOggetti.setSoggetti(oggetti.getSoggetti());

        return newOggetti;
    }

    /**
     * Return a record of Oggetti on table oggetti
     * 
     * @param id - id - id oggetto
     * @return the object Oggetti
     * @throws DAOFinderException if no record found with passed params
     */
    public Oggetti get(Integer id) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM oggetti ").append(EOL);
            query.append("WHERE ID = ?  ").append(EOL);
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
            super.setParameterInteger(statement, p++, id);

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
                Oggetti oggettiModel = buildModelFromResultSet(rs, qborrrowManager);
                return oggettiModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Oggetti on database with [id = ", id, "]"));

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method get(Integer id) for Oggetti on database with [id = ", id, "]");
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
     * Fill all object (Oggetti) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @return the Oggetti object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    protected Oggetti oggetti(ResultSet rs) throws SQLException {
        return buildModelFromResultSet(rs, qborrrowManager);
    }

    /**
     * Fill all object (Oggetti) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrowManager the manager to set
     * @return the Oggetti object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */

    public Oggetti buildModelFromResultSet(ResultSet rs, QborrrowManager qborrrowManager) throws SQLException {
        Oggetti oggetti = new Oggetti();

        oggetti.setJdbc(true);
        oggetti.setQborrrowManager(qborrrowManager);

        oggetti.setSoggettiUsername(getParameterString(rs, "proprietario"));
        oggetti.setId(getParameterInteger(rs, "id"));
        oggetti.setTitolo(getParameterString(rs, "titolo"));
        oggetti.setDescrizione(getParameterString(rs, "descrizione"));
        oggetti.setImmagine(getParameterString(rs, "immagine"));
        oggetti.setCategoria(getParameterString(rs, "categoria"));
        oggetti.setDataUltimaModifica(getParameterDate(rs, "data_ultima_modifica"));

        return oggetti;
    }

    /**
     * Fill all object (Oggetti) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrrowManager the manager to set
     * @return the Oggetti object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Oggetti oggetti(ResultSet rs, QborrrowManager qborrrowManager) throws SQLException {

        Oggetti oggetti = new Oggetti();

        oggetti.setJdbc(true);
        oggetti.setQborrrowManager(qborrrowManager);

        oggetti.setSoggettiUsername(getParameterString(rs, "proprietario"));
        oggetti.setId(getParameterInteger(rs, "id"));
        oggetti.setTitolo(getParameterString(rs, "titolo"));
        oggetti.setDescrizione(getParameterString(rs, "descrizione"));
        oggetti.setImmagine(getParameterString(rs, "immagine"));
        oggetti.setCategoria(getParameterString(rs, "categoria"));
        oggetti.setDataUltimaModifica(getParameterDate(rs, "data_ultima_modifica"));

        return oggetti;
    }

    /**
     * Delete a record of Oggetti on table oggetti
     * 
     * @param id - id - id oggetto
     *
     * @throws DAODeleteException if no record deleted
     */
    public void delete(Integer id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM oggetti ").append(EOL);
            query.append("WHERE ID = ?  ").append(EOL);
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
            super.setParameterInteger(statement, p++, id);

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
                String msg = FrameworkStringUtils.concat("No record of Oggetti deleted with params [id = ", id, "]");
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error during delete records on Oggetti with params [id = ", id, "]");
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
     * Get list of Oggetti by Soggetti
     * 
     * @param Soggetti
     * @return a Oggetti list
     */
    public List<Oggetti> getOggettiListBySoggetti(String soggetti_user_name) {
        List<Oggetti> list = new ArrayList<Oggetti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM oggetti ").append(EOL);
            query.append("WHERE proprietario = ?  ").append(EOL);
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
                Oggetti oggetti = buildModelFromResultSet(rs, qborrrowManager);
                list.add(oggetti);
            }
            return list;
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on find Oggetti with soggetti_user_name = ", soggetti_user_name, "on database.");
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
     * Delete Oggetti list by Soggetti
     * 
     * @param soggetti_user_name - user_name - Nome utente
     * @throws DAODeleteException if no record deleted
     */
    public void deleteOggettiListBySoggetti(String soggetti_user_name) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM oggetti ").append(EOL);
            query.append("WHERE proprietario = ?  ").append(EOL);
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
                    FrameworkStringUtils.concat("An error occurred delete Oggetti with soggetti_user_name = ", soggetti_user_name,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on delete Oggetti with soggetti_user_name = ", soggetti_user_name, " on database.");
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
     * Execute the count query on oggetti filtered for searchModel OggettiSearch
     * 
     * @param OggettiSearch search model
     * @return nRow the number of records found
     */
    public Long count(OggettiSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM oggetti ").append(EOL);
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
     * Retur a list of Oggetti filtered for searchModel OggettiSearch
     * 
     * @param OggettiSearch search model
     * @return a list of Oggetti
     */
    public List<Oggetti> getList(OggettiSearch search) {
        List<Oggetti> list = new ArrayList<Oggetti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT * ").append(EOL);
            query.append(" FROM oggetti ").append(EOL);
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
                Oggetti oggetti = buildModelFromResultSet(rs, qborrrowManager);
                list.add(oggetti);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;
        } catch (SQLException ex) {
            String msg = "Unexpeted error on find Oggetti  on database.";
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
     * @param OggettiSearch the search model
     * @return the StringBuilder that compose where clause on query
     */
    protected StringBuilder getWhereQuery(Map<Integer, Object> parameters, OggettiSearch search) {
        StringBuilder whereClause = new StringBuilder("");
        int p = 1;
        if (search.getId() != null) {
            whereClause.append("AND id = ? ");
            parameters.put(new Integer(p), search.getId());
            p++;
        } else {

        }

        if (StringUtils.isNotEmpty(search.getTitolo())) {
            whereClause.append("AND titolo  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getTitolo() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getDescrizione())) {
            whereClause.append("AND descrizione  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getDescrizione() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getImmagine())) {
            whereClause.append("AND immagine  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getImmagine() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getCategoria())) {
            whereClause.append("AND categoria  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getCategoria() + "%");
            p++;
        }
        if (search.getData_ultima_modificaFrom() != null) {
            whereClause.append("AND data_ultima_modifica >= ? ");
            parameters.put(new Integer(p), search.getData_ultima_modificaFrom());
            p++;
        }
        if (search.getData_ultima_modificaTo() != null) {
            whereClause.append("AND data_ultima_modifica <= ? ");
            parameters.put(new Integer(p), search.getData_ultima_modificaTo());
            p++;
        }
        if (search.getSoggetti() != null) {
            whereClause.append("AND proprietario = ?  ");
            parameters.put(new Integer(p), search.getSoggetti().getUsername());
            p++;
        } else {
            if (search.getSoggetti_user_name() != null) {
                whereClause.append("AND proprietario = ? ");
                parameters.put(new Integer(p), search.getSoggetti_user_name());
                p++;
            }

        }
        return whereClause;
    }

    /**
     * Return the StringBuilder that compose orderBy clause on query
     * 
     * @param OggettiSearch the search model
     * @param the query to order
     */
    protected void addOrderClause(OggettiSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY id ASC ");
                break;
            case 2:
                query.append(" ORDER BY id DESC ");
                break;
            case 3:
                query.append(" ORDER BY proprietario ASC ");
                break;
            case 4:
                query.append(" ORDER BY proprietario DESC ");
                break;
            case 5:
                query.append(" ORDER BY titolo ASC ");
                break;
            case 6:
                query.append(" ORDER BY titolo DESC ");
                break;
            case 7:
                query.append(" ORDER BY data_ultima_modifica ASC ");
                break;
            case 8:
                query.append(" ORDER BY data_ultima_modifica DESC ");
                break;

            default:
                query.append(" ORDER BY id ASC, proprietario ASC, titolo ASC, data_ultima_modifica ASC");
                break;
        }
    }

    protected void skipFirstRows(ResultSet rs, OggettiSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, OggettiSearch search) {
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