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
import co.aikar.commands.annotation.Subcommand;
import me.aboodyy.itemmanager.commands.ItemManagerCommand;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.*;

@CommandAlias("itemmanager|imanager|im")
public class LoreRemoveCommand extends ItemManagerCommand {

    @Subcommand("lore remove")
    @CommandCompletion("@lremove @players")
    @CommandPermission("itemmanager.lore.remove")
    public void onLoreRemove(CommandSender sender, String[] args) {
        String space = getConfig().getString("symbols.space", "_");
        Player p;
        String lore = "";
        int line = -1;
        boolean all = false;

        if (args.length == 0 || !(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore remove <line/lore/ALL> [Player] [-S]"));
            return;
        }

        switch (args.length) {
            case 2:
                p = Bukkit.getPlayerExact(args[1]);
                if (NumberUtils.isNumber(args[0]))
                    line = Integer.parseInt(args[0]);
                else {
                    if (args[0].equals("ALL")) all = true;
                    else lore = args[0];
                }
                break;
            case 1:
                p = (Player) sender;
                if (NumberUtils.isNumber(args[0])) line = Integer.parseInt(args[0]);
                else {
                    if (args[0].equals("ALL")) all = true;
                    else lore = args[0];
                }
                break;
            default:
                sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore remove <line/lore/ALL> [Player] [-S]"));
                return;
        }

        if (p == null) {
            sender.sendMessage(color("&f" + args[1] + " &cis not online."));
            return;
        }
        if (getItemInHand(p) == null || getItemInHand(p).getType() == Material.AIR) {
            sender.sendMessage(color( "&f" + p.getName() + " &cisn't holding an item."));
            return;
        }

        boolean isSilent = args[args.length - 1].equalsIgnoreCase("-s");
        ItemMeta meta = getItemInHand(p).getItemMeta();
        if (!meta.hasLore()) {
            sender.sendMessage(color("&cThe item doesn't have a lore to remove it."));
            return;
        }
        List<String> iLore = meta.getLore();

        if (all) {
            iLore.clear();
            meta.setLore(iLore);
        } else if (line != -1) {
            if (line > iLore.size()) {
                sender.sendMessage(color("&cLine &f" + line + " &cdoesn't exist to remove it."));
                return;
            }
            iLore.remove(line - 1);
            meta.setLore(iLore);
        } else {
            lore = color(lore.replace(space, " "));
            List<String> newLore = new ArrayList<>();

            for (String l : iLore) {
                if (l.contains(lore)) continue;

                newLore.add(l);
            }

            if (iLore.equals(newLore)) {
                sender.sendMessage(color("&eLore doesn't contains &f" + lore + "&e. &6Nothing has been changed."));
                return;
            }

            meta.setLore(newLore);
        }

        getItemInHand(p).setItemMeta(meta);

        if (isSilent) return;

        sender.sendMessage(color("&aItem lore has been successfully updated for &f" + p.getName()));
    }

}
