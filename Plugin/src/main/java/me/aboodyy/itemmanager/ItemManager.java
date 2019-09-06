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

package me.aboodyy.itemmanager;

import co.aikar.commands.*;
import me.aboodyy.itemmanager.commands.*;
import me.aboodyy.itemmanager.commands.enchantcommands.*;
import me.aboodyy.itemmanager.commands.inventorycommands.*;
import me.aboodyy.itemmanager.commands.metacommands.lorecommands.*;
import me.aboodyy.itemmanager.commands.metacommands.RenameCommand;
import me.aboodyy.itemmanager.updatechecker.SpigotUpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.aboodyy.itemmanager.utils.Enchantments.registerGlow;
import static org.bukkit.Material.LEGACY_PREFIX;

public class ItemManager extends JavaPlugin {

    public static ItemManager pl;

    public boolean isLegacy;

    public void onEnable() {
        pl = this;
        isLegacy = !Bukkit.getBukkitVersion().contains("1.13") && !Bukkit.getBukkitVersion().contains("1.14");

        SpigotUpdateChecker updateChecker = new SpigotUpdateChecker();

        Metrics metrics = new Metrics(this);
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        registerGlow();

        if (this.getConfig().getString("symbols.space").contains(" ")) {
            this.getConfig().set("symbols.space", "_");
            this.saveConfig();
            this.getLogger().info("Space symbol cannot contains a space. Default value (_) has been set.");
        }
        if (this.getConfig().getString("symbols.new_line").contains(" ")) {
            this.getConfig().set("symbols.new_line", "|");
            this.saveConfig();
            this.getLogger().info("New line symbol cannot contains a space. Default value (|) has been set.");
        }

        List<String> enchantments = new ArrayList<>();
        List<String> materials = new ArrayList<>();

        for (Enchantment ench : Enchantment.values()) {
            if (ench != null && !ench.toString().equals("9909")) {
                if (isLegacy) {
                    enchantments.add(ench.getName().toLowerCase());
                    continue;
                }
                enchantments.add(ench.getKey().getKey());
            }
        }
        for (Material mat : Material.values()) {
            if (mat == Material.AIR) continue;
            if (mat.toString().contains(LEGACY_PREFIX)) continue;

            materials.add(mat.toString().toLowerCase());
        }
        Collections.sort(enchantments);
        Collections.sort(materials);

        BukkitCommandManager cmdManager = new BukkitCommandManager(this);

        cmdManager.getCommandCompletions().registerCompletion("enchantments", c -> enchantments);
        cmdManager.getCommandCompletions().registerCompletion("materials", c -> materials);
        cmdManager.getCommandCompletions().registerCompletion("gmodifiers", c ->
                Arrays.asList("amt:", "data:", "name:", "lore:", "enchs:", "unsafe")
        );
        cmdManager.getCommandCompletions().registerCompletion("modifiers", c ->
                Arrays.asList("mat:", "amt:", "data:", "nameSW:", "nameE:", "nameER:", "nameC:", "nameCR:",
                        "nameEW:", "loreSW:", "loreE:", "loreC:", "loreCR:", "loreEW:", "enchs:", "strict")
        );
        cmdManager.getCommandCompletions().registerCompletion("lremove", c ->
                Arrays.asList("<lore>", "<line>", "ALL")
        );

        cmdManager.registerCommand(new ItemManagerCommand());
        cmdManager.registerCommand(new RenameCommand());
        cmdManager.registerCommand(new ReloadCommand());
        cmdManager.registerCommand(new SetCommand());
        cmdManager.registerCommand(new AddCommand());
        cmdManager.registerCommand(new LoreRemoveCommand());
        cmdManager.registerCommand(new GlowCommand());
        cmdManager.registerCommand(new GlowRemoveCommand());
        cmdManager.registerCommand(new EnchantCommand());
        cmdManager.registerCommand(new EnchantRemoveCommand());
        cmdManager.registerCommand(new CheckCommand());
        cmdManager.registerCommand(new ItemRemoveCommand());
        cmdManager.registerCommand(new GiveCommand());

    }
}
