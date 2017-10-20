package it.quix.academy.qborrrow.core.dao;

import it.quix.academy.qborrrow.core.dao.generated.OggettiAbstractDAO;
import it.quix.academy.qborrrow.core.model.Oggetti;
import it.quix.academy.qborrrow.core.model.Prestiti;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.search.OggettiSearch;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The DAO for Oggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 12/10/2017 12:46:09
 */
public class OggettiDAO extends OggettiAbstractDAO {

    private static Log log = LogFactory.getLog(OggettiDAO.class);

    public OggettiDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("OggettiDAO initialized!");
        }
    }

    /**
     * lista dei miei oggetti con info sui prestiti
     * 
     * @param search
     * @return
     */
    public List<Oggetti> getMieiOggettiList(OggettiSearch search) {
        List<Oggetti> list = new ArrayList<Oggetti>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query

            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT oggetti.*, prestiti.beneficiario, prestiti.data_scadenza_prestito, soggetti.nome, soggetti.cognome ").append(EOL);
            query.append(" FROM oggetti").append(EOL);
            query.append(" LEFT JOIN prestiti ON oggetti.id = prestiti.oggetto_prestato ").append(EOL);
            query.append(" LEFT JOIN soggetti ON prestiti.beneficiario = soggetti.user_name ").append(EOL);
            query.append(" WHERE oggetti.proprietario ='oksana' ").append(EOL);
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
                if (getParameterString(rs, "beneficiario") == null) {

                    oggetti.setOggettoPrestato(true);

                    Prestiti prestito = new Prestiti();
                    prestito.setDataPrestito(getParameterDate(rs, "data_scadenza_prestito"));
                    Soggetti soggetto = new Soggetti();
                    // soggetto.setUser_name(getParameterString(rs, "user_name"));
                    soggetto.setCognome(getParameterString(rs, "nome"));
                    soggetto.setCognome(getParameterString(rs, "cognome"));

                    prestito.setSoggetti(soggetto);
                    oggetti.setPrestito(prestito);
                }

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

}