package nastya.BookShop.config;

import nastya.BookShop.model.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DateFormatter.class)
public class DateFormatterConfig {

    private final DateFormatter dateFormatter;

    public DateFormatterConfig(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Bean
    public DateFormatter dateFormat(){
        return dateFormatter;
    }
}
