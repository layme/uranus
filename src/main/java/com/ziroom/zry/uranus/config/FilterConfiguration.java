package com.ziroom.zry.uranus.config;

import com.ziroom.zry.uranus.security.filter.CasSingleSignInFilter;
import com.ziroom.zry.uranus.security.filter.CasSingleSignOutFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @Date Created in 2018年07月18日 13:47
 * @since 1.0
 */
@Configuration
public class FilterConfiguration {
    @Autowired
    private Environment environment;

    /**
     * 该过滤器负责用户的认证工作
     * @author renhy
     * @created 2018年07月18日 14:21:22
     * @param
     * @return
     */
    @Bean
    public FilterRegistrationBean<CasSingleSignInFilter> buildCasSingleSignInFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(environment.getProperty("casSingleSignInFilterOrder", Integer.class));
        filterRegistrationBean.setFilter(new CasSingleSignInFilter());
        filterRegistrationBean.setName("casSingleSignInFilter");
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.addInitParameter("casServerLoginUrl", "http://cas.ziroom.com/CAS/login");
        filterRegistrationBean.addInitParameter("serverName", "http://localhost:9999");
        return filterRegistrationBean;
    }

    /**
     * 该过滤器用于实现单点登出功能
     * @author renhy
     * @created 2018年07月18日 14:22:07
     * @param
     * @return
     */
    @Bean
    public FilterRegistrationBean<CasSingleSignOutFilter> buildCasSingleSignOutFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(environment.getProperty("casSingleSignOutFilterOrder", Integer.class));
        filterRegistrationBean.setFilter(new CasSingleSignOutFilter());
        filterRegistrationBean.setName("casSingleSignOutFilter");
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    /**
     * 该过滤器负责对Ticket的校验工作
     * @author renhy
     * @created 2018年07月18日 14:23:05
     * @param
     * @return
     */
    @Bean
    public FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> buildCas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(environment.getProperty("cas20ProxyReceivingTicketValidationFilterOrder", Integer.class));
        filterRegistrationBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        filterRegistrationBean.setName("cas20ProxyReceivingTicketValidationFilter");
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.addInitParameter("casServerUrlPrefix", "http://cas.ziroom.com/CAS");
        filterRegistrationBean.addInitParameter("redirectAfterValidation", "true");
        filterRegistrationBean.addInitParameter("serverName", "http://localhost:9999");
        return filterRegistrationBean;
    }

    /**
     * 该过滤器负责实现HttpServletRequest请求的包裹
     * @author renhy
     * @created 2018年07月18日 14:23:20
     * @param
     * @return
     */
    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> buildHttpServletRequestWrapperFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(environment.getProperty("httpServletRequestWrapperFilterOrder", Integer.class));
        filterRegistrationBean.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistrationBean.setName("httpServletRequestWrapperFilter");
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
