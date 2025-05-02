package org.example.ecommerce.locale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class LocaleTestController {
    private final MessageSource messageSource;

    @GetMapping("/hello")
    public String hello(Locale locale) {
        return messageSource.getMessage("app.welcome", null, locale);
    }
}
