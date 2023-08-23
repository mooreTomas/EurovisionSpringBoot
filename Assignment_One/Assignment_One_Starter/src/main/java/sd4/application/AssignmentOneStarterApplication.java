package sd4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import sd4.repository.EntrantRepository;
import sd4.repository.VenueRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@SpringBootApplication
@ComponentScan({"sd4.service", "sd4.controller"})
@EntityScan("sd4.model")
@EnableJpaRepositories("sd4.repository")

public class AssignmentOneStarterApplication implements CommandLineRunner, WebMvcConfigurer {

    @Autowired
    private EntrantRepository entrantRepo;
    @Autowired
    private VenueRepository venueRepo;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("en", "IE"));
        return slr;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }


    @Configuration
    public class AppConfig {

        @Bean(name = "myDateFormat")
        public DateTimeFormatter myDateFormat() {
            Locale userLocale = Locale.getDefault();
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(userLocale);
        }
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }




    public static void main(String[] args) {
        SpringApplication.run(AssignmentOneStarterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
