package model.util;

import java.io.IOException;

public class FileExtensionError extends IOException {
    public FileExtensionError(String message) {
        super("Extension is not: " +  message);
    }

    public FileExtensionError() {
    }
}
