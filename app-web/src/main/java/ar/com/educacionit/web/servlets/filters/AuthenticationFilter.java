package ar.com.educacionit.web.servlets.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.educacionit.web.dto.User;

@WebFilter(filterName="/AuthenticationFilter",urlPatterns="/*" )
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		this.context.log("Requested Resource::" + uri);

		HttpSession session = req.getSession(false);

		final List<String> excludedPath = Arrays.asList(
				contextPath+ "/meli/categorias",
				contextPath+"/login.jsp", 
				contextPath+"/notLogged.jsp", 
				contextPath+"/login", 
				contextPath+"/logout", 
				req.getContextPath() + "/"
		);

		boolean isExcluded = excludedPath.stream()
				// .peek(x ->System.out.println(x))
				.filter(x -> uri.equals(x)).count() > 0;
		
		if(isExcluded) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}else {
				
			if (session == null) {
				this.context.log("Unauthorized access request");
				res.sendRedirect(req.getContextPath()+"/notLogged.jsp");
			} else {
				
				User loggedUser = (User)session.getAttribute("user");
				
				if(loggedUser != null) {
					// pass the request along the filter chain
					chain.doFilter(request, response);
				}else {
					this.context.log("Unauthorized access request");
					res.sendRedirect(req.getContextPath()+"/notLogged.jsp");
				}
			}
		}
	}

	public void destroy() {
		// close any resources here
	}

}
