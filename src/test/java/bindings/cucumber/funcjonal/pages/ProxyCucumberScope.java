package bindings.cucumber.funcjonal.pages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Retention(RetentionPolicy.RUNTIME)
@Scope(value = "cucumber-glue", proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface ProxyCucumberScope {
}
