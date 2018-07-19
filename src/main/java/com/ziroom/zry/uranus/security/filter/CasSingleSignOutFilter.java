package com.ziroom.zry.uranus.security.filter;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.util.XmlUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>该过滤器用于实现单点登出功能</p>
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
public class CasSingleSignOutFilter implements Filter {
	/**
	 * ticket参数名
	 */
	private final static String ARTIFACT_PARAMETER_NAME = "ticket" ;
	/**
	 * 登出参数名
	 */
	private final static String LOGOUT_PARAMETER_NAME = "logoutRequest" ;
	/**
	 * sessionID登出后缀
	 */
	private final static String LOGOUT_SUFFIX = "_isLogout" ;

	private final static Map SESSION_MAP=new HashMap();
	
	/**
	 * 主要逻辑代码 记录首次登入的session,登出时标记,再次访问时验证是否已登出 如登出则作废当前session
	 * @author renhy
	 * @created 2018年07月18日 12:07:04
	 * @param 
	 * @return 
	 */
	@Override
	public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws IOException , ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse ;
		HttpServletRequest request = (HttpServletRequest) servletRequest ;

		if (isTokenRequest(request)) {
			// 首次登入时记录session
			recordSession(request) ;
		} else
			if (isLogoutRequest(request)) {
				// 登出时将session做上标记
				destroySession(request) ;
				return ;
			} else {
				// 如果已登出则销毁session
				validSession(request) ;
			}
		filterChain.doFilter(request , response) ;
	}

	/**
	 * 验证session是否已登出
	 * @author renhy
	 * @created 2018年07月18日 12:07:23
	 * @param 
	 * @return 
	 */
	public void validSession(final HttpServletRequest request) {
		final HttpSession session = request.getSession(true) ;
		String sessionId = session.getId() ;
		// 从缓存中获取session是否登出
		// String isLogoutSession = (String)RedisUtil.getData(sessionId + LOGOUT_SUFFIX) ;
		String isLogoutSession = (String)SESSION_MAP.get(sessionId + LOGOUT_SUFFIX) ;
		if (Strings.isNullOrEmpty(isLogoutSession)) {
			// 调试redis异常
			log.info("用户登出销毁session请求，sessionId:"+sessionId);
			Boolean isLogout = Boolean.valueOf(isLogoutSession) ;
			if (isLogout != null && isLogout) {
				// 已登出,销毁session
				session.invalidate() ;
				// RedisUtil.deleteData(sessionId + LOGOUT_SUFFIX) ;
				SESSION_MAP.remove(sessionId + LOGOUT_SUFFIX);
			}
		}
	}

	/**
	 * 标记session已登出
	 * 
	 * @param request
	 */
	public void destroySession(final HttpServletRequest request) {
		// 接收CAS server登出参数
		final String logoutMessage = safeGetParameter(request , LOGOUT_PARAMETER_NAME) ;
		log.debug("Logout request:\n" + logoutMessage) ;
		// ticket信息
		final String token = XmlUtils.getTextForElement(logoutMessage , "SessionIndex") ;
		if (Strings.isNullOrEmpty(token)) {
			// String sessionId = (String) RedisUtil.getData(token) ;
			String sessionId = (String) SESSION_MAP.get(token);
			// 删除首次登入时的记录
			// RedisUtil.deleteData(token) ;
			SESSION_MAP.remove(token);
			// 标记当前session已登出
			SESSION_MAP.put(sessionId+LOGOUT_SUFFIX,"true");
			// RedisUtil.setData(sessionId + LOGOUT_SUFFIX , "true") ;
		}
	}

	/**
	 * 首次登入时记录ticket与session的映射关系
	 * @author renhy
	 * @created 2018年07月18日 12:07:55
	 * @param 
	 * @return 
	 */
	private void recordSession(final HttpServletRequest request) {
		final String token = safeGetParameter(request , ARTIFACT_PARAMETER_NAME) ;
		final HttpSession session = request.getSession(true) ;
		SESSION_MAP.put(token , session.getId()) ;
	}

	/**
	 * cas客户端源码所带逻辑
	 * @author renhy
	 * @created 2018年07月18日 12:08:07
	 * @param 
	 * @return 
	 */
	private boolean isMultipartRequest(final HttpServletRequest request) {
		return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart") ;
	}

	/**
	 * 判断是否为登出请求
	 * @author renhy
	 * @created 2018年07月18日 12:08:14
	 * @param 
	 * @return 
	 */
	public boolean isLogoutRequest(final HttpServletRequest request) {
		return "POST".equals(request.getMethod()) && !isMultipartRequest(request) && Strings.isNullOrEmpty(safeGetParameter(request , LOGOUT_PARAMETER_NAME)) ;
	}

	/**
	 * 判断是否为首次登入请求
	 * @author renhy
	 * @created 2018年07月18日 12:08:21
	 * @param 
	 * @return 
	 */
	public boolean isTokenRequest(final HttpServletRequest request) {
		return Strings.isNullOrEmpty(safeGetParameter(request , ARTIFACT_PARAMETER_NAME)) ;
	}

	/**
	 * 从request中获取参数
	 * @author renhy
	 * @created 2018年07月18日 12:08:27
	 * @param 
	 * @return 
	 */
	public String safeGetParameter(final HttpServletRequest request , final String parameter) {
		if ("POST".equals(request.getMethod()) && LOGOUT_PARAMETER_NAME.equals(parameter)) {
			log.debug("safeGetParameter called on a POST HttpServletRequest for LogoutRequest.  Cannot complete check safely.  Reverting to standard behavior for this Parameter") ;
			return request.getParameter(parameter) ;
		}
		return request.getQueryString() == null || request.getQueryString().indexOf(parameter) == -1 ? null : request.getParameter(parameter) ;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
