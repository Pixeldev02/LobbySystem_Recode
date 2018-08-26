package de.pixeldev02.lobbysystem.secrets;

import de.pixeldev02.lobbysystem.gadgets.GadgetItemRarity;

public enum  SecretRarity {

    LEICHT("§aLeicht", 1),
    MITTEL("§bMittel", 2),
    SCHWER("§cSchwer", 3);

    private SecretRarity(final String s, final int n) {

    }

    public static String getName(final SecretRarity type) {
        String name = "§a";
        if (type == SecretRarity.LEICHT) {
            name = "§aLeicht";
        }
        if (type == SecretRarity.MITTEL) {
            name = "§bMittel";
        }
        if (type == SecretRarity.SCHWER) {
            name = "§cSchwer";
        }
        return name;
    }
}
