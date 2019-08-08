package me.aboodyy.itemmanager.commands.enchantcommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import static me.aboodyy.itemmanager.utils.Enchantments.getEnchantment;
import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;

@CommandAlias("itemmanager|imanager|im")
public class EnchantRemoveCommand extends BaseCommand {

    @Subcommand("enchant remove")
    @CommandCompletion("@enchantments @players")
    @CommandPermission("itemmanager.enchant.remove")
    public void onRemove(CommandSender sender, String[] args) {

        if (args.length == 0 || !(sender instanceof Player) && args.length != 2) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager enchant remove <enchantment> [player]"));
            return;
        }

        Player p = args.length == 2 ? Bukkit.getPlayerExact(args[1]) : (Player) sender;
        Enchantment ench = getEnchantment(args[0]);

        if (p == null) {
            sender.sendMessage(color("&f" + args[1] + " &cis not online."));
            return;
        }
        if (ench == null) {
            sender.sendMessage(color("&f" + args[0] + " &cis not an enchantment."));
            return;
        }
        if (getItemInHand(p) == null || getItemInHand(p).getType() == Material.AIR) {
            sender.sendMessage(color( "&f" + p.getName() + " &cisn't holding an item."));
            return;
        }
        if (!getItemInHand(p).getEnchantments().containsKey(ench)) {
            sender.sendMessage(color( "&f" + p.getName() + "'s &eitem doesn't have this enchantment to remove it."));
            return;
        }

        getItemInHand(p).removeEnchantment(ench);
        sender.sendMessage(color("&f" + args[0] + " &aenchantment has successfully removed from &f" + p.getName() + "'s &a item."));

    }

}
