package dk.mrspring.commands.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created on 05-10-2015 for ConvenientCommands.
 */
public class CommandSetAttribute extends CommandAttributeBase
{
    public CommandSetAttribute(IAttributeHandler... handlers)
    {
        super(handlers);
    }

    @Override
    public String getCommandFailure(ICommandSender sender)
    {
        return "command.set_attributes.usage";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "command.set_attributes.failure";
    }

    @Override
    public void doFromCommand(ICommandSender sender, EntityPlayerMP player, IAttributeHandler handler, String[] arguments)
    {
        if (handler.setAttribute(this, sender, player, arguments))
            func_152373_a(sender, this, "command.set_attributes.success", handler.getName());
    }

    @Override
    public String getCommandName()
    {
        return "set";
    }
}
