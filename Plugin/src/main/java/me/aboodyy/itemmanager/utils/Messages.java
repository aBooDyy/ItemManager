package me.aboodyy.itemmanager.utils;

import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;

import static me.aboodyy.itemmanager.ItemManager.pl;

public class Messages {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static TextComponent getMessage(String display, String suggestion, String tooltip) {
        TextComponent message = new TextComponent(color(display));

        BaseComponent[] tt = new ComponentBuilder(color(tooltip)).create();
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tt));

        ClickEvent click = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion);
        message.setClickEvent(click);

        return message;
    }

    public static TextComponent getHeader() {
        TextComponent right = new TextComponent(color("&7&m               "));
        TextComponent left = new TextComponent(color("&7&m               "));
        TextComponent im = new TextComponent(color("&9 ItemManager "));
        BaseComponent[] tooltip = new ComponentBuilder(
                color("\n &9Version: &f" + pl.getDescription().getVersion() + " \n &9Author: &faBooDyy \n" +
                        " &9Contact: &faBooDyy.net/Discord \n &9Command aliases: &f/IManager, /IM \n"))
                .create();

        im.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip));
        im.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/70136/"));

        right.addExtra(im);
        right.addExtra(left);

        return right;
    }

}
