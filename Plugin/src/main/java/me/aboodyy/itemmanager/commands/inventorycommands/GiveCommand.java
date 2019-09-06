package me.aboodyy.itemmanager.commands.inventorycommands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.aboodyy.itemmanager.commands.ItemManagerCommand;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static me.aboodyy.itemmanager.ItemManager.pl;
import static me.aboodyy.itemmanager.utils.Enchantments.getEnchantment;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.convertAbbr;
import static org.bukkit.Material.matchMaterial;

@CommandAlias("itemmanager|imanager|im")
public class GiveCommand extends ItemManagerCommand {

    @Subcommand("give")
    @CommandCompletion("@players @materials @gmodifiers")
    @CommandPermission("itemmanager.give")
    public void onGiving(CommandSender sender, String[] arguments) {
        String space = pl.getConfig().getString("symbols.space", "_");
        String newLine = pl.getConfig().getString("symbols.new_line", "|");
        int data = 0, amt = 1;
        String name = null;
        List<String> lore = null;
        Map<Enchantment, Integer> enchs = null;
        boolean unsafe = false;

        String[] args = convertAbbr("give", arguments);

        if (args.length < 2) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager give <Player> <Material> [Modifiers]"));
            return;
        }

        Player p = Bukkit.getPlayerExact(args[0]);
        Material mat = matchMaterial(args[1]);
        if (p == null) {
            sender.sendMessage(color("&f" + args[0] + " &cis not online."));
            return;
        }
        if (mat == null) {
            sender.sendMessage(color("&cUnknown material."));
            return;
        }
        if (p.getInventory().firstEmpty() == -1) {
            sender.sendMessage(color("&f" + args[0] + " &cdoesn't have an empty slot."));
            return;
        }

        for (String argument : args) {
            if (argument.equals(args[0])) continue;
            String[] arg = argument.split(":", 2);
            if (arg.length == 1 && !arg[0].equalsIgnoreCase("unsafe")) continue;

            switch (arg[0].toLowerCase()) {
                case "amount":
                case "amt":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid amount."));
                        return;
                    }
                    amt = Integer.parseInt(arg[1]);
                    break;
                case "data":
                case "damage":
                case "durability":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid data."));
                        return;
                    }
                    data = Integer.parseInt(arg[1]);
                    break;
                case "name":
                    name = color(arg[1]).replace(space, " "); break;
                case "lore":
                    arg[1] = color(arg[1].replace(space, " "));
                    lore = Arrays.asList(arg[1].split(Pattern.quote(newLine)));
                    break;
                case "enchantments":
                case "enchs":
                    String[] enchantments = arg[1].split("\\|");
                    enchs = new HashMap<>();
                    for (String ench : enchantments) {
                        String[] e = ench.split(":", 2);
                        if (e.length == 1) continue;
                        if (!NumberUtils.isNumber(e[1]) || NumberUtils.isNumber(e[1]) && Integer.parseInt(e[1]) < 1) continue;

                        enchs.put(getEnchantment(e[0]), Integer.parseInt(e[1]));
                    }
                    break;
                case "unsafe":
                    unsafe = true;
            }
        }

        ItemStack item = new ItemStack(mat, amt, (short) data);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        if (enchs != null) {
            for (Enchantment ench : enchs.keySet()) {
                if (!unsafe && !ench.canEnchantItem(item)) continue;

                meta.addEnchant(ench, enchs.get(ench), unsafe);
            }
        }
        item.setItemMeta(meta);

        p.getInventory().addItem(item);
    }

}
