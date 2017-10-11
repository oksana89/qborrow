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
import it.quix.framework.core.converter.annotation.QcDateType;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.manager.UserContextHolder;
import it.quix.framework.core.model.AttributeView;
import it.quix.framework.core.model.UserContext;

/**
 * The Oggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 14:58:54
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
     * id <br>
     * id oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * This field is part of the primary key of this entity.
     */
    @Id
    @Column(length = 50, nullable = false)
    @QrExcelColumn(order = 0)
    private Integer id;

    /**
     * proprietario <br>
     * proprietario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     */
    @Column(length = 50, nullable = false)
    @QrExcelColumn(order = 0)
    private String proprietario;

    /**
     * titolo <br>
     * titolo <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 225)
    @QrExcelColumn(order = 0)
    private String titolo;

    /**
     * descrizione <br>
     * descrizione <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 225)
    @QrExcelColumn(order = 0)
    private String descrizione;

    /**
     * immagine <br>
     * immagine <br>
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
     * categoria <br>
     * categoria <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 225)
    @QrExcelColumn(order = 0, translate = true)
    private String categoria;

    /**
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>nullable = false
     * </ul>
     */
    @Column(length = 9, nullable = false)
    @QcDateType()
    @Temporal(TemporalType.DATE)
    @QrExcelColumn(order = 0)
    private Date data_ultima_modifica;

    /**
     * Property of field:
     * <ul>
     * <li>columnName = user_name
     * <li>nullable = true
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name")
    private Soggetti soggetti;

    @Transient
    private boolean soggettiJdbcAlreadyChecked = false;

    @Transient
    private String soggetti_user_name;

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

        sb.append(", ").append("proprietario=").append(proprietario);

        sb.append(", ").append("titolo=").append(titolo);

        sb.append(", ").append("descrizione=").append(descrizione);

        sb.append(", ").append("immagine=").append(immagine);

        sb.append(", ").append("categoria=").append(categoria);

        sb.append(", ").append("data_ultima_modifica=").append(data_ultima_modifica);

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
     * Return the id
     * id oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
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
     * Set the id
     * id oggetto <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @param id the id to set
     * @see Oggetti.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return the proprietario
     * proprietario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @return the proprietario
     * @see Oggetti.proprietario
     */

    public String getProprietario() {
        return proprietario;
    }

    /**
     * Set the proprietario
     * proprietario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @param proprietario the proprietario to set
     * @see Oggetti.proprietario
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Return the titolo
     * titolo <br>
     * Property of field:
     * <ul>
     * <li>length = 225
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
     * Set the titolo
     * titolo <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     * 
     * @param titolo the titolo to set
     * @see Oggetti.titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Return the descrizione
     * descrizione <br>
     * Property of field:
     * <ul>
     * <li>length = 225
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
     * Set the descrizione
     * descrizione <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     * 
     * @param descrizione the descrizione to set
     * @see Oggetti.descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Return the immagine
     * immagine <br>
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
     * Set the immagine
     * immagine <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param immagine the immagine to set
     * @see Oggetti.immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * Return the categoria
     * categoria <br>
     * Property of field:
     * <ul>
     * <li>length = 225
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
     * Return the description of SysAttribute QBO000_categoria
     */
    public String getCategoriaDescription() {
        if (getCategoria() != null) {
            QborrrowManager manager = getQborrrowManager();
            UserContext uc = UserContextHolder.getUserContext();
            SysAttributeHandler sysAttributeHandler = getSysAttributeHandler();
            try {
                AttributeView attribute =
                    sysAttributeHandler.getAttribute("QBO000_categoria", uc.getLanguageForSysAttribute(), uc.getOrganizationForSysSysAttribute(),
                        getCategoria(), uc);
                String description = attribute.getDescription();
                return description;
            } catch (Exception e) {
                log.error("Error on find sysAttribute QBO000_categoria, value " + getCategoria() + " not found!", e);
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Set the categoria
     * categoria <br>
     * Property of field:
     * <ul>
     * <li>length = 225
     * <li>nullable = true
     * </ul>
     * 
     * @param categoria the categoria to set
     * @see Oggetti.categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>nullable = false
     * </ul>
     * 
     * @return the data_ultima_modifica
     * @see Oggetti.data_ultima_modifica
     */

    public Date getData_ultima_modifica() {
        return data_ultima_modifica;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>nullable = false
     * </ul>
     * 
     * @param data_ultima_modifica
     * @see Oggetti.data_ultima_modifica
     */
    public void setData_ultima_modifica(Date data_ultima_modifica) {
        this.data_ultima_modifica = data_ultima_modifica;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>columnName = user_name
     * <li>nullable = true
     * </ul>
     * 
     * @return the soggetti
     * @see Oggetti.soggetti
     */

    public Soggetti getSoggetti() {
        if (jdbc && soggetti == null && soggetti_user_name != null && !soggettiJdbcAlreadyChecked) {
            try {
                soggetti = getQborrrowManager().getSoggetti(soggetti_user_name);
                soggettiJdbcAlreadyChecked = true;
            } catch (DAOFinderException e) {
                log.debug("Unexpected DAOFinderException on getSoggetti by soggetti_user_name = " + soggetti_user_name, e);
            }
        }
        return soggetti;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>columnName = user_name
     * <li>nullable = true
     * </ul>
     * 
     * @param soggetti
     * @see Oggetti.soggetti
     */
    public void setSoggetti(Soggetti soggetti) {
        this.soggetti = soggetti;
        if (soggetti != null) {
            soggetti_user_name = soggetti.getUser_name();
        } else {
            soggetti_user_name = null;
        }
    }

    public String getSoggetti_user_name() {
        if (jdbc) {
            return soggetti_user_name;
        } else {
            return soggetti == null ? null : soggetti.getUser_name();
        }
    }

    public void setSoggetti_user_name(String soggetti_user_name) {
        if (jdbc) {
            if (this.soggetti_user_name != null && !this.soggetti_user_name.equals(soggetti_user_name)) {
                soggetti = null;
                soggettiJdbcAlreadyChecked = false;
            }
            this.soggetti_user_name = soggetti_user_name;
        } else {
            throw new ModelJdbcException("The method setSoggetti_user_name can be invoked only on jdbc model.");
        }
    }

    @JSON(include = false)
    public boolean isSoggettiJdbcAlreadyChecked() {
        return soggettiJdbcAlreadyChecked;
    }

    public void setSoggettiJdbcAlreadyChecked(boolean soggettiJdbcAlreadyChecked) {
        this.soggettiJdbcAlreadyChecked = soggettiJdbcAlreadyChecked;
    }

}