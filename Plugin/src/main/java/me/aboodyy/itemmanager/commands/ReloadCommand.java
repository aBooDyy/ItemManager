package me.aboodyy.itemmanager.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

import static me.aboodyy.itemmanager.ItemManager.pl;
import static me.aboodyy.itemmanager.utils.Messages.color;

@CommandAlias("itemmanager|imanager|im")
public class ReloadCommand extends ItemManagerCommand {

    @Subcommand("reload|r|rl")
    @CommandPermission("itemmanager.reload")
    public void onReload(CommandSender sender) {
        pl.reloadConfig();
        sender.sendMessage(color("&aConfig file has been successfully reloaded."));
    }

}
