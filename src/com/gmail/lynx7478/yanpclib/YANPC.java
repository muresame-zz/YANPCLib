package com.gmail.lynx7478.yanpclib;

import org.bukkit.Location;

import com.gmail.lynx7478.yanpclib.utils.VersionUtils;


public class YANPC {
	
	private static YANPCHandler handler;
	
	public YANPC()
	{
		handler = null;
        try
        {
            String version = VersionUtils.getVersion();
            String className = "com.gmail.lynx7478.yanpclib.versions.NPC"+version;
            Class<?> cl = Class.forName(className);
            Class<? extends YANPCHandler> pack = cl.asSubclass(YANPCHandler.class);
            YANPCHandler p = pack.newInstance();
            handler = p;
        }
        catch(Throwable t)
        {
        	//TODO: Post a message.
        }
	}
	
	public void spawn(Location loc, String name)
	{
		handler.spawn(loc, name);
	}
	
	public void destroy()
	{
		handler.destroy();
	}
	
	
}
