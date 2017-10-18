package it.quix.academy.qborrrow.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.manager.ManagerHolder;
import it.quix.academy.qborrrow.core.manager.QborrrowManager;

/**
 *
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class QborrrowInterceptor extends AbstractInterceptor {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * QborrrowManager
     */
    private QborrrowManager qborrrowManager;

    /**
     * SysAttributeHandler
     */
    private SysAttributeHandler sysAttributeHandler;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        qborrrowManager = (QborrrowManager) wac.getBean("qborrrowManager");
        sysAttributeHandler = (SysAttributeHandler) wac.getBean("sysAttributeHandler");

        ManagerHolder.setManagerOnThreadLocal("qborrrowManager", qborrrowManager);
        ManagerHolder.setManagerOnThreadLocal("sysAttributeHandler", sysAttributeHandler);

        return invocation.invoke();
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

    /**
     * @see #sysAttributeHandler
     * @return the sysAttributeHandler
     */
    public SysAttributeHandler getSysAttributeHandler() {
        return sysAttributeHandler;
    }

    /**
     * @see #sysAttributeHandler
     * @param sysAttributeHandler the sysAttributeHandler to set
     */
    public void setSysAttributeHandler(SysAttributeHandler sysAttributeHandler) {
        this.sysAttributeHandler = sysAttributeHandler;
    }
}