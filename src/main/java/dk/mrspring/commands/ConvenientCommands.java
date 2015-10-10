package dk.mrspring.commands;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import dk.mrspring.commands.command.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 05-10-2015 for ConvenientCommands.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ConvenientCommands
{
    private static List<IAttributeHandler> ATTRIBUTE_HANDLERS = new ArrayList<IAttributeHandler>();

    public static void registerAttributeHandler(IAttributeHandler handler)
    {
        if (handler != null && !ATTRIBUTE_HANDLERS.contains(handler))
            ATTRIBUTE_HANDLERS.add(handler);
    }

    public static IAttributeHandler[] getRegisteredAttributeHandler()
    {
        return ATTRIBUTE_HANDLERS.toArray(new IAttributeHandler[ATTRIBUTE_HANDLERS.size()]);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        registerAttributeHandler(new AttributeHelpHandler());
        registerAttributeHandler(new AttributeHealthHandler());
        registerAttributeHandler(new AttributeHungerHandler());
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSetAttribute(getRegisteredAttributeHandler()));
        event.registerServerCommand(new CommandGetAttribute(getRegisteredAttributeHandler()));
    }
}
