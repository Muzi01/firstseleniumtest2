package cucumber.linkstatusverification;


import io.vavr.control.Option;

public interface CookiesHolder extends SingleValueHolder<String> {
    Option<String> provideCookieEntry();
}
