package com.ziroom.zry.uranus.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>该过滤器负责用户的认证工作</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @Date Created in 2018年07月13日 19:56
 * @since 1.0
 */
@Slf4j
public class CasSingleSignInFilter extends AbstractCasFilter {
    private String casServerLoginUrl;
    private boolean renew = false;
    private boolean gateway = false;
    private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

    @Value("#{'${workBench}'.trim()}")
    private String serviceUrl;

    private String loginUri = "/api/system/getUpsList";

    @Override
    public void init() {
        super.init();
        CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestUri = request.getRequestURI();
        // option预检查，直接通过请求
        if (HttpMethod.OPTIONS.equals(request.getMethod())){
            filterChain.doFilter(request, response);
        }
        //
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        String xRequest = request.getHeader("x-requested-with");
        HttpSession session = request.getSession(false);
        Assertion assertion = session != null?(Assertion)session.getAttribute("_const_cas_assertion_"):null;
        if(assertion != null) {
            filterChain.doFilter(request, response);
        } else {
            String serviceUrl = this.constructServiceUrl(request, response);
            String ticket = CommonUtils.safeGetParameter(request, this.getArtifactParameterName());
            boolean isUseGateway = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);

            if(!CommonUtils.isNotBlank(ticket)&&xRequest != null && xRequest.equalsIgnoreCase("XMLHttpRequest")) {
                PrintWriter printWriter=response.getWriter();
                printWriter.print("session_timeout");
                printWriter.flush();
                printWriter.close();
                return;
            } else if (!CommonUtils.isNotBlank(ticket) && !isUseGateway){
                log.info("no ticket and no assertion found");
                // 如果访问的登录接口则跳转到CAS登录，否则返回未登录
                if (loginUri.equals(requestUri)) {
//                    String modifiedServiceUrl = "http://localhost:8008";
                    String modifiedServiceUrl = "http://localhost:9999/api/system/getUpsList";
                    String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, this.getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);
                    log.info("redirecting to \"" + urlToRedirectTo + "\"");
                    response.sendRedirect(urlToRedirectTo);
                } else {
                    PrintWriter printWriter=response.getWriter();
                    printWriter.print("u5000");
                    printWriter.flush();
                    printWriter.close();
                    return;
                }
            }else {
                filterChain.doFilter(request, response);
            }
        }
    }

    public final void setRenew(boolean renew) {
        this.renew = renew;
    }

    public final void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public final void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public final void setGatewayStorage(GatewayResolver gatewayStorage) {
        this.gatewayStorage = gatewayStorage;
    }

    @Override
    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        if(!this.isIgnoreInitConfiguration()) {
            super.initInternal(filterConfig);
            this.setCasServerLoginUrl(this.getPropertyFromInitParams(filterConfig, "casServerLoginUrl", (String)null));
            log.trace("Loaded CasServerLoginUrl parameter: " + this.casServerLoginUrl);
            this.setRenew(this.parseBoolean(this.getPropertyFromInitParams(filterConfig, "renew", "false")));
            log.trace("Loaded renew parameter: " + this.renew);
            this.setGateway(this.parseBoolean(this.getPropertyFromInitParams(filterConfig, "gateway", "false")));
            log.trace("Loaded gateway parameter: " + this.gateway);
            String gatewayStorageClass = this.getPropertyFromInitParams(filterConfig, "gatewayStorageClass", (String)null);
            if(gatewayStorageClass != null) {
                try {
                    this.gatewayStorage = (GatewayResolver)Class.forName(gatewayStorageClass).newInstance();
                } catch (Exception var4) {
                    log.error("miss an Exception:", var4);
                    throw new ServletException(var4);
                }
            }
        }

    }
}
