package br.edu.ifsp.ifitness.model.util;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public enum Util {

    RESOURCES_PATH(new Object() {
        String evaluate() {
            try {
                return Paths.get(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().getParent().getParent().getParent().resolve("resources").resolve("database").toString();
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
            }

            return "";
        }
    }.evaluate());

    private final String value;

    Util(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
