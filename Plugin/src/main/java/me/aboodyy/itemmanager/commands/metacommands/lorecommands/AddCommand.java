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

package me.aboodyy.itemmanager.commands.metacommands.lorecommands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;;
import me.aboodyy.itemmanager.commands.ItemManagerCommand;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.*;

@CommandAlias("itemmanager|imanager|im")
public class AddCommand extends ItemManagerCommand {

    @Subcommand("lore add")
    @CommandCompletion("<lore> @players")
    @CommandPermission("itemmanager.lore.add")
    public void onLoreAdd(CommandSender sender, String[] args) {
        String space = getConfig().getString("symbols.space", "_");
        String newLine = getConfig().getString("symbols.new_line", "|");
        Player p;
        String lore;
        int line = -1;

        if (args.length == 0 || !(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore add [line] <lore> [Player] [-S]"));
            return;
        }

        switch (args.length) {
            case 4:
            case 3:
                p = Bukkit.getPlayerExact(args[2]);
                lore = args[1];
                line = Integer.parseInt(args[0]);
                break;
            case 2:
                if (sender instanceof Player) {
                    if (NumberUtils.isNumber(args[0])) {
                        if (Bukkit.getPlayerExact(args[1]) != null) {
                            p = Bukkit.getPlayerExact(args[1]);
                            lore = args[0];
                        } else {
                            p = (Player) sender;
                            lore = args[1];
                            line = Integer.parseInt(args[0]);
                        }
                        break;
                    }
                }
                p = Bukkit.getPlayerExact(args[1]);
                lore = args[0];
                break;
            case 1:
                p = (Player) sender;
                lore = args[0];
                break;
            default:
                sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore add [line] <lore> [Player] [-S]"));
                return;
        }

        if (p == null) {
            sender.sendMessage(color("&f" + (line == -1 ? args[1] : args[2]) + " &cis not online."));
            return;
        }
        if (getItemInHand(p) == null || getItemInHand(p).getType() == Material.AIR) {
            sender.sendMessage(color( "&f" + p.getName() + " &cisn't holding an item."));
            return;
        }

        boolean isSilent = args[args.length - 1].equalsIgnoreCase("-s");
        ItemMeta meta = getItemInHand(p).getItemMeta();
        List<String> iLore = new ArrayList<>();
        if (meta.hasLore()) iLore = meta.getLore();
        lore = color(lore.replace(space, " "));

        if (line == -1) {
            iLore.addAll(Arrays.asList(lore.split(Pattern.quote(newLine))));
        } else {
            if (line > (iLore == null ? 0 : iLore.size())) {
                while (line - 1 > (iLore == null ? 0 : iLore.size())) {
                    iLore.add("");
                }
            }

            iLore.addAll(line - 1, Arrays.asList(lore.split(Pattern.quote(newLine))));
        }

        meta.setLore(iLore);
        getItemInHand(p).setItemMeta(meta);

        if (isSilent) return;

        sender.sendMessage(color("&aItem lore has been successfully updated for &f" + p.getName()));
    }

}
