package dk.mrspring.commands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created on 05-10-2015 for ConvenientCommands.
 */
public class CommandGetAttribute extends CommandAttributeBase
{
    public CommandGetAttribute(IAttributeHandler... handlers)
    {
        super(handlers);
    }

    @Override
    public String getCommandFailure(ICommandSender sender)
    {
        return "command.set_attributes.failure";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "command.set_attributes.usage";
    }

    @Override
    public void doFromCommand(ICommandSender sender, EntityPlayerMP player, IAttributeHandler handler, String[] arguments) throws CommandException
    {
        Object result = handler.getAttribute(this, sender, player, arguments);
        if (result != null)
            notifyOperators(sender, this, "command.get_attributes.success", handler.getName(), result.toString());
    }

    @Override
    public String getName()
    {
        return "get";
    }
}
