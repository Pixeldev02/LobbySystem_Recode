package de.pixeldev02.lobbysystem.gadgets;

public enum GadgetItemRarity {

    COMMON("COMMON", 0),
    RARE("RARE", 1),
    EPIC("EPIC", 2),
    LEGENDARY("LEGENDARY", 3);

    private GadgetItemRarity(final String s, final int n) {
    }

    public static String getName(final GadgetItemRarity type) {
        String name = "§a";
        if (type == GadgetItemRarity.COMMON) {
            name = "§aHÄUFIG";
        }
        if (type == GadgetItemRarity.RARE) {
            name = "§9SELTEN";
        }
        if (type == GadgetItemRarity.EPIC) {
            name = "§5EPISCH";
        }
        if (type == GadgetItemRarity.LEGENDARY) {
            name = "§6LEGENDÄR";
        }
        return name;
    }
}
