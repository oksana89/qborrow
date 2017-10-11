package it.quix.academy.qborrrow.core.model;

import java.io.Serializable;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
 * The Soggetti entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 14:58:54
 */
@Entity
@Table(name = "soggetti")
public class Soggetti extends QborrrowAbstractModel implements Serializable {

    /**
     * The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses
     * this number to ensure that a loaded class corresponds exactly to a serialized object.
     * If no match is found, then an InvalidClassException is thrown.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger object
     */
    private static Log log = LogFactory.getLog(Soggetti.class);

    /**
     * user_name <br>
     * Nome utente <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = user_name
     * <li>nullable = false
     * </ul>
     * This field is part of the primary key of this entity.
     */
    @Id
    @Column(length = 50, name = "user_name", nullable = false)
    @QvPattern(regex = "[^|]*", message = "error.qvpattern.message")
    @QrExcelColumn(order = 0)
    private String user_name;

    /**
     * email <br>
     * email <br>
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     */
    @Column(length = 100, nullable = false)
    @QrExcelColumn(order = 0)
    private String email;

    /**
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     */
    @Column()
    @QrExcelColumn(order = 0)
    private String ragione_sociale;

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 50)
    @QrExcelColumn(order = 0)
    private String nome;

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 50)
    @QrExcelColumn(order = 0)
    private String cognome;

    /**
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
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     */
    @Column(nullable = false)
    @QcDateType()
    @Temporal(TemporalType.DATE)
    @QrExcelColumn(order = 0)
    private Date data_ultima_modifica;

    /**
	 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_name")
    @QrExcelColumn(order = 0)
    private Set<Oggetti> oggetti;

    @Transient
    private boolean oggettiJdbcAlreadyChecked = false;

    /**
	 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_name")
    @QrExcelColumn(order = 0)
    private Set<Prestiti> prestiti;

    @Transient
    private boolean prestitiJdbcAlreadyChecked = false;

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
        Soggetti other = (Soggetti) obj;
        if (user_name == null) {
            if (other.getUser_name() != null) {
                return false;
            }
        } else if (!user_name.equals(other.getUser_name())) {
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
        result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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

        sb.append(", ").append("user_name=").append(user_name);

        sb.append(", ").append("email=").append(email);

        sb.append(", ").append("ragione_sociale=").append(ragione_sociale);

        sb.append(", ").append("nome=").append(nome);

        sb.append(", ").append("cognome=").append(cognome);

        sb.append(", ").append("immagine=").append(immagine);

        sb.append(", ").append("data_ultima_modifica=").append(data_ultima_modifica);

        sb.append(", ").append("oggetti=").append(oggetti);

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
        this.user_name = UUID.randomUUID().toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void prePersist(Configuration configuration) {
        prePersist();
        this.user_name = UUID.randomUUID().toString();
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
     * Return the user_name
     * Nome utente <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = user_name
     * <li>nullable = false
     * </ul>
     * 
     * @return the user_name
     * @see Soggetti.user_name
     */

    public String getUser_name() {
        return user_name;
    }

    /**
     * Set the user_name
     * Nome utente <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>columnName = user_name
     * <li>nullable = false
     * </ul>
     * 
     * @param user_name the user_name to set
     * @see Soggetti.user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Return the email
     * email <br>
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     * 
     * @return the email
     * @see Soggetti.email
     */

    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     * email <br>
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     * 
     * @param email the email to set
     * @see Soggetti.email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the ragione_sociale
     * @see Soggetti.ragione_sociale
     */

    public String getRagione_sociale() {
        return ragione_sociale;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param ragione_sociale
     * @see Soggetti.ragione_sociale
     */
    public void setRagione_sociale(String ragione_sociale) {
        this.ragione_sociale = ragione_sociale;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @return the nome
     * @see Soggetti.nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @param nome
     * @see Soggetti.nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @return the cognome
     * @see Soggetti.cognome
     */

    public String getCognome() {
        return cognome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @param cognome
     * @see Soggetti.cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the immagine
     * @see Soggetti.immagine
     */

    public String getImmagine() {
        return immagine;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param immagine
     * @see Soggetti.immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @return the data_ultima_modifica
     * @see Soggetti.data_ultima_modifica
     */

    public Date getData_ultima_modifica() {
        return data_ultima_modifica;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = false
     * </ul>
     * 
     * @param data_ultima_modifica
     * @see Soggetti.data_ultima_modifica
     */
    public void setData_ultima_modifica(Date data_ultima_modifica) {
        this.data_ultima_modifica = data_ultima_modifica;
    }

    /**
     * <br>
     * 
     * @return the oggetti
     * @see Soggetti.oggetti
     */

    public Set<Oggetti> getOggetti() {
        if (jdbc && !oggettiJdbcAlreadyChecked && (oggetti == null || oggetti.isEmpty())) {
            List<Oggetti> list = getQborrrowManager().getOggettiListBySoggetti(user_name);
            oggettiJdbcAlreadyChecked = true;
            Set<Oggetti> set = new HashSet<Oggetti>();
            set.addAll(list);
            oggetti = set;
        }
        return oggetti;
    }

    /**
     * <br>
     * 
     * @param oggetti
     * @see Soggetti.oggetti
     */
    public void setOggetti(Set<Oggetti> oggetti) {
        this.oggetti = oggetti;
    }

    @JSON(include = false)
    public boolean isOggettiJdbcAlreadyChecked() {
        return oggettiJdbcAlreadyChecked;
    }

    public void setOggettiJdbcAlreadyChecked(boolean oggettiJdbcAlreadyChecked) {
        this.oggettiJdbcAlreadyChecked = oggettiJdbcAlreadyChecked;
    }

    /**
     * <br>
     * 
     * @return the prestiti
     * @see Soggetti.prestiti
     */

    public Set<Prestiti> getPrestiti() {
        if (jdbc && !prestitiJdbcAlreadyChecked && (prestiti == null || prestiti.isEmpty())) {
            List<Prestiti> list = getQborrrowManager().getPrestitiListBySoggetti(user_name);
            prestitiJdbcAlreadyChecked = true;
            Set<Prestiti> set = new HashSet<Prestiti>();
            set.addAll(list);
            prestiti = set;
        }
        return prestiti;
    }

    /**
     * <br>
     * 
     * @param prestiti
     * @see Soggetti.prestiti
     */
    public void setPrestiti(Set<Prestiti> prestiti) {
        this.prestiti = prestiti;
    }

    @JSON(include = false)
    public boolean isPrestitiJdbcAlreadyChecked() {
        return prestitiJdbcAlreadyChecked;
    }

    public void setPrestitiJdbcAlreadyChecked(boolean prestitiJdbcAlreadyChecked) {
        this.prestitiJdbcAlreadyChecked = prestitiJdbcAlreadyChecked;
    }

}