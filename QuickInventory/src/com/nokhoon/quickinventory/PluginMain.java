package com.nokhoon.quickinventory;

import java.util.Collections;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.audience.Audience;

public class PluginMain extends JavaPlugin {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Audience audience = (Audience) sender;
		switch(label.toLowerCase()) {
		case "ct" -> {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.openWorkbench(null, true) == null)
					audience.sendMessage(PluginConstants.error("제작 메뉴를 열지 못했습니다."));
			}
			else audience.sendMessage(PluginConstants.PLAYER_COMMAND);
		}
		case "ec" -> {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);
				if(player.openInventory(player.getEnderChest()) == null)
					audience.sendMessage(PluginConstants.error("엔더 상자를 열지 못했습니다."));
			}
			else audience.sendMessage(PluginConstants.PLAYER_COMMAND);
		}
		default -> { return false; }
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(command.getLabel().equals("ct") || command.getLabel().equals("ec")) return Collections.emptyList();
		return super.onTabComplete(sender, command, alias, args);
	}
}
