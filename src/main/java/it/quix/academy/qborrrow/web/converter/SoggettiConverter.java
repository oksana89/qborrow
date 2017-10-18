package it.quix.academy.qborrrow.web.converter;

import java.util.Map;
import java.lang.Exception;
import java.math.BigInteger;
import java.util.Date;

import javax.annotation.Resource;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;
import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.academy.qborrrow.core.model.QborrrowUserContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.ValueStack;
import it.quix.framework.web.converter.AbstractTypeConverter;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * Converter class for the Soggetti model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettiConverter extends AbstractTypeConverter<java.lang.String> {

    /**
     * QborrrowManager injected by Spring
     */
    @Resource(name = "qborrrowManager")
    private QborrrowManager qborrrowManager;

    @SuppressWarnings("unchecked")
    @Override
    public Object convertFromString(Map context, String[] arg1, Class arg2) {
        try {
            if (arg1 == null || arg1.length == 0 || arg1[0] == null || arg1[0].equals(""))
                return null;
            if (!arg2.equals(Soggetti.class))
                throw new TypeConversionException();
            ValueStack stack = ActionContext.getContext().getValueStack();
            // QborrrowUserContext uc = (QborrrowUserContext) stack.findValue("userContext");

            String[] pks = arg1[0].split("[|]");
            String user_name = (java.lang.String) convertFromString(pks[0], String.class);
            Soggetti soggetti = getQborrrowManager().getSoggetti(user_name);

            if (soggetti == null) {
                throw new TypeConversionException("Error on SoggettiConverter. Soggetti with key (id serialized) " + arg1[0] + " not found.");
            }
            return soggetti;
        } catch (Exception e) {
            throw new TypeConversionException("Error on SoggettiConverter.", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String convertToString(Map context, Object obj) {
        Soggetti soggetti = (Soggetti) obj;
        try {
            StringBuilder pk = new StringBuilder();
            pk.append(convertToString(soggetti.getUser_name()));
            return pk.toString();
        } catch (Exception e) {
            throw new TypeConversionException(e);
        }
    }

    /**
     * @see #qborrrowManager
     * @return the qborrrowManager
     */
    public QborrrowManager getQborrrowManager() {
        return qborrrowManager;
    }

    /**
     * @see #qborrrowManager
     * @param qborrrowManager the qborrrowManager to set
     */
    public void setQborrrowManager(QborrrowManager qborrrowManager) {
        this.qborrrowManager = qborrrowManager;
    }

}
