package com.priyanathbhukta.spring_boot_url_shortner.web.controllers;

import com.priyanathbhukta.spring_boot_url_shortner.ApplicationProperties;
import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import com.priyanathbhukta.spring_boot_url_shortner.domain.exceptions.ShortUrlNotFoundException;
import com.priyanathbhukta.spring_boot_url_shortner.domain.models.CreateShortUrlCmd;
import com.priyanathbhukta.spring_boot_url_shortner.domain.models.ShortUrlDto;
import com.priyanathbhukta.spring_boot_url_shortner.domain.services.ShortUrlService;
import com.priyanathbhukta.spring_boot_url_shortner.web.dtos.CreateShortUrlForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;
    private final ApplicationProperties properties;
    public HomeController(ShortUrlService shortUrlService, ApplicationProperties properties) {
        this.shortUrlService = shortUrlService;
        this.properties = properties;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrlDto>shortUrls= shortUrlService.findAllPublicShortUrls();
        model.addAttribute("shortUrls",shortUrls);
        model.addAttribute("baseUrl","http://localhost:8080");
        model.addAttribute("createShortUrlForm",new CreateShortUrlForm(""));
        return "index";
    }

    @PostMapping("/short-urls")
    public String createShortUrl(
            @ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<ShortUrlDto> shortUrls = shortUrlService.findAllPublicShortUrls();
            model.addAttribute("shortUrls", shortUrls);
            model.addAttribute("baseUrl", properties.baseUrl());
            return "index";
        }

        try {
            CreateShortUrlCmd cmd = new CreateShortUrlCmd(form.originalUrl());
            var shortUrlDto = shortUrlService.createShortUrl(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "Short URL created " +properties.baseUrl()
                   + "/s/"+shortUrlDto.shortKey());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create short URL");
        }

        return "redirect:/";
    }

    @GetMapping("/s/{shortKey}")
    public String redirectToOriginalUrl(@PathVariable String shortKey){
        Optional<ShortUrlDto> shortUrlDtoOptional = shortUrlService.accessShortUrl(shortKey);
        if(shortUrlDtoOptional.isEmpty()){
            throw new ShortUrlNotFoundException("Invalid Url "+ shortKey);
        }
        ShortUrlDto shortUrlDto = shortUrlDtoOptional.get();
        return "redirect:"+shortUrlDto.originalUrl();
    }


}
