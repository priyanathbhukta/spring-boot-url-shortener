package com.priyanathbhukta.spring_boot_url_shortner.web.controllers;

import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import com.priyanathbhukta.spring_boot_url_shortner.domain.services.ShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;

    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrl>shortUrls= shortUrlService.findAllPublicShortUrls();
        model.addAttribute("shortUrls",shortUrls);
        model.addAttribute("baseUrl","http://localhost:8080");
        return "index";
    }


}
