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

package me.aboodyy.itemmanager.updatechecker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static me.aboodyy.itemmanager.ItemManager.pl;
import static me.aboodyy.itemmanager.utils.Messages.color;

public class SpigotUpdateChecker implements Listener {

    public SpigotUpdateChecker() {
        checkForUpdates();
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    private void checkForUpdates() {
        if (!pl.getConfig().getBoolean("check_updates"))
            return;

        if (isLatest()) {
            pl.getLogger().info(" ");
            pl.getLogger().info("You're using the latest version!");
            pl.getLogger().info(" ");
            return;
        }

        pl.getLogger().warning(" ");
        pl.getLogger().warning("New version is available at:");
        pl.getLogger().warning("https://www.spigotmc.org/resources/70136/");
        pl.getLogger().warning(" ");
        pl.getLogger().warning("Download it to stay up to date!");
        pl.getLogger().warning(" ");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!pl.getConfig().getBoolean("check_updates"))
            return;

        Player p = e.getPlayer();
        if (p.isOp() || p.hasPermission("itemmanager.admin")) {
            p.sendMessage(" ");
            p.sendMessage(color("&9New version of ItemManager is available and can be found here:"));
            p.sendMessage(color("&bhttps://www.spigotmc.org/resources/70136/"));
            p.sendMessage(" ");
        }
    }

    private String getLatestVersion() {
        URL url;
        URLConnection connection;
        BufferedReader bufferedReader;
        String latest;

        try {
            url = new URL("https://api.spigotmc.org/legacy/update.php?resource=70136");
            connection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            latest = bufferedReader.readLine();
        } catch (Exception e) {
            pl.getLogger().warning(" ");
            pl.getLogger().warning("Couldn't check for new updates.");
            pl.getLogger().warning("Current version is " + pl.getDescription().getVersion() + ".");
            pl.getLogger().warning("Check https://www.spigotmc.org/resources/70136/ to stay up to date.");
            pl.getLogger().warning(" ");
            return null;
        }

        return latest;
    }

    private boolean isLatest() {
        String latest = getLatestVersion();
        return pl.getDescription().getVersion().equals(latest);
    }
}
