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

package me.aboodyy.itemmanager.commands.enchantcommands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.aboodyy.itemmanager.commands.ItemManagerCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.aboodyy.itemmanager.utils.Enchantments.getGlowEnchantment;
import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;

@CommandAlias("itemmanager|imanager|im")
public class GlowCommand extends ItemManagerCommand {

    @Subcommand("glow")
    @CommandCompletion("@players")
    @CommandPermission("itemmanager.glow")
    public void onGlow(CommandSender sender, String[] args) {
        if (!(sender instanceof Player) && args.length == 0 || args.length > 1) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager glow [Player] [-S]"));
            return;
        }

        Player p = args.length == 1 ? Bukkit.getPlayerExact(args[0]) : (Player) sender;
        if (p == null) {
            sender.sendMessage(color("&f" + args[0] + " &cis not online."));
            return;
        }

        ItemStack item = getItemInHand(p);
        boolean isSilent = args.length > 0 && args[args.length - 1].equalsIgnoreCase("-s");

        if (item == null || item.getType() == Material.AIR) {
            sender.sendMessage(color( "&f" + p.getName() + " &cisn't holding an item."));
            return;
        }

        item.addUnsafeEnchantment(getGlowEnchantment(), 1);

        if (isSilent) return;

        sender.sendMessage(color("&aGlow effect has been successfully added to &f" + p.getName() + "'s &aitem."));
    }

}
