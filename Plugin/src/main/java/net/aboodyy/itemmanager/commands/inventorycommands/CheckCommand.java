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

package net.aboodyy.itemmanager.commands.inventorycommands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import net.aboodyy.itemmanager.ItemWrapper;
import net.aboodyy.itemmanager.commands.ItemManagerCommand;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.aboodyy.itemmanager.utils.ItemUtils.getItemSlots;
import static net.aboodyy.itemmanager.utils.Messages.color;
import static net.aboodyy.itemmanager.utils.Utils.convertAbbr;
import static org.bukkit.Material.matchMaterial;

@CommandAlias("itemmanager|imanager|im")
public class CheckCommand extends ItemManagerCommand {

    @Subcommand("check")
    @CommandCompletion("@players @modifiers")
    @CommandPermission("itemmanager.check")
    public void onCheck(CommandSender sender, String[] arguments) {
        ItemWrapper wrapper = new ItemWrapper();
        Player p;

        String[] args = convertAbbr("check/remove", arguments);

        if (args.length < 1) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager check <Player> <Modifiers>"));
            return;
        }

        p = Bukkit.getPlayerExact(args[0]);
        if (p == null) {
            sender.sendMessage(color("&f" + args[0] + " &cis not online."));
            return;
        }

        for (String argument : args) {
            if (argument.equals(args[0])) continue;
            String[] arg = argument.split(":", 2);
            if (arg.length == 1 && !arg[0].equalsIgnoreCase("strict")) continue;

            switch (arg[0].toLowerCase()) {
                case "material":
                case "mat":
                    if (matchMaterial(arg[1]) == null) {
                        sender.sendMessage(color("&cUnknown material."));
                        return;
                    }
                    wrapper.setMat(arg[1]); break;
                case "amount":
                case "amt":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid amount."));
                        return;
                    }
                    wrapper.setAmt(Integer.parseInt(arg[1])); break;
                case "data":
                case "damage":
                case "durability":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid data."));
                        return;
                    }
                    wrapper.setData(Integer.parseInt(arg[1])); break;
                case "namestartswith":
                case "namesw":
                    wrapper.setNameSW(arg[1]); break;
                case "nameequals":
                case "namee":
                case "name":
                    wrapper.setNameE(arg[1]); break;
                case "nameequalsregex":
                case "nameer":
                    wrapper.setNameER(arg[1]); break;
                case "namecontains":
                case "namec":
                    wrapper.setNameC(arg[1]); break;
                case "namecontainsregex":
                case "namecr":
                    wrapper.setNameCR(arg[1]); break;
                case "nameendswith":
                case "nameew":
                    wrapper.setNameEW(arg[1]); break;
                case "lorestartswith":
                case "loresw":
                    wrapper.setLoreSW(arg[1]); break;
                case "loreequals":
                case "loree":
                    wrapper.setLoreE(arg[1]); break;
                case "lorecontains":
                case "lorec":
                    wrapper.setLoreC(arg[1]); break;
                case "lorecontainsregex":
                case "lorecr":
                    wrapper.setLoreCR(arg[1]); break;
                case "loreendswith":
                case "loreew":
                    wrapper.setLoreEW(arg[1]); break;
                case "enchantments":
                case "enchs":
                    wrapper.setEnchs(arg[1].split("\\|")); break;
                case "strict":
                    wrapper.setStrict(true);
            }
        }

        boolean hasItem = getItemSlots(p, wrapper) != null;

        if (hasItem) {
            sender.sendMessage(color("&f" + p.getName() + " &ahas the specified item."));
            return;
        }
        sender.sendMessage(color("&f" + p.getName() + " &cdoesn't have the specified item."));
    }

}
