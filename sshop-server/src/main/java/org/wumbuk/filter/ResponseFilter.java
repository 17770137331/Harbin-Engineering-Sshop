package org.wumbuk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther PeihaoYang
 * @date 2020/10/4 - 23:05
 */
@WebFilter(filterName = "myResponseFilter", urlPatterns = "/*")
public class ResponseFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被销毁");
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("过滤器开始执行了14:50。。。。。");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
        chain.doFilter(req, res);
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("----------------------->过滤器被创建14:50");

    }

}
