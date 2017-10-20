package it.quix.academy.qborrrow.core.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import flexjson.JSON;
import it.quix.academy.qborrrow.Configuration;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.framework.core.composer.annotation.QrExcelColumn;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.manager.UserContextHolder;
import it.quix.framework.core.model.AttributeView;
import it.quix.framework.core.model.UserContext;

/**
 * The Oggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 14:50:04
 */
@Entity
@Table(name = "oggetti")
public class Oggetti extends QborrrowAbstractModel implements Serializable {

    /**
     * The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses
     * this number to ensure that a loaded class corresponds exactly to a serialized object.
     * If no match is found, then an InvalidClassException is thrown.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger object
     */
    private static Log log = LogFactory.getLog(Oggetti.class);

    /**
     * Id <br>
     * Codice identificativo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * This field is part of the primary key of this entity.
     */
    @Id
    @Column(nullable = false)
    @QrExcelColumn(order = 0)
    private Integer id;

    /**
     * Titolo <br>
     * Titolo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     */
    @Column()
    @QrExcelColumn(order = 0)
    private String titolo;

    /**
     * Descrizione <br>
     * Descrzione dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 50)
    @QrExcelColumn(order = 0)
    private String descrizione;

    /**
     * Immagine <br>
     * Percorso dell'immagine dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     */
    @Column()
    @Lob
    @QrExcelColumn(order = 0)
    private String immagine;

    /**
     * Categoria <br>
     * Categoria a cui appartiene l'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     */
    @Column()
    @QrExcelColumn(order = 0, translate = true)
    private String categoria;

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 50, name = "data_ultima_modifica", nullable = false)
    @QrExcelColumn(order = 0)
    private Date dataUltimaModifica;

    /**
     * Propretario <br>
     * Username del propretario dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>columnName = propretario
     * <li>nullable = true
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propretario")
    private Soggetti soggetti;

    @Transient
    private boolean soggettiJdbcAlreadyChecked = false;

    @Transient
    private String soggettiUsername;

    /**
     * Indica se l'oggetto � prestato
     */
    private boolean isOggettoPrestato = false;

    /**
     * Se l'oggetto � in prestito, vedremo i dati del prestito.
     */
    private Prestiti prestito;

    /**
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relation on non-null object references:
     * <ul>
     * <li>It is reflexive: for any non-null reference value x, x.equals(x) should return true.</li>
     * <li>It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.</li>
     * <li>It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should
     * return true.</li>
     * <li>It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return
     * false, provided no information used in equals comparisons on the objects is modified.</li>
     * <li>For any non-null reference value x, x.equals(null) should return false.</li>
     * </ul>
     * The equals method for class Object implements the most discriminating possible equivalence relation on objects; that is, for
     * any non-null reference values x and y, this method returns true if and only if x and y refer to the same object (x == y has the value true).
     * Note that it is generally necessary to override the hashCode method whenever this method is overridden, so as to maintain the general contract
     * for the hashCode method, which states that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return rue if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Oggetti other = (Oggetti) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hashtables such as those provided by java.util.Hashtable.
     * The general contract of hashCode is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently return the
     * same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of
     * an application to another execution of the same application.</li>
     * <li>If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same
     * integer result.</li>
     * <li>It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling the hashCode method on each of the
     * two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects
     * may improve the performance of hashtables.</li>
     * </ul>
     * 
     * @return a hash code value for this object
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation
     * that is easy for a person to read.
     * 
     * @return a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append("(");

        sb.append(", ").append("id=").append(id);

        sb.append(", ").append("titolo=").append(titolo);

        sb.append(", ").append("descrizione=").append(descrizione);

        sb.append(", ").append("immagine=").append(immagine);

        sb.append(", ").append("categoria=").append(categoria);

        sb.append(", ").append("dataUltimaModifica=").append(dataUltimaModifica);

        sb.append(", ").append("soggetti=").append(soggetti);

        sb.append(")");
        return sb.toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PrePersist
    public void prePersist() {
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void prePersist(Configuration configuration) {
        prePersist();
    }

    /**
     * Executed before the update operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PreUpdate
    public void preUpdate() {

    }

    /**
     * Executed before the update operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void preUpdate(Configuration configuration) {
        preUpdate();
    }

    /**
     * Executed before the delete operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PreRemove
    public void preRemove() {

    }

    /**
     * Executed before the delete operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void preRemove(Configuration configuration) {
        preRemove();

    }

    /**
     * Return the Id
     * Codice identificativo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @return the id
     * @see Oggetti.id
     */

    public Integer getId() {
        return id;
    }

    /**
     * Set the Id
     * Codice identificativo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @param id the Id to set
     * @see Oggetti.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return the Titolo
     * Titolo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the titolo
     * @see Oggetti.titolo
     */

    public String getTitolo() {
        return titolo;
    }

    /**
     * Set the Titolo
     * Titolo dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param titolo the Titolo to set
     * @see Oggetti.titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Return the Descrizione
     * Descrzione dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @return the descrizione
     * @see Oggetti.descrizione
     */

    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Set the Descrizione
     * Descrzione dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @param descrizione the Descrizione to set
     * @see Oggetti.descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Return the Immagine
     * Percorso dell'immagine dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the immagine
     * @see Oggetti.immagine
     */

    public String getImmagine() {
        return immagine;
    }

    /**
     * Set the Immagine
     * Percorso dell'immagine dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param immagine the Immagine to set
     * @see Oggetti.immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * Return the Categoria
     * Categoria a cui appartiene l'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the categoria
     * @see Oggetti.categoria
     */

    public String getCategoria() {
        return categoria;
    }

    /**
     * Return the description of SysAttribute qbo0001_categoria
     */
    public String getCategoriaDescription() {
        if (getCategoria() != null) {
            QborrrowManager manager = getQborrrowManager();
            UserContext uc = UserContextHolder.getUserContext();
            SysAttributeHandler sysAttributeHandler = getSysAttributeHandler();
            try {
                AttributeView attribute =
                    sysAttributeHandler.getAttribute("qbo0001_categoria", uc.getLanguageForSysAttribute(), uc.getOrganizationForSysSysAttribute(),
                        getCategoria(), uc);
                String description = attribute.getDescription();
                return description;
            } catch (Exception e) {
                log.error("Error on find sysAttribute qbo0001_categoria, value " + getCategoria() + " not found!", e);
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Set the Categoria
     * Categoria a cui appartiene l'oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param categoria the Categoria to set
     * @see Oggetti.categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     * 
     * @return the dataUltimaModifica
     * @see Oggetti.dataUltimaModifica
     */

    public Date getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     * 
     * @param dataUltimaModifica
     * @see Oggetti.dataUltimaModifica
     */
    public void setDataUltimaModifica(Date dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }

    /**
     * Return the Propretario
     * Username del propretario dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>columnName = propretario
     * <li>nullable = true
     * </ul>
     * 
     * @return the soggetti
     * @see Oggetti.soggetti
     */

    public Soggetti getSoggetti() {
        if (jdbc && soggetti == null && soggettiUsername != null && !soggettiJdbcAlreadyChecked) {
            try {
                soggetti = getQborrrowManager().getSoggetti(soggettiUsername);
                soggettiJdbcAlreadyChecked = true;
            } catch (DAOFinderException e) {
                log.debug("Unexpected DAOFinderException on getSoggetti by soggetti_username = " + soggettiUsername, e);
            }
        }
        return soggetti;
    }

    /**
     * Set the Propretario
     * Username del propretario dell'oggetto <br>
     * Property of field:
     * <ul>
     * <li>columnName = propretario
     * <li>nullable = true
     * </ul>
     * 
     * @param soggetti the Propretario to set
     * @see Oggetti.soggetti
     */
    public void setSoggetti(Soggetti soggetti) {
        this.soggetti = soggetti;
        if (soggetti != null) {
            soggettiUsername = soggetti.getUsername();
        } else {
            soggettiUsername = null;
        }
    }

    public String getSoggettiUsername() {
        if (jdbc) {
            return soggettiUsername;
        } else {
            return soggetti == null ? null : soggetti.getUsername();
        }
    }

    public void setSoggettiUsername(String soggetti_username) {
        if (jdbc) {
            if (this.soggettiUsername != null && !this.soggettiUsername.equals(soggetti_username)) {
                soggetti = null;
                soggettiJdbcAlreadyChecked = false;
            }
            this.soggettiUsername = soggetti_username;
        } else {
            throw new ModelJdbcException("The method setSoggetti_username can be invoked only on jdbc model.");
        }
    }

    @JSON(include = false)
    public boolean isSoggettiJdbcAlreadyChecked() {
        return soggettiJdbcAlreadyChecked;
    }

    public void setSoggettiJdbcAlreadyChecked(boolean soggettiJdbcAlreadyChecked) {
        this.soggettiJdbcAlreadyChecked = soggettiJdbcAlreadyChecked;
    }

    public boolean isOggettoPrestato() {
        return isOggettoPrestato;
    }

    public void setOggettoPrestato(boolean isOggettoPrestato) {
        this.isOggettoPrestato = isOggettoPrestato;
    }

    public Prestiti getPrestito() {
        return prestito;
    }

    public void setPrestito(Prestiti prestito) {
        this.prestito = prestito;
    }

}