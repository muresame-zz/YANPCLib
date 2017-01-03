package com.gmail.lynx7478.yanpclib;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;


public class YANPC {
	
	private static YANPCHandler handler;
	
	private Class<?> npcClass;
	
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
            npcClass = cl;
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
	
	public Entity getEntity()
	{
		Entity ent = null;
		Method method;
		if(npcClass != null)
		{
			try
			{
				method = npcClass.getDeclaredMethod("getEntity", Entity.class);
				Object val = method.invoke(this, 0);
				ent = (Entity) val;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return ent;
	}
	
	
}

class VersionUtils
{
    public static String getVersion()
    {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf(".") + 1);
    }
}
