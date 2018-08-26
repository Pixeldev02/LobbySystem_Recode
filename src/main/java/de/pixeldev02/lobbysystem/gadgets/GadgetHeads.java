package de.pixeldev02.lobbysystem.gadgets;

import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.utils.NameFetcher;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GadgetHeads {

    public static HashMap<Integer, GadgetHeads> IDS = new HashMap<>();

    public void registerGadgets() {
        new GadgetHeads("§8» §bUngespielt §8«", "1588abbb-e45b-49e6-9e43-8b83c5d5f812", GadgetItemRarity.COMMON, 22);
        new GadgetHeads("§8» §bPaluten §8«", "6e61a2a9-3cb7-4687-9013-f275dd7357bd", GadgetItemRarity.COMMON, 23);
        new GadgetHeads("§8» §bGermanLetsPlay §8«", "2d76acc5-46c7-47ea-83e4-2ea1fd1285de", GadgetItemRarity.COMMON, 24);
        new GadgetHeads("§8» §bGommeHD §8«", "e9013c2f-da01-425f-a48b-516f55e94386", GadgetItemRarity.COMMON, 25);
        new GadgetHeads("§8» §bMaudado §8«", "8a4122bc-5efe-45e4-9090-8d037dd4c47d", GadgetItemRarity.COMMON, 26);
        new GadgetHeads("§8» §bRewinside §8«", "7bc638a8-dfcd-4496-8fc1-9481421f8a3b", GadgetItemRarity.COMMON, 27);
        new GadgetHeads("§8» §bZinus §8«", "6880c1cd-b482-47f6-b771-8951f5f5d5a0", GadgetItemRarity.COMMON, 28);

    }

    private String name;
    private String uuid;
    private GadgetItemRarity rarity;
    private Integer ID;

    public GadgetHeads(String name, String uuid, GadgetItemRarity rarity, Integer ID) {
        this.name = name;
        this.uuid = uuid;
        this.rarity = rarity;
        this.ID = ID;
        IDS.put(ID, this);
    }

    public int getId() {
        return this.ID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public GadgetItemRarity getRarity() {
        return this.rarity;
    }

    public void setId(int id) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ItemStack getItem() {
        ItemStack head = new ItemManager(Material.SKULL_ITEM, (short) 3).setSkullOwner(NameFetcher.getName(getUuid())).setDisplayName(getName()).build();
        return head;
    }

    public Material getMaterial() {
        Material head = getItem().getType();
        return head;
    }


    public ItemStack getHeadFromID(Integer IDs) {
        for(int i = 0; i < IDS.size(); i++) {
            GadgetHeads head = IDS.get(i);
            if(head.getId() == IDs) {
                return head.getItem();
            }
        }
        return null;
    }

}
