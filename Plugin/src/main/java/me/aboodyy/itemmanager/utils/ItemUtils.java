package me.aboodyy.itemmanager.utils;

import me.aboodyy.itemmanager.ItemWrapper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.aboodyy.itemmanager.utils.Enchantments.getEnchantment;
import static me.aboodyy.itemmanager.utils.Messages.color;
import static me.aboodyy.itemmanager.utils.Utils.*;
import static org.bukkit.Material.matchMaterial;

public class ItemUtils {

    public static ItemStack getItemInHand(Player p) {
        try {
            return p.getInventory().getItemInMainHand();
        } catch (NoSuchMethodError e) {
            return p.getItemInHand();
        }
    }

    public static List<Integer> getItemSlots(Player p, ItemWrapper w) {
        String space = getConfig().getString("symbols.space", "_");

        List<Integer> slots = new ArrayList<>();
        boolean matchMat = false, matchData, matchEnchs = false,

                matchNameSW = false, matchNameE = false, matchNameER = false,
                matchNameC = false, matchNameCR = false, matchNameEW = false,

                matchLoreSW = false, matchLoreE = false, matchLoreC = false,
                matchLoreCR = false, matchLoreEW = false;

        ItemStack[] items = p.getInventory().getContents();
        ItemStack is;
        ItemMeta meta;

        for (int i = 0; i < items.length; i++) {
            is = items[i];
            if (is == null || is.getType() == Material.AIR) continue;
            meta = is.getItemMeta();

            if (w.matExists()) matchMat = is.getType() == matchMaterial(w.getMat());

            if (w.isStrict())
                matchData = is.getDurability() == w.getData();
            else {
                if (w.matExists() && matchMaterial(w.getMat()).getMaxDurability() == 0)
                    matchData = is.getDurability() == w.getData();
                else
                    matchData = is.getDurability() <= w.getData();
            }

            if (w.enchsExist()) {
                String[] parts;
                int matches = 0;
                for (String ench : w.getEnchs()) {
                    parts = ench.split(":");
                    if (w.isStrict()) {
                        if (is.getEnchantmentLevel(getEnchantment(parts[0])) == Integer.parseInt(parts[1])) matches++;
                    } else {
                        if (is.getEnchantmentLevel(getEnchantment(parts[0])) >= Integer.parseInt(parts[1])) matches++;
                    }
                }
                matchEnchs = matches == w.getEnchs().length;
            }

            if (is.hasItemMeta() && meta.hasDisplayName()) {
                if (w.nameSWExists())
                    matchNameSW = meta.getDisplayName().startsWith(color(w.getNameSW().replace(space, " ")));
                if (w.nameEExists())
                    matchNameE = meta.getDisplayName().equals(color(w.getNameE().replace(space, " ")));
                if (w.nameERExists())
                    matchNameER = meta.getDisplayName().matches(w.getNameER());
                if (w.nameCExists())
                    matchNameC = meta.getDisplayName().contains(color(w.getNameC().replace(space, " ")));
                if (w.nameCRExists())
                    matchNameCR = contains(meta.getDisplayName(), w.getNameCR());
                if (w.nameEWExists())
                    matchNameEW = meta.getDisplayName().endsWith(color(w.getNameEW().replace(space, " ")));
            }

            if (is.hasItemMeta() && meta.hasLore()) {
                if (w.loreSWExists()) matchLoreSW = startsWith(meta.getLore(), w.getLoreSW());
                if (w.loreEExists()) matchLoreE = Utils.equals(meta.getLore(), w.getLoreE());
                if (w.loreCExists()) matchLoreC = contains(meta.getLore(), w.getLoreC());
                if (w.loreCRExists()) matchLoreCR = containsR(meta.getLore(), w.getLoreCR());
                if (w.loreEWExists()) matchLoreEW = endsWith(meta.getLore(), w.getLoreEW());
            }

            if (w.matExists() && !matchMat) continue;
            if (!matchData) continue;

            if (w.isStrict()) {
                if (!w.enchsExist() && is.hasItemMeta() && meta.hasEnchants()) continue;

                if (!w.nameSWExists() && !w.nameEExists() && !w.nameERExists() &&
                        !w.nameCExists() && !w.nameCRExists() && !w.nameEWExists() &&
                        is.hasItemMeta() && meta.hasDisplayName()) continue;

                if (!w.loreSWExists() && !w.loreEExists() && !w.loreCExists() &&
                        !w.loreCRExists() && !w.loreEWExists() && is.hasItemMeta() && meta.hasLore()) continue;
            }

            if (w.enchsExist() && !matchEnchs) continue;

            if (w.nameSWExists() && !matchNameSW) continue;
            if (w.nameEExists() && !matchNameE) continue;
            if (w.nameERExists() && !matchNameER) continue;
            if (w.nameCExists() && !matchNameC) continue;
            if (w.nameCRExists() && !matchNameCR) continue;
            if (w.nameEWExists() && !matchNameEW) continue;

            if (w.loreSWExists() && !matchLoreSW) continue;
            if (w.loreEExists() && !matchLoreE) continue;
            if (w.loreCExists() && !matchLoreC) continue;
            if (w.loreCRExists() && !matchLoreCR) continue;
            if (w.loreEWExists() && !matchLoreEW) continue;

            slots.add(i);
        }

        int total = 0;
        for (int i : slots) total += p.getInventory().getItem(i).getAmount();

        if (total < w.getAmt()) return null;

        return slots;
    }

}
