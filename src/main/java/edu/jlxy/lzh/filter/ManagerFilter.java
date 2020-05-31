package edu.jlxy.lzh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.jlxy.lzh.pojo.User;

public class ManagerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if("admin".equals(user.getUsername())){
            chain.doFilter(request,response);
        }else{
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    
}