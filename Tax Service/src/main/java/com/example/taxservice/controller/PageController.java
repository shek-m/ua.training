package com.example.taxservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Controller
public class PageController {

    @GetMapping("/")
    public String viewHomePage(Model model, HttpServletRequest request){
        model.addAttribute("page title", "Tax Service Home Page");
        Locale currentLocale = request.getLocale();
        String countryCode = currentLocale.getCountry();
        String countryName = currentLocale.getDisplayCountry();

        String languageCode = currentLocale.getLanguage();
        String languageName = currentLocale.getDisplayLanguage();

        log.info("{}", countryCode + " : " + countryName);
        log.info("{}", languageCode + " : " + languageName);

        return "index";
    }

}
