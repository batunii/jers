package org.redmark.jesi;
import java.util.Map;

public class JesiBuilder {

    private final Jesi jesi = new Jesi();

    public Jesi buildIndex(String folderPath) throws RuntimeException {
        if(folderPath.isEmpty()||folderPath.isBlank())
        {
            throw new RuntimeException("Folder path cannot be empty");
        }
        else
        {
            jesi.index(folderPath);
            return jesi;
        }
    }

}
