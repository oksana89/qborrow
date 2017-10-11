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
import it.quix.framework.core.validation.annotation.QvPattern;

/**
 * The Prestiti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 14:58:54
 */
@Entity
@Table(name = "prestiti")
public class Prestiti extends QborrrowAbstractModel implements Serializable {

    /**
     * The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses
     * this number to ensure that a loaded class corresponds exactly to a serialized object.
     * If no match is found, then an InvalidClassException is thrown.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger object
     */
    private static Log log = LogFactory.getLog(Prestiti.class);

    /**
     * beneficiario <br>
     * fk-beneficiario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * This field is part of the primary key of this entity.
     */
    @Id
    @Column(length = 50, nullable = false)
    @QvPattern(regex = "[^|]*", message = "error.qvpattern.message")
    @QrExcelColumn(order = 0)
    private String beneficiario;

    /**
     * oggetto prestato <br>
     * oggetto prestato <br>
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
    private Integer oggetto_prestato;

    /**
     * data prestito <br>
     * data prestito <br>
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
    private Date data_prestito;

    /**
     * data scadenza prestito <br>
     * data scadenza prestito <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>columnName = data ultima modifica
     * <li>nullable = false
     * </ul>
     */
    @Column(length = 9, name = "data ultima modifica", nullable = false)
    @QcDateType()
    @Temporal(TemporalType.DATE)
    @QrExcelColumn(order = 0)
    private Date data_scadenza_prestito;

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
     * Property of field:
     * <ul>
     * <li>columnName = id
     * <li>nullable = true
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Prestiti prestiti;

    @Transient
    private boolean prestitiJdbcAlreadyChecked = false;

    @Transient
    private String prestiti_beneficiario;

    @Transient
    private Integer prestiti_oggetto_prestato;

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
        Prestiti other = (Prestiti) obj;
        if (beneficiario == null) {
            if (other.getBeneficiario() != null) {
                return false;
            }
        } else if (!beneficiario.equals(other.getBeneficiario())) {
            return false;
        }
        if (oggetto_prestato == null) {
            if (other.getOggetto_prestato() != null) {
                return false;
            }
        } else if (!oggetto_prestato.equals(other.getOggetto_prestato())) {
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
        result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
        result = prime * result + ((oggetto_prestato == null) ? 0 : oggetto_prestato.hashCode());
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

        sb.append(", ").append("beneficiario=").append(beneficiario);

        sb.append(", ").append("oggetto_prestato=").append(oggetto_prestato);

        sb.append(", ").append("data_prestito=").append(data_prestito);

        sb.append(", ").append("data_scadenza_prestito=").append(data_scadenza_prestito);

        sb.append(", ").append("soggetti=").append(soggetti);

        sb.append(", ").append("prestiti=").append(prestiti);

        sb.append(")");
        return sb.toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PrePersist
    public void prePersist() {
        this.beneficiario = UUID.randomUUID().toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void prePersist(Configuration configuration) {
        prePersist();
        this.beneficiario = UUID.randomUUID().toString();
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
     * Return the beneficiario
     * fk-beneficiario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @return the beneficiario
     * @see Prestiti.beneficiario
     */

    public String getBeneficiario() {
        return beneficiario;
    }

    /**
     * Set the beneficiario
     * fk-beneficiario <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @param beneficiario the beneficiario to set
     * @see Prestiti.beneficiario
     */
    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * Return the oggetto prestato
     * oggetto prestato <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @return the oggetto_prestato
     * @see Prestiti.oggetto_prestato
     */

    public Integer getOggetto_prestato() {
        return oggetto_prestato;
    }

    /**
     * Set the oggetto prestato
     * oggetto prestato <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @param oggetto_prestato the oggetto prestato to set
     * @see Prestiti.oggetto_prestato
     */
    public void setOggetto_prestato(Integer oggetto_prestato) {
        this.oggetto_prestato = oggetto_prestato;
    }

    /**
     * Return the data prestito
     * data prestito <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>nullable = false
     * </ul>
     * 
     * @return the data_prestito
     * @see Prestiti.data_prestito
     */

    public Date getData_prestito() {
        return data_prestito;
    }

    /**
     * Set the data prestito
     * data prestito <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>nullable = false
     * </ul>
     * 
     * @param data_prestito the data prestito to set
     * @see Prestiti.data_prestito
     */
    public void setData_prestito(Date data_prestito) {
        this.data_prestito = data_prestito;
    }

    /**
     * Return the data scadenza prestito
     * data scadenza prestito <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>columnName = data ultima modifica
     * <li>nullable = false
     * </ul>
     * 
     * @return the data_scadenza_prestito
     * @see Prestiti.data_scadenza_prestito
     */

    public Date getData_scadenza_prestito() {
        return data_scadenza_prestito;
    }

    /**
     * Set the data scadenza prestito
     * data scadenza prestito <br>
     * Property of field:
     * <ul>
     * <li>length = 9
     * <li>columnName = data ultima modifica
     * <li>nullable = false
     * </ul>
     * 
     * @param data_scadenza_prestito the data scadenza prestito to set
     * @see Prestiti.data_scadenza_prestito
     */
    public void setData_scadenza_prestito(Date data_scadenza_prestito) {
        this.data_scadenza_prestito = data_scadenza_prestito;
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
     * @see Prestiti.soggetti
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
     * @see Prestiti.soggetti
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

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>columnName = id
     * <li>nullable = true
     * </ul>
     * 
     * @return the prestiti
     * @see Prestiti.prestiti
     */

    public Prestiti getPrestiti() {
        if (jdbc && prestiti == null && (prestiti_beneficiario != null && prestiti_oggetto_prestato != null) && !prestitiJdbcAlreadyChecked) {
            try {
                prestiti = getQborrrowManager().getPrestiti(prestiti_beneficiario, prestiti_oggetto_prestato);
                prestitiJdbcAlreadyChecked = true;
            } catch (DAOFinderException e) {
                log.debug("Unexpected DAOFinderException on getPrestiti by prestiti_beneficiario = " + prestiti_beneficiario + ", prestiti_oggetto_prestato = "
                    + prestiti_oggetto_prestato, e);
            }
        }
        return prestiti;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>columnName = id
     * <li>nullable = true
     * </ul>
     * 
     * @param prestiti
     * @see Prestiti.prestiti
     */
    public void setPrestiti(Prestiti prestiti) {
        this.prestiti = prestiti;
        if (prestiti != null) {
            prestiti_beneficiario = prestiti.getBeneficiario();
            prestiti_oggetto_prestato = prestiti.getOggetto_prestato();
        } else {
            prestiti_beneficiario = null;
            prestiti_oggetto_prestato = null;
        }
    }

    public String getPrestiti_beneficiario() {
        if (jdbc) {
            return prestiti_beneficiario;
        } else {
            return prestiti == null ? null : prestiti.getBeneficiario();
        }
    }

    public void setPrestiti_beneficiario(String prestiti_beneficiario) {
        if (jdbc) {
            if (this.prestiti_beneficiario != null && !this.prestiti_beneficiario.equals(prestiti_beneficiario)) {
                prestiti = null;
                prestitiJdbcAlreadyChecked = false;
            }
            this.prestiti_beneficiario = prestiti_beneficiario;
        } else {
            throw new ModelJdbcException("The method setPrestiti_beneficiario can be invoked only on jdbc model.");
        }
    }

    public Integer getPrestiti_oggetto_prestato() {
        if (jdbc) {
            return prestiti_oggetto_prestato;
        } else {
            return prestiti == null ? null : prestiti.getOggetto_prestato();
        }
    }

    public void setPrestiti_oggetto_prestato(Integer prestiti_oggetto_prestato) {
        if (jdbc) {
            if (this.prestiti_oggetto_prestato != null && !this.prestiti_oggetto_prestato.equals(prestiti_oggetto_prestato)) {
                prestiti = null;
                prestitiJdbcAlreadyChecked = false;
            }
            this.prestiti_oggetto_prestato = prestiti_oggetto_prestato;
        } else {
            throw new ModelJdbcException("The method setPrestiti_oggetto_prestato can be invoked only on jdbc model.");
        }
    }

    @JSON(include = false)
    public boolean isPrestitiJdbcAlreadyChecked() {
        return prestitiJdbcAlreadyChecked;
    }

    public void setPrestitiJdbcAlreadyChecked(boolean prestitiJdbcAlreadyChecked) {
        this.prestitiJdbcAlreadyChecked = prestitiJdbcAlreadyChecked;
    }

}