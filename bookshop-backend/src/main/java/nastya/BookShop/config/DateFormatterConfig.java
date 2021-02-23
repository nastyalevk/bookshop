package nastya.BookShop.config;

import nastya.BookShop.model.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DateFormatterConfig {

    @Bean
    public DateFormatter dateFormat() {
        return new DateFormatter();
    }

}
