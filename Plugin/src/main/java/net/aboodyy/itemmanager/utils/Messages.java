/*

    ItemManager - A plugin to manage items in player's inventory.
    Copyright (C) 2019 aBooDyy

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package net.aboodyy.itemmanager.utils;

import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;

import static net.aboodyy.itemmanager.ItemManager.pl;

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
