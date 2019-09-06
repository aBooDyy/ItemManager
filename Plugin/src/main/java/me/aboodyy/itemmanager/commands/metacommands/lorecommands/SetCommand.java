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
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static me.aboodyy.itemmanager.utils.ItemUtils.getItemInHand;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.*;

@CommandAlias("itemmanager|imanager|im")
public class SetCommand extends ItemManagerCommand {

    @Subcommand("lore set")
    @CommandCompletion("<lore> @players")
    @CommandPermission("itemmanager.lore.set")
    public void onLoreSet(CommandSender sender, String[] args) {
        String space = getConfig().getString("symbols.space", "_");
        String newLine = getConfig().getString("symbols.new_line", "|");
        Player p;
        String lore;
        int line = -1;

        if (args.length == 0 || !(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore set [line] <lore> [player]"));
            return;
        }

        switch (args.length) {
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
                sender.sendMessage(color("&cIncorrect usage. &7/ItemManager lore set [line] <lore> [player]"));
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

        ItemMeta meta = getItemInHand(p).getItemMeta();
        lore = color(lore).replace(space, " ");

        if (line == -1) {
            meta.setLore(Arrays.asList(lore.split(Pattern.quote(newLine))));
        } else {
            List<String> iLore = new ArrayList<>();
            if (meta.hasLore()) iLore = meta.getLore();

            if (line > (iLore == null ? 0 : iLore.size())) {
                while (line > (iLore == null ? 0 : iLore.size())) {
                    iLore.add("");
                }
            }
            iLore.set(line - 1, lore);

            meta.setLore(iLore);
        }

        getItemInHand(p).setItemMeta(meta);
        sender.sendMessage(color("&aItem lore has been successfully updated for &f" + p.getName()));
    }

}