package com.springapp.mvc.aspect;

import com.springapp.mvc.Admin;
import com.springapp.mvc.model.Bucket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by adel on 24.05.14.
 */

@Aspect
@Component
public class SecurityAspect {
    private static final String PANEL_REDIRECT = "redirect:/admin/";
    public static final String DENIED_REDIRECT = "redirect:/admin/login";

    public static Logger log = LoggerFactory.getLogger(SecurityAspect.class);

    @Autowired
    HttpSession session;

    @Around(value = "@annotation(com.springapp.mvc.aspect.Secured)")
    public Object secured(final ProceedingJoinPoint point) throws Exception {
        log.info("@annotation(com.springapp.mvc.aspect.Secured) work");
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            try {
                System.out.println("joinpoint \"" + point.getSignature().getName() + "\" proceeded");
                Object[] objects = point.getArgs();
                for (Object obj: objects)
                    if (obj instanceof Model){
                        Model model = (Model) obj;
                        model.addAttribute("secured", true);
                    }
                return point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        log.info("redirected to login");
        return DENIED_REDIRECT;
    }

    @Around(value = "@annotation(com.springapp.mvc.aspect.ProceedIfLoggedIn)")
    public Object proceed(final ProceedingJoinPoint point){
        log.info("@annotation(com.springapp.mvc.aspect.ProceedIfLoggedIn) work");
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null)
            try {
                System.out.println("joinpoint \"" + point.getSignature().getName() + "\" proceeded");
                return point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        log.info("redirected to admin panel");
        return PANEL_REDIRECT;
    }

    @Before(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void desecure(JoinPoint point){
        Object[] objects = point.getArgs();
        for (Object t: objects)
            if (t instanceof Model){
                Model model = (Model) t;
                model.addAttribute("secure", false);
            }
    }

    @Before(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void bucket(JoinPoint point){
        Bucket bucket = (Bucket) session.getAttribute("bucket");
        if (bucket == null) session.setAttribute("bucket", new Bucket());
    }
}
