package me.aboodyy.itemmanager.commands.enchantcommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.aboodyy.itemmanager.utils.Enchantments.getGlowEnchantment;
import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;

@CommandAlias("itemmanager|imanager|im")
public class GlowCommand extends BaseCommand {

    @Subcommand("glow")
    @CommandCompletion("@players")
    @CommandPermission("itemmanager.glow")
    public void onGlow(CommandSender sender, String[] args) {
        if (!(sender instanceof Player) && args.length == 0 || args.length > 1) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager glow [Player]"));
            return;
        }

        Player p = args.length == 1 ? Bukkit.getPlayerExact(args[0]) : (Player) sender;
        if (p == null) {
            sender.sendMessage(color("&f" + args[0] + " &cis not online."));
            return;
        }

        ItemStack item = getItemInHand(p);

        if (item == null || item.getType() == Material.AIR) {
            sender.sendMessage(color( "&f" + p.getName() + " &cisn't holding an item."));
            return;
        }

        item.addUnsafeEnchantment(getGlowEnchantment(), 1);
        sender.sendMessage(color("&aGlow effect has been successfully added to &f" + p.getName() + "'s &aitem."));
    }

}
