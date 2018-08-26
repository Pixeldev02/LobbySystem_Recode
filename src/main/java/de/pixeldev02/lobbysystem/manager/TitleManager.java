package de.pixeldev02.lobbysystem.manager;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleManager {

    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

        PacketPlayOutTitle PacketPlayOutTime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (BaseComponent[]) null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket(PacketPlayOutTime);
        if(subtitle != null) {
            IChatBaseComponent TitleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, TitleSub);
            connection.sendPacket(packetPlayOutSubTitle);
        }
        if(title != null) {
            IChatBaseComponent Title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, Title);
            connection.sendPacket(packetPlayOutSubTitle);
        }
    }
}
