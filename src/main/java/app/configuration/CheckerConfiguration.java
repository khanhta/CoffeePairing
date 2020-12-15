package app.configuration;

import app.services.checkers.Checker;
import app.services.checkers.IsWithinAMonth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckerConfiguration {
    @Bean
    public Checker getIsWithinAMonthChecker() {
        return new IsWithinAMonth();
    }
}
