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

package me.aboodyy.itemmanager.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.aboodyy.itemmanager.ItemManager.pl;
import static me.aboodyy.itemmanager.utils.Messages.color;

public class Utils {

    private static String space = getConfig().getString("symbols.space", "_");

    private static String newLine = getConfig().getString("symbols.new_line", "|");

    public static FileConfiguration getConfig() {
        return pl.getConfig();
    }

    static boolean contains(String text, String regex) {
        Pattern ex = Pattern.compile(regex);
        Matcher match = ex.matcher(text);
        return match.find();
    }

    static boolean contains(List<String> list, String text) {
        text = color(text.replace(space, " "));

        if (list != null) {
            for (String s : list) {
                if (s.contains(text)) return true;
            }
        }

        return false;
    }

    static boolean containsR(List<String> list, String regex) {
        Pattern ex = Pattern.compile(regex);
        if (list != null) {
            for (String s : list) {
                Matcher match = ex.matcher(s);

                if (match.find()) return true;
            }
        }
        return false;
    }

    static boolean startsWith(List<String> list, String prefix) {
        prefix = color(prefix.replace(space, " "));

        if (list != null && list.size() > 0) {
            return list.get(0).startsWith(prefix);
        }
        return false;
    }

    static boolean equals(List<String> list, String value) {
        value = color(value.replace(space, " "));
        List<String> ls = Arrays.asList(value.split(Pattern.quote(newLine)));

        return list != null && list.equals(ls);
    }

    static boolean endsWith(List<String> list, String suffix) {
        suffix = color(suffix.replace(space, " "));

        if (list != null) {
            return list.get(list.size() - 1).endsWith(suffix);
        }

        return false;
    }

    public static String[] convertAbbr(String path, String[] args) {
        List<String> newArgs = new ArrayList<>();

        if (getConfig().getConfigurationSection("abbreviations." + path) == null)
            return args;

        for (String arg : args) {
            for (String key : getConfig().getConfigurationSection("abbreviations." + path).getKeys(false)) {
                arg = arg.replace("{" + key + "}", getConfig().getString("abbreviations." + path + "." + key));
            }
            newArgs.addAll(Arrays.asList(arg.split(" ")));
        }

        return newArgs.toArray(new String[0]);
    }

}
