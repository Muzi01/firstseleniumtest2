package bindings.cucumber.funcjonal.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@StoryProxyComponent
public class PageFactory {
    private static final Logger LOGGER = LogManager.getLogger(PageFactory.class);

    private final List<AbstractPage> pages;

    private AbstractPage currentPage;

    public PageFactory(
            final List<AbstractPage> pages) {
        this.pages = pages;
    }

    private AbstractPage getPage(final String pageName) {
        return pages.stream().filter(page -> page.getPageName().equals(pageName)).findFirst()
                .orElseThrow(() -> new PageNotFoundException(
                        "Did not found page: " + pageName));
    }

    private void updateCurrentPage(final String pageName) {
        currentPage = getPage(pageName);
        LOGGER.info("Current page set to: {}", pageName);
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }

    public void open(final String pageName) {
        updateCurrentPage(pageName);
    }
}
