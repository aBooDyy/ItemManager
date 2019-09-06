package me.aboodyy.itemmanager.commands.inventorycommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.aboodyy.itemmanager.ItemWrapper;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.aboodyy.itemmanager.utils.ItemUtils.getItemSlots;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.convertAbbr;
import static org.bukkit.Material.matchMaterial;

@CommandAlias("itemmanager|imanager|im")
public class CheckCommand extends BaseCommand {

    @Subcommand("check")
    @CommandCompletion("@players @modifiers")
    @CommandPermission("itemmanager.check")
    public void onCheck(CommandSender sender, String[] arguments) {
        String mat = null, nameSW = null, nameE = null, nameER = null,
                nameC = null, nameCR = null, nameEW = null, loreSW = null,
                loreE = null, loreC = null, loreCR = null, loreEW = null;
        String[] enchs = null;
        int data = 0, amt = 1;
        boolean strict = false;
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
                    mat = arg[1]; break;
                case "amount":
                case "amt":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid amount."));
                        return;
                    }
                    amt = Integer.parseInt(arg[1]); break;
                case "data":
                case "damage":
                case "durability":
                    if (!NumberUtils.isNumber(arg[1])) {
                        sender.sendMessage(color("&cPlease set a valid data."));
                        return;
                    }
                    data = Integer.parseInt(arg[1]); break;
                case "namestartswith":
                case "namesw":
                    nameSW = arg[1]; break;
                case "nameequals":
                case "namee":
                    nameE = arg[1]; break;
                case "nameequalsregex":
                case "nameer":
                    nameER = arg[1]; break;
                case "namecontains":
                case "namec":
                    nameC = arg[1]; break;
                case "namecontainsregex":
                case "namecr":
                    nameCR = arg[1]; break;
                case "nameendswith":
                case "nameew":
                    nameEW = arg[1]; break;
                case "lorestartswith":
                case "loresw":
                    loreSW = arg[1]; break;
                case "loreequals":
                case "loree":
                    loreE = arg[1]; break;
                case "lorecontains":
                case "lorec":
                    loreC = arg[1]; break;
                case "lorecontainsregex":
                case "lorecr":
                    loreCR = arg[1]; break;
                case "loreendswith":
                case "loreew":
                    loreEW = arg[1]; break;
                case "enchantments":
                case "enchs":
                    enchs = arg[1].split("\\|"); break;
                case "strict":
                    strict = true;
            }
        }

        ItemWrapper Wrapper = new ItemWrapper(mat, data, amt, nameSW, nameE, nameER, nameC, nameCR, nameEW,
                loreSW, loreE, loreC, loreCR, loreEW, enchs, strict);

        boolean hasItem = getItemSlots(p, Wrapper) != null;

        if (hasItem) {
            sender.sendMessage(color("&f" + p.getName() + " &ahas the specified item."));
            return;
        }
        sender.sendMessage(color("&f" + p.getName() + " &cdoesn't have the specified item."));
    }

}
