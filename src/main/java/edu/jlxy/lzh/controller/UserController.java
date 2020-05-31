package edu.jlxy.lzh.controller;

import edu.jlxy.lzh.pojo.User;
import edu.jlxy.lzh.service.Impl.UserServiceImpl;
import edu.jlxy.lzh.service.UserService;
import edu.jlxy.lzh.utils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserController extends BaseController {
    private UserService userService = new UserServiceImpl();
    /**
     * 处理登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1、获取请求的参数,注入User对象
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = BeanUtils.beanparams(req.getParameterMap(), new User());
        //2、检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)){
            //3、检查用户名是否可用
            if (userService.existsUsername(username)){
                //信息回显
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //调用Service保存到数据库
                userService.registUser(user);
                //跳到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //信息回显
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数,注入User对象
        String username = req.getParameter("username");
        User user = BeanUtils.beanparams(req.getParameterMap(), new User());
        //调用userService.login()登录处理业务
        User loginUser = userService.login(user);
        //如果等于null,说明登录失败!
        if (loginUser == null) {
            //数据回显
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //登录成功
            //保护用户登录成功的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect("http://localhost:8080/bookshop");
    }

    /**
     * 异步校验
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数username
        String username = req.getParameter("username");
        //调用service中方法
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成map对象
        Map<Object, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
