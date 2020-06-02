package bindings.driver;
import org.openqa.selenium.Dimension;

public enum DimensionType {
    DESKTOP(new Dimension(1920, 1080)),
    TABLET(new Dimension(1366, 768)),
    MOBILE(new Dimension(360, 640));

    private final Dimension dimension;

    DimensionType(final Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
}
