package com.gmail.lynx7478.yanpclib;

import net.minecraft.server.v1_10_R1.EntityPlayer;

import org.bukkit.Location;

public interface YANPCHandler {
	
	void spawn(Location loc, String name);
	
	void destroy();

}
