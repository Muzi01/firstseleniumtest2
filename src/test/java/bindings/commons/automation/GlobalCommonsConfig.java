package bindings.commons.automation;

import com.ipfdigital.automation.customer.CustomerGeneratorConfig;
import com.ipfdigital.database.connection.DBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import ({CustomerGeneratorConfig.class, DBConfig.class})
public class GlobalCommonsConfig {
}
