package me.aboodyy.itemmanager.commands.enchantcommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import static me.aboodyy.itemmanager.utils.Enchantments.getEnchantment;
import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;


@CommandAlias("itemmanager|imanager|im")
public class EnchantCommand extends BaseCommand {

    @Subcommand("enchant")
    @CommandCompletion("@enchantments @range:1-5 @players unsafe")
    @CommandPermission("itemmanager.enchant")
    public void onEnchant(CommandSender sender, String[] args) {
        boolean unsafe = false;

        if (args.length < 2 || !(sender instanceof Player) && args.length < 3) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager enchant <enchantment> <level> [player] [unsafe]"));
            return;
        }

        if (!(sender instanceof Player) && args[2].equalsIgnoreCase("unsafe")) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager enchant <enchantment> <level> <player> [unsafe]"));
            return;
        }

        if (!NumberUtils.isNumber(args[1]) || NumberUtils.isNumber(args[1]) && Integer.parseInt(args[1]) < 1) {
            sender.sendMessage(color("&cSet a valid level."));
            return;
        }

        Player p = args.length >= 3 && !args[2].equalsIgnoreCase("unsafe") ? Bukkit.getPlayerExact(args[2]) : (Player) sender;
        Enchantment ench = getEnchantment(args[0]);
        int level = Integer.parseInt(args[1]);
        unsafe = args.length == 3 && args[2].equalsIgnoreCase("unsafe") || args.length == 4 && args[3].equalsIgnoreCase("unsafe");

        if (p == null) {
            sender.sendMessage(color("&f" + args[2] + " &cis not online."));
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

        if (unsafe) {
            getItemInHand(p).addUnsafeEnchantment(ench, level);
        } else {
            if (!ench.canEnchantItem(getItemInHand(p))) {
                sender.sendMessage(color( "&f" + args[0] + " &ccan't be applied to this item."));
                return;
            }
            if (level > ench.getMaxLevel()) {
                sender.sendMessage(color( "&f" + ench.getMaxLevel() + " &cis the maximum level for this enchantment."));
                return;
            }
            getItemInHand(p).addEnchantment(ench, level);
        }

        sender.sendMessage(color("&f" + args[0] + " " + level + " &ahas been successfully added to &f" + p.getName() + "'s &aitem."));

    }

}
