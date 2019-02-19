package com.udemy.com.udemy.controller;

import com.udemy.com.udemy.constant.ViewConstant;
import com.udemy.com.udemy.model.UserCredential;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;

@Controller
public class LoginController {

    private static final Log log = LogFactory.getLog(LoginController.class);

 /*   @GetMapping("/")
    public String redirectToLogin(){
        log.info("METHOD: redirecToLogin()");
        return "redirect:/login";
    }
*/
    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @RequestParam(name = "error", required = false) String error,
                                @RequestParam(name = "logout", required = false) String logout){
        log.info("METHOD: showLoginForm() --"+"Params: error = "+error+" logout: "+logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        /*model.addAttribute("userCredentials", new UserCredential());*/
        log.info("Regresando a la vista de login");
        return ViewConstant.LOGIN;
    }

    /*@PostMapping("/loginCheck")
    public String loginCheck(@ModelAttribute("userCredentials")UserCredential userCredential){
        if(userCredential.getUsername().equals("user")&& userCredential.getPassword().equals("user")){
            log.info("METHOD: loginCheck() --"+"Params:"+userCredential.toString());
            log.info("regresando a la vista de contacto");
            return "redirect:/contacts/showContacts";
        }
        log.info("redireccionando al login?error");
        return "redirect:/login?error";
    }*/

    @GetMapping({"/loginsuccess","/"})
    public String loginCheck(){
        log.info("METHOD: loginCheck()");
        log.info("regresando a la vista de contacto");
        //return "redirect:/login?error";
        return "redirect:/contacts/showContacts";
    }
}
