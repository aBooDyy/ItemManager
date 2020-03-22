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

package me.aboodyy.itemmanager.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.aboodyy.itemmanager.ItemManager.pl;
import static me.aboodyy.itemmanager.utils.Messages.*;
import static me.aboodyy.itemmanager.utils.Utils.getConfig;

@CommandAlias("itemmanager|imanager|im")
public class ItemManagerCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("itemmanager.help")
    public void onUnknown(CommandSender sender) {
        sender.sendMessage(color("&cIncorrect usage. &7/ItemManager help"));
    }

    @HelpCommand
    @CommandPermission("itemmanager.help")
    public void onHelp(CommandSender sender) {
        String space = getConfig().getString("symbols.space", "_");
        String newLine = getConfig().getString("symbols.new_line", "|");

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.spigot().sendMessage(getHeader());
            p.sendMessage("");
            p.spigot().sendMessage(getMessage(" &b/ItemManager Help",
                    "/itemmanager help",
                    "\n &fDisplays all the plugin's command. \n\n" +
                            " &9Usage: &f/IM Help \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Reload",
                    "/itemmanager reload",
                    "\n &fReloads the plugin's config file. \n\n" +
                            " &9Usage: &f/IM Reload \n"));
            p.sendMessage("");
            p.spigot().sendMessage(getMessage(" &b/ItemManager Give",
                    "/itemmanager give ",
                    "\n &fGive item to the player. \n\n" +
                            " &9Usage: &f/IM Give <Player> <Material> [Modifiers] \n" +
                            " &9Modifiers: \n" +
                            "&7   - &fAmt:<Amount> \n" +
                            "&7   - &fData:<Data/Durability> \n" +
                            "&7   - &fName:<Display_Name> \n" +
                            "&7   - &fLore:<Lore_1|Lore_2> \n" +
                            "&7   - &fEnchs:<Ench:1|Ench2:1> \n" +
                            "&7   - &AnvilEnchs:<Ench:1|Ench2:1> \n" +
                            "&7   - &fUnsafe \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Check",
                    "/itemmanager check ",
                    "\n &fCheck if the player has the specified item. \n\n" +
                            " &9Usage: &f/IM Check <Player> <Modifiers> \n" +
                            " &9Modifiers: \n" +
                            "&7   - &fMat:<Material> \n" +
                            "&7   - &fAmt:<Amount> \n" +
                            "&7   - &fData:<Data/Durability> \n" +
                            "&7   - &fNameSW:<NameStartsWith> \n" +
                            "&7   - &fNameE:<NameEquals> \n" +
                            "&7   - &fNameER:<NameEqualsRegex> \n" +
                            "&7   - &fNameC:<NameContains> \n" +
                            "&7   - &fNameCR:<NameContainsRegex> \n" +
                            "&7   - &fNameEW:<NameEndsWith> \n" +
                            "&7   - &fLoreSW:<LoreStartsWith> \n" +
                            "&7   - &fLoreE:<LoreEquals> \n" +
                            "&7   - &fLoreC:<LoreContains> \n" +
                            "&7   - &fLoreCR:<LoreContainsRegex> \n" +
                            "&7   - &fLoreEW:<LoreEndsWith> \n" +
                            "&7   - &fEnchs:<Ench:1|Ench2:1> \n" +
                            "&7   - &fStrict \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Remove",
                    "/itemmanager remove ",
                    "\n &fRemove the specified item from the player. \n\n" +
                            " &9Usage: &f/IM Remove <Player> <Modifiers> \n" +
                            " &9Modifiers: \n" +
                            "&7   - &fMat:<Material> \n" +
                            "&7   - &fAmt:<Amount> \n" +
                            "&7   - &fData:<Data/Durability> \n" +
                            "&7   - &fNameSW:<NameStartsWith> \n" +
                            "&7   - &fNameE:<NameEquals> \n" +
                            "&7   - &fNameER:<NameEqualsRegex> \n" +
                            "&7   - &fNameC:<NameContains> \n" +
                            "&7   - &fNameCR:<NameContainsRegex> \n" +
                            "&7   - &fNameEW:<NameEndsWith> \n" +
                            "&7   - &fLoreSW:<LoreStartsWith> \n" +
                            "&7   - &fLoreE:<LoreEquals> \n" +
                            "&7   - &fLoreC:<LoreContains> \n" +
                            "&7   - &fLoreCR:<LoreContainsRegex> \n" +
                            "&7   - &fLoreEW:<LoreEndsWith> \n" +
                            "&7   - &fEnchs:<Ench:1|Ench2:1> \n" +
                            "&7   - &fStrict \n"));
            p.sendMessage("");
            p.spigot().sendMessage(getMessage(" &b/ItemManager Rename",
                    "/itemmanager rename ",
                    "\n &fRenames the item in your hand, \n" +
                            " &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Rename <Name> [Player] \n" +
                            " &9Space symbol: &f" + space + " \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Lore Set",
                    "/itemmanager lore set ",
                    "\n &fSets the item's lore in your hand, \n" +
                            " &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Lore Set <Lore> [Player] \n\n" +
                            " &9New line symbol: &f" + newLine + " \n" +
                            " &9Space symbol: &f" + space + " \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Lore Add",
                    "/itemmanager lore set ",
                    "\n &fAdds new line to the current item's lore &fin your \n" +
                            " &fhand, or player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Lore Add [Line] <Lore> [Player] \n" +
                            " &9Line: &fAdds the lore after this line if specified. \n\n" +
                            " &9New line symbol: &f" + newLine + " \n" +
                            " &9Space symbol: &f" + space + " \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Lore Remove",
                    "/itemmanager lore remove ",
                    "\n &fRemoves lore from the item in your hand, \n" +
                            " &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Lore Remove <Line/Lore/ALL> [Player] \n" +
                            " &9Line: &fRemoves this lore line. \n" +
                            " &9Lore: &fRemoves the line that contains this text. \n" +
                            " &9ALL: &fRemoves the whole lore. \n\n" +
                            " &9New line symbol: &f" + newLine + " \n" +
                            " &9Space symbol: &f" + space + " \n"));
            p.sendMessage("");
            p.spigot().sendMessage(getMessage(" &b/ItemManager Enchant",
                    "/itemmanager enchant ",
                    "\n &fEnchants the item in your hand, &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Enchant <Enchantment> <Level> [Player] [Unsafe] \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Enchant Remove",
                    "/itemmanager enchant remove ",
                    "\n &fRemoves the enchantment from your hand, \n" +
                            " &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Enchant Remove <Enchantment> [Player] \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Glow",
                    "/itemmanager glow ",
                    "\n &fMakes the item in your hand glows, or player's \n" +
                            " &fhand if specified. \n\n" +
                            " &9Usage: &f/IM Glow [Player] \n\n" +
                            " &9Note: &fThis command won't hide the other enchantments. \n"));
            p.spigot().sendMessage(getMessage(" &b/ItemManager Glow Remove",
                    "/itemmanager glow remove ",
                    "\n &fRemoves glow effect from your hand, \n" +
                            " &for player's hand if specified. \n\n" +
                            " &9Usage: &f/IM Glow Remove [Player] \n"));
            p.sendMessage("");
            p.sendMessage(color("&7&m                                                "));
            return;
        }
        sender.sendMessage("");
        sender.sendMessage(color("&9ItemManager &fv" + pl.getDescription().getVersion()));
        sender.sendMessage("");
        sender.sendMessage(color("&b/ItemManager Help"));
        sender.sendMessage(color("&b/ItemManager Reload"));
        sender.sendMessage("");
        sender.sendMessage(color("&b/ItemManager Give"));
        sender.sendMessage(color("&b/ItemManager Check"));
        sender.sendMessage(color("&b/ItemManager Remove"));
        sender.sendMessage("");
        sender.sendMessage(color("&b/ItemManager Rename"));
        sender.sendMessage(color("&b/ItemManager Lore Set"));
        sender.sendMessage(color("&b/ItemManager Lore Add"));
        sender.sendMessage(color("&b/ItemManager Lore Remove"));
        sender.sendMessage("");
        sender.sendMessage(color("&b/ItemManager Enchant"));
        sender.sendMessage(color("&b/ItemManager Enchant Remove"));
        sender.sendMessage(color("&b/ItemManager Glow"));
        sender.sendMessage(color("&b/ItemManager Glow Remove"));
        sender.sendMessage("");
    }
}
