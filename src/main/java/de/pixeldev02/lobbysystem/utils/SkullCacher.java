package de.pixeldev02.lobbysystem.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkullCacher {

    public static Map<String, String> cached = new HashMap<String, String>();

    @SuppressWarnings("deprecation")
    public static ItemStack getHead(OfflinePlayer player) {
        String skinURL = null;
        loadSkin: if (cached.containsKey(player.getName()))
            skinURL = cached.get(player.getName());
        else {
            try {
                GameProfile profile = new GameProfile(player.getUniqueId(), player.getName());
                Field field;
                field = MinecraftServer.class.getDeclaredField("W");
                field.setAccessible(true);
                Object value = field.get(MinecraftServer.getServer());
                if (!(value instanceof MinecraftSessionService))
                    break loadSkin;
                MinecraftSessionService ss = (MinecraftSessionService) value;
                ss.fillProfileProperties(profile, true);
                Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures = ss.getTextures(profile, true);
                if (textures.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                    MinecraftProfileTexture tex = textures.get(MinecraftProfileTexture.Type.SKIN);
                    skinURL = tex.getUrl();
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        if (skinURL == null)
            return head;
        cached.put(player.getName(), skinURL);
        ItemMeta headMeta = head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder()
                .encode((String.format("{\"textures\":{\"SKIN\":{\"url\":\"%s\"}}}", skinURL).getBytes()));
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
}
