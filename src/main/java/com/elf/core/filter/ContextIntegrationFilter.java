package com.elf.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elf.core.context.context.Context;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.context.context.impl.ContextImpl;

public class ContextIntegrationFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		/**功能待完善*/
		if (request == null) {
			return;
		}

		if (request.getAttribute("_session_integration_filter_applied") != null) {
			try {
				filterChain.doFilter(request, response);
			} catch (Exception localException) {
				localException.printStackTrace();
			} finally {

			}
		} else {
			request.setAttribute("_session_integration_filter_applied", Boolean.TRUE);
			ContextHolder.setContext(null);

			HttpSession httpSession = null;
			try {
				httpSession = ((HttpServletRequest) request).getSession(false);
			} catch (IllegalStateException localIllegalStateException1) {
				if (logger.isWarnEnabled()) {
					logger.warn("No HttpSession currently exists");
				}

			}

			if (httpSession != null) {
				Context context = (Context) httpSession.getAttribute("com.elf.core.context.Context");

				if (context == null) {
					context = new ContextImpl();
				}
				ContextHolder.setContext(context);
			}

			httpSession = null;
			try {
				filterChain.doFilter(request, response);
			} catch (IOException localIOException) {
				throw localIOException;
			} catch (ServletException localServletException) {
				throw localServletException;
			} finally {
				try {
					httpSession = ((HttpServletRequest) request).getSession(false);
				} catch (IllegalStateException localIllegalStateException2) {
					if (logger.isWarnEnabled()) {
						logger.warn("No HttpSession currently exists");
					}
				}

				if (httpSession != null)
					httpSession.setAttribute("com.elf.core.context.Context",
							ContextHolder.getContext());
			}

		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}