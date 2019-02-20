package com.udemy.com.udemy.controller;

import com.udemy.com.udemy.constant.ViewConstant;
import com.udemy.com.udemy.entity.Contact;
import com.udemy.com.udemy.model.ContactModel;
import com.udemy.com.udemy.model.UserCredential;
import com.udemy.com.udemy.service.impl.ContactService;
import com.udemy.com.udemy.service.impl.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    private static final Log log = LogFactory.getLog(ContactController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/contacts/showContacts";
    }

    @GetMapping("/contactForm")
    public String redirectContactForm(Model model,
                                      @RequestParam(name = "id", required = false) int id){
        ContactModel contactModel = new ContactModel();
        if(id != 0){
            contactModel = contactService.findContactByIdModel(id);
        }
        model.addAttribute("contactModel", contactModel);
        return ViewConstant.CONTACT_FORM;
    }

    @PostMapping("/addcontact")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de contactform
    public String addContact(@ModelAttribute(name = "contactModel")ContactModel contactModel,
                             Model model){
        log.info("Method: addContact() -- Params: "+contactModel.toString());
        if(contactService.addContact(contactModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/contacts/showContacts";
    }

    @GetMapping("/showContacts")
    public ModelAndView showContacts(){
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.addObject("username", user.getUsername());
        mav.addObject("contacts", contactService.listAllContacts());
        return mav;
    }

    @GetMapping("/removeContact")
    public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id){
        contactService.removeContact(id);
        return showContacts();
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute(name = "userCredential")UserCredential userCredential,
                          Model model){
        log.info("addUser()");
        userService.addUser(userCredential);
        return "redirect:/login";
    }

    @GetMapping("/addUserForm")
    public String addUserForm(Model model){
        log.info("Method: addUserForm()");
        model.addAttribute("userCredential", new UserCredential());
        return ViewConstant.ADD_USER;
    }

    @GetMapping("/add")
    public String add(){
        log.info("Method: add()");
        return "redirect:/contacts/addUserForm";
    }
}
