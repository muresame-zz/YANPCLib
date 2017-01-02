package com.gmail.lynx7478.yanpclib.test;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.lynx7478.yanpclib.test.commands.*;

public class YANPCLib extends JavaPlugin {
	
	//TODO: Mostly, this class is only for testing, it shouldn't be put in as a plugin, the aim is for it to be an open source simple to use library
	// that you put into your project.
	@Override
	public void onEnable()
	{
		this.getCommand("npc").setExecutor(new NPCCommand());
	}

}
