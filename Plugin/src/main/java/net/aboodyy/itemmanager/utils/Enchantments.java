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

package net.aboodyy.itemmanager.utils;

import net.aboodyy.itemmanager.ItemManager;
import net.aboodyy.itemmanager.legacy.LegacyGlowEnchantment;
import net.aboodyy.itemmanager.v1_13plus.GlowEnchantment;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;

public class Enchantments {

    public static Enchantment getEnchantment(String name) {

        switch (name.toLowerCase()) {
            case "arrow_damage":
            case "power":
                return Enchantment.ARROW_DAMAGE;
            case "arrow_fire":
            case "flame":
                return Enchantment.ARROW_FIRE;
            case "arrow_infinite":
            case "infinity":
                return Enchantment.ARROW_INFINITE;
            case "arrow_knockback":
            case "punch":
                return Enchantment.ARROW_KNOCKBACK;
            case "binding_curse":
            case "curse_of_binding":
            case "curseofbinding":
            case "binding":
                return Enchantment.BINDING_CURSE;
            case "channeling":
                return Enchantment.CHANNELING;
            case "damage_all":
            case "sharpness":
            case "sharp":
                return Enchantment.DAMAGE_ALL;
            case "damage_arthropods":
            case "bane":
            case "bane_of_arthropods":
            case "baneofarthropods":
                return Enchantment.DAMAGE_ARTHROPODS;
            case "damage_undead":
            case "smite":
                return Enchantment.DAMAGE_UNDEAD;
            case "depth_strider":
            case "depthstrider":
            case "depth":
            case "strider":
                return Enchantment.DEPTH_STRIDER;
            case "dig_speed":
            case "digspeed":
            case "efficiency":
            case "effi":
                return Enchantment.DIG_SPEED;
            case "durability":
            case "unbreaking":
            case "unb":
                return Enchantment.DURABILITY;
            case "fire_aspect":
            case "fireaspect":
            case "fire":
                return Enchantment.FIRE_ASPECT;
            case "frost_walker":
            case "frostwalker":
            case "walker":
                return Enchantment.FROST_WALKER;
            case "impaling":
                return Enchantment.IMPALING;
            case "knockback":
                return Enchantment.KNOCKBACK;
            case "loot_bonus_blocks":
            case "fortune":
                return Enchantment.LOOT_BONUS_BLOCKS;
            case "loot_bonus_mobs":
            case "looting":
                return Enchantment.LOOT_BONUS_MOBS;
            case "loyalty":
                return Enchantment.LOYALTY;
            case "luck":
                return Enchantment.LUCK;
            case "lure":
                return Enchantment.LURE;
            case "mending":
                return Enchantment.MENDING;
            case "multishot":
                return Enchantment.MULTISHOT;
            case "oxygen":
            case "respiration":
            case "resp":
                return Enchantment.OXYGEN;
            case "piercing":
                return Enchantment.PIERCING;
            case "protection_environmental":
            case "protection":
            case "pro":
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case "protection_explosions":
            case "blast_protection":
            case "blastprotection":
            case "blastpro":
                return Enchantment.PROTECTION_EXPLOSIONS;
            case "protection_fall":
            case "feather_falling":
            case "featherfalling":
            case "featherfall":
            case "fall":
                return Enchantment.PROTECTION_FALL;
            case "protection_fire":
            case "fire_protection":
            case "fireprotection":
            case "firepro":
                return Enchantment.PROTECTION_FIRE;
            case "projectile_protection":
            case "projectileprotection":
            case "projpro":
                return Enchantment.PROTECTION_PROJECTILE;
            case "quick_charge":
            case "quickcharge":
                return Enchantment.QUICK_CHARGE;
            case "riptide":
                return Enchantment.RIPTIDE;
            case "silk_touch":
            case "silktouch":
            case "silk":
                return Enchantment.SILK_TOUCH;
            case "sweeping_edge":
            case "sweepingedge":
            case "sweeping":
                return Enchantment.SWEEPING_EDGE;
            case "thorns":
                return Enchantment.THORNS;
            case "vanishing_curse":
            case "curse_of_vanishing":
            case "curseofvanishing":
            case "vanishing":
            case "vanish":
                return Enchantment.VANISHING_CURSE;
            case "water_worker":
            case "aqua_affinity":
            case "aquaaffinity":
            case "aqua":
                return Enchantment.WATER_WORKER;
        }

        return null;
    }

    public static void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Enchantment.registerEnchantment(getGlowEnchantment());
            ItemManager.pl.getLogger().info("Glow effect has been registered successfully.");
        }
        catch (IllegalArgumentException e) {
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Enchantment getGlowEnchantment() {
        if (!ItemManager.pl.isLegacy)
            return new GlowEnchantment("9909");

        return new LegacyGlowEnchantment(9909);
    }

}
