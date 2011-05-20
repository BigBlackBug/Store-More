package qbix.sm.server;

/**
 *
 * @author iliax
 */
public class FileSavingConfig {

    private String rootDirectoryPath="C:/";

    private boolean allowed=true;

    public FileSavingConfig() {
    }

    public void setRootDirectoryPath(String rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    
}
