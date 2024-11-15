package kaptainwutax.tungsten;

import kaptainwutax.tungsten.commands.*;
import kaptainwutax.tungsten.commandsystem.CommandException;

public class TungstenCommands {

	public TungstenCommands(TungstenMod mod) throws CommandException {
		TungstenMod.getCommandExecutor().registerNewCommand(
				new GotoCommand(mod),
				new StopCommand(mod)
		);
	}
}
