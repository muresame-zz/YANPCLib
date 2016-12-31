package com.gmail.lynx7478.yanpclib.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.lynx7478.yanpclib.YANPC;

public class NPCCommand implements CommandExecutor {
	
	private boolean npcSpawned;
	private YANPC yanpc;

	@Override
	public boolean onCommand(CommandSender sender, Command npc, String s, String[] args) {
		if(npc.getName().equalsIgnoreCase("npc"))
		{
			if(!(sender instanceof Player))
			{
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Only players can run this command, sorry!");
				return false;
			}
			Player p = (Player) sender;
			if(!npcSpawned)
			{
				yanpc = new YANPC();
				yanpc.spawn(p.getLocation(), "Notch");
				npcSpawned = true;
			}else
			{
				npcSpawned = false;
				yanpc.destroy();
				yanpc = null;
			}
		}
		return false;
	}

}
