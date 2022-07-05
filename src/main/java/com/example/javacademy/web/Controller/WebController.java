package com.example.javacademy.web.Controller;

import com.example.javacademy.Model.User;
import com.example.javacademy.Service.AuthService;
import com.example.javacademy.exceptions.InvalidArgumentsException;
import com.example.javacademy.exceptions.InvalidUserCredentialsException;
import com.example.javacademy.exceptions.PasswordsDoNotMatchException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class WebController {
    private final AuthService authService;
    @Autowired
    ServletContext servletContext;
    private final TemplateEngine templateEngine;

    public WebController(AuthService authService, TemplateEngine templateEngine) {
        this.authService = authService;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public String showSearch(HttpServletRequest request, Model model){
        User user= (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "index.html";
    }
    @GetMapping("/index-logged")
    public String showindexlogged(HttpServletRequest request, Model model){
        User user= (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "index-logged.html";
    }

    @GetMapping("/login")
    // @RequestMapping(method = RequestMethod.GET,value = "")
    public String getLoginPage(){
        return "login.html";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model){
        User user=null;
        try{
            user = this.authService.login(request.getParameter("username"),request.getParameter("password"));
            request.getSession().setAttribute("user",user);
            return "redirect:/index-logged";
        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError",true);
            model.addAttribute("error",exception.getMessage());
            return "login.html";
        }
    }
    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if ( error != null && !error.isEmpty() ) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        return "register";
    }
    @GetMapping("/quiz-questions")
    public String quizz(){
        return "quiz-questions.html";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname){
        try {
            this.authService.register(username, password, repeatedPassword, name, surname);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException | InvalidArgumentsException exception){
            return "redirect:/register?=error="+exception.getMessage();
        }

    }
    @GetMapping("/get-certificate")
    public String showCert(){
        return "get-certificate.html";
    }
    @GetMapping("/quiz")
    public String showQuiz(){
        return "quiz.html";
    }
    @GetMapping("/quiz-logged")
    public String showQuizlogged(){
        return "quiz-logged.html";
    }
    @GetMapping("/learn")
    public String showLearn(Model model){
        return "learn.html";
    }
    @GetMapping("/learn-logged")
    public String showLearnlogged(Model model){
        return "learn-logged.html";
    }

    @GetMapping("/sources")
    public String showSources(Model model){
        return "sources.html";
    }
    @GetMapping("/sources-logged")
    public String showSourceslogged(Model model){
        return "sources-logged.html";
    }

    @GetMapping("/learn/oop")
    public String showOop(Model model){
        return "oop.html";
    }
    @GetMapping("/learn/oop-logged")
    public String showOoplogged(Model model){
        return "oop-logged.html";
    }

    @GetMapping("/learn/java-basics")
    public String showBasics(Model model){
        return "java-basics.html";
    }
    @GetMapping("/learn/java-basics-logged")
    public String showBasicslogged(Model model){
        return "java-basics-logged.html";
    }

    @GetMapping("/learn/intro")
    public String showIntro(Model model){
        return "javaintro.html";
    }

    @GetMapping("/learn/intro-logged")
    public String showIntrologged(Model model){
        return "javaintro-logged.html";
    }

    @GetMapping("/learn/methods")
    public String showMethods(Model model){
        return "methods.html";
    }
    @GetMapping("/learn/methods-logged")
    public String showMethodslogged(Model model){
        return "methods-logged.html";
    }

    @GetMapping("/learn/conditions")
    public String showCond(Model model){
        return "conditions.html";
    }
    @GetMapping("/learn/conditions-logged")
    public String showCondlogged(Model model){
        return "conditions-logged.html";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}
