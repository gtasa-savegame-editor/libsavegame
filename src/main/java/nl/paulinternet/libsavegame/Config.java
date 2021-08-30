package nl.paulinternet.libsavegame;

public class Config {

    private static Config self;

    private boolean enableGarages = true;

    public static Config get() {
        if (self == null) {
            self = new Config();
        }
        return self;
    }

    private Config() {
    }

    public boolean garagesEnabled() {
        return enableGarages;
    }

    public void setEnableGarages(boolean enableGarages) {
        this.enableGarages = enableGarages;
    }
}
