package it.quix.academy.qborrrow.core.search.generated;

import it.quix.framework.core.model.AbstractSearchModel;
import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.academy.qborrrow.core.model.Oggetti;
import java.util.Date;
import java.math.BigInteger;
import it.quix.academy.qborrrow.core.search.PrestitiSearch;
import it.quix.academy.qborrrow.core.model.Soggetti;

import it.quix.framework.core.converter.annotation.QcDateType;

/**
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class PrestitiAbstractSearch extends AbstractSearchModel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Soggetti soggetti;

    private String soggetti_user_name;

    private Oggetti oggetti;

    private Integer oggetti_id;

    @QcDateType()
    private Date data_prestitoFrom;

    @QcDateType()
    private Date data_prestitoTo;

    @QcDateType()
    private Date data_scadenza_prestitoFrom;

    @QcDateType()
    private Date data_scadenza_prestitoTo;

    public void clearFilter() {
        soggetti = null;
        soggetti_user_name = null;
        oggetti = null;
        oggetti_id = null;
        data_prestitoFrom = null;
        data_prestitoTo = null;
        data_scadenza_prestitoFrom = null;
        data_scadenza_prestitoTo = null;
    }

    public PrestitiSearch cloneFilter() {
        PrestitiSearch search = new PrestitiSearch();

        search.setSoggetti(soggetti);
        search.setSoggetti_user_name(soggetti_user_name);
        search.setOggetti(oggetti);
        search.setOggetti_id(oggetti_id);
        search.setData_prestitoFrom(data_prestitoFrom);
        search.setData_prestitoTo(data_prestitoTo);
        search.setData_scadenza_prestitoFrom(data_scadenza_prestitoFrom);
        search.setData_scadenza_prestitoTo(data_scadenza_prestitoTo);
        return search;
    }

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
        if (getClass() != obj.getClass()) {
            return false;
        }
        PrestitiAbstractSearch other = (PrestitiAbstractSearch) obj;
        if (soggetti == null) {
            if (other.getSoggetti() != null) {
                return false;
            }
        } else if (!soggetti.equals(other.getSoggetti())) {
            return false;
        }
        if (soggetti_user_name == null) {
            if (other.getSoggetti_user_name() != null) {
                return false;
            }
        } else if (!soggetti_user_name.equals(other.getSoggetti_user_name())) {
            return false;
        }
        if (oggetti == null) {
            if (other.getOggetti() != null) {
                return false;
            }
        } else if (!oggetti.equals(other.getOggetti())) {
            return false;
        }
        if (oggetti_id == null) {
            if (other.getOggetti_id() != null) {
                return false;
            }
        } else if (!oggetti_id.equals(other.getOggetti_id())) {
            return false;
        }
        if (data_prestitoFrom == null) {
            if (other.getData_prestitoFrom() != null) {
                return false;
            }
        } else if (!data_prestitoFrom.equals(other.getData_prestitoFrom())) {
            return false;
        }
        if (data_prestitoTo == null) {
            if (other.getData_prestitoTo() != null) {
                return false;
            }
        } else if (!data_prestitoTo.equals(other.getData_prestitoTo())) {
            return false;
        }
        if (data_scadenza_prestitoFrom == null) {
            if (other.getData_scadenza_prestitoFrom() != null) {
                return false;
            }
        } else if (!data_scadenza_prestitoFrom.equals(other.getData_scadenza_prestitoFrom())) {
            return false;
        }
        if (data_scadenza_prestitoTo == null) {
            if (other.getData_scadenza_prestitoTo() != null) {
                return false;
            }
        } else if (!data_scadenza_prestitoTo.equals(other.getData_scadenza_prestitoTo())) {
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

        result = prime * result + ((soggetti == null) ? 0 : soggetti.hashCode());
        result = prime * result + ((oggetti == null) ? 0 : oggetti.hashCode());
        result = prime * result + ((data_prestitoFrom == null) ? 0 : data_prestitoFrom.hashCode());
        result = prime * result + ((data_prestitoTo == null) ? 0 : data_prestitoTo.hashCode());
        result = prime * result + ((data_scadenza_prestitoFrom == null) ? 0 : data_scadenza_prestitoFrom.hashCode());
        result = prime * result + ((data_scadenza_prestitoTo == null) ? 0 : data_scadenza_prestitoTo.hashCode());

        result = prime * result + ((soggetti == null) ? 0 : soggetti.hashCode());
        result = prime * result + ((oggetti == null) ? 0 : oggetti.hashCode());
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
        sb.append("soggetti=").append(soggetti);
        sb.append("oggetti=").append(oggetti);
        sb.append(", ").append("data_prestitoFrom=").append(data_prestitoFrom);
        sb.append(", ").append("data_prestitoTo=").append(data_prestitoTo);
        sb.append(", ").append("data_scadenza_prestitoFrom=").append(data_scadenza_prestitoFrom);
        sb.append(", ").append("data_scadenza_prestitoTo=").append(data_scadenza_prestitoTo);
        sb.append(")");
        return sb.toString();
    }

    /**
     * @return the soggetti
     * @see PrestitiSearch#soggetti
     */
    public Soggetti getSoggetti() {
        return soggetti;
    }

    /**
     * @param soggetti the soggetti to set
     * @see PrestitiSearch#soggetti
     */
    public void setSoggetti(Soggetti soggetti) {
        this.soggetti = soggetti;
    }

    /**
     * @return the soggetti_user_name
     * @see PrestitiSearch#soggetti_user_name
     */
    public String getSoggetti_user_name() {
        return soggetti_user_name;
    }

    /**
     * @param soggetti_user_name the soggetti_user_name to set
     * @see PrestitiSearch#soggetti_user_name
     */
    public void setSoggetti_user_name(String soggetti_user_name) {
        this.soggetti_user_name = soggetti_user_name;
    }

    /**
     * @return the oggetti
     * @see PrestitiSearch#oggetti
     */
    public Oggetti getOggetti() {
        return oggetti;
    }

    /**
     * @param oggetti the oggetti to set
     * @see PrestitiSearch#oggetti
     */
    public void setOggetti(Oggetti oggetti) {
        this.oggetti = oggetti;
    }

    /**
     * @return the oggetti_id
     * @see PrestitiSearch#oggetti_id
     */
    public Integer getOggetti_id() {
        return oggetti_id;
    }

    /**
     * @param oggetti_id the oggetti_id to set
     * @see PrestitiSearch#oggetti_id
     */
    public void setOggetti_id(Integer oggetti_id) {
        this.oggetti_id = oggetti_id;
    }

    /**
     * @return the data_prestitoFrom
     * @see PrestitiSearch#data_prestitoFrom
     */
    public Date getData_prestitoFrom() {
        return data_prestitoFrom;
    }

    /**
     * @param data_prestitoFrom the data_prestitoFrom to set
     * @see PrestitiSearch#data_prestitoFrom
     */
    public void setData_prestitoFrom(Date data_prestitoFrom) {
        this.data_prestitoFrom = data_prestitoFrom;
    }

    /**
     * @return the data_prestitoTo
     * @see PrestitiSearch#data_prestitoTo
     */
    public Date getData_prestitoTo() {
        return data_prestitoTo;
    }

    /**
     * @param data_prestitoTo the data_prestitoTo to set
     * @see PrestitiSearch#data_prestitoTo
     */
    public void setData_prestitoTo(Date data_prestitoTo) {
        this.data_prestitoTo = data_prestitoTo;
    }

    /**
     * @return the data_scadenza_prestitoFrom
     * @see PrestitiSearch#data_scadenza_prestitoFrom
     */
    public Date getData_scadenza_prestitoFrom() {
        return data_scadenza_prestitoFrom;
    }

    /**
     * @param data_scadenza_prestitoFrom the data_scadenza_prestitoFrom to set
     * @see PrestitiSearch#data_scadenza_prestitoFrom
     */
    public void setData_scadenza_prestitoFrom(Date data_scadenza_prestitoFrom) {
        this.data_scadenza_prestitoFrom = data_scadenza_prestitoFrom;
    }

    /**
     * @return the data_scadenza_prestitoTo
     * @see PrestitiSearch#data_scadenza_prestitoTo
     */
    public Date getData_scadenza_prestitoTo() {
        return data_scadenza_prestitoTo;
    }

    /**
     * @param data_scadenza_prestitoTo the data_scadenza_prestitoTo to set
     * @see PrestitiSearch#data_scadenza_prestitoTo
     */
    public void setData_scadenza_prestitoTo(Date data_scadenza_prestitoTo) {
        this.data_scadenza_prestitoTo = data_scadenza_prestitoTo;
    }

    public int orderByManagement(String orderField, OrderByTypeEnum orderType) {
        if (orderField.equals("soggetti")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 1;
            } else {
                order = 2;
            }
        }
        if (orderField.equals("data_prestito")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 3;
            } else {
                order = 4;
            }
        }
        if (orderField.equals("data_scadenza_prestito")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 5;
            } else {
                order = 6;
            }
        }

        return order;
    }

}