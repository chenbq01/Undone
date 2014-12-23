package cn.com.uwoa.global.security.filter;  
  
import java.io.IOException;  
import java.util.Collection;    
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;   
import org.springframework.security.access.ConfigAttribute;  
import org.springframework.security.access.SecurityMetadataSource;  
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;  
import org.springframework.security.access.intercept.InterceptorStatusToken;  
import org.springframework.security.core.Authentication;  
import org.springframework.security.core.context.SecurityContextHolder;  
import org.springframework.security.web.FilterInvocation;  
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;  

import cn.com.uwoa.global.security.SecurityHelper;
  
public class SecurityFilter extends AbstractSecurityInterceptor  
        implements Filter {  
  
    private FilterInvocationSecurityMetadataSource securityMetadataSource;  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        FilterInvocation fi = new FilterInvocation(request, response, chain);  
        invoke(fi);  
        
        
        
      }  
  
    @Override  
    public void destroy() {  
    }  
  
    @Override  
    public Class<?> getSecureObjectClass() {  
        return FilterInvocation.class;  
    }  
    
    @Override  
    public SecurityMetadataSource obtainSecurityMetadataSource() {  
        return this.securityMetadataSource;  
    }  
 
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {  
        return this.securityMetadataSource;  
    }  
    
    public void invoke(FilterInvocation fi) throws IOException,  
            ServletException {  
        
    	// object为FilterInvocation对象
		//1.获取请求资源的权限
		//Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes((Object)fi);
		//2.是否拥有权限
		//获取安全主体，可以强制转换为UserDetails的实例
		//1) UserDetails
		// 
		//this.accessDecisionManager.decide(authenticated, object, attributes);
		//用户拥有的权限
		//2) GrantedAuthority
		//Collection<GrantedAuthority> authenticated.getAuthorities()
    	
    	
    	
		System.out.println("用户发送请求！ ");
		InterceptorStatusToken token = super.beforeInvocation(fi);
		
		
		String requestUrl = fi.getFullRequestUrl();
		if(!(requestUrl.endsWith("/login.do")||requestUrl.endsWith("/j_spring_security_check")||requestUrl.endsWith("/j_spring_security_logout")))
		{
			if(!SecurityHelper.isLogin()){
				
				fi.getHttpResponse().sendRedirect(fi.getRequest().getContextPath() + "/login.do");
				return;
			}
			System.out.println(fi.getHttpRequest().getRequestURL());
			
		}
        try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } finally {  
            super.afterInvocation(token, null);  
        }  
    }  
  
    public void setSecurityMetadataSource(  
            FilterInvocationSecurityMetadataSource securityMetadataSource) {  
        this.securityMetadataSource = securityMetadataSource;  
    }  
  
}  
