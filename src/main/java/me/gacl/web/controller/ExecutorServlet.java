package me.gacl.web.controller;

import me.gacl.domain.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiezhonggui on 16-4-18.
 */
@WebServlet("/ExecutorServlet")
public class ExecutorServlet extends HttpServlet {
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final String string = "finalExecutorServlet";
    private final User user =  new User();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("ExecutorServlet init() Method......");
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName("xiezhonggui");
        user.setUserBirthday(new Date());
        user.setUserSalary(12d);
        System.out.println(user);
        System.out.println("ExecutorServlet init() Method......");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Thread Pool Execute....." + string);
                try {
                    Thread.sleep(30000);
                    user.setUserId(UUID.randomUUID().toString());
                    user.setUserName("leorain");
                    user.setUserBirthday(new Date());
                    user.setUserSalary(23d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(user);
                }
                System.out.println("Thread Pool Execute....." + string);
            }
        });
        response.getWriter().println("HelloWolrd");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
