package dk.mrspring.commands.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import java.util.Collection;

/**
 * Created on 04-10-2015 for TheKitchenMod.
 */
public class AttributeHelpHandler implements IAttributeHandler
{
    @Override
    public String getName()
    {
        return "help";
    }

    @Override
    public String getSetDescription(ICommandSender sender)
    {
        return "Lists all attributes that can be set";
    }

    @Override
    public String getGetDescription(ICommandSender sender)
    {
        return "Lists all attributes that can be gotten";
    }

    @Override
    public boolean setAttribute(CommandSetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        doStuff(command, sender, player, arguments);
        return false;
    }

    @Override
    public Object getAttribute(CommandGetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        doStuff(command, sender, player, arguments);
        return null;
    }

    private void doStuff(CommandAttributeBase command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        Collection<IAttributeHandler> handlers = command.listHandlers();
        sender.addChatMessage(new ChatComponentText("Attributes: "));
        for (IAttributeHandler handler : handlers)
            if (handler != this)
                sender.addChatMessage(new ChatComponentText("- " + handler.getName() + ": " + handler.getSetDescription(sender)));
    }
}
