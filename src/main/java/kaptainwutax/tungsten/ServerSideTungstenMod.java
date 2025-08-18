package kaptainwutax.tungsten;

import io.github.hackerokuz.FakePlayerAPIMod;
import io.github.hackerokuz.fakes.OurFakePlayer;
import kaptainwutax.tungsten.agent.Agent;
import kaptainwutax.tungsten.path.PathExecutor;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ServerSideTungstenMod implements DedicatedServerModInitializer {

	public OurFakePlayer player;
	
	public void onInitializeServer() {
		
		if (!FabricLoader.getInstance().isModLoaded("fakeplayerapi")) return;
		
		TungstenModDataContainer.EXECUTOR = new PathExecutor(false);

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            dispatcher.register(CommandManager.literal("summonTungsten")
                    .executes(context -> {
                        return this.summonFakePlayer(context.getSource());
                    }));
            
            dispatcher.register(CommandManager.literal("come")
                    .executes(context -> {
                    	
                    	TungstenModDataContainer.PATHFINDER.find(context.getSource().getWorld(), context.getSource().getPosition(), player);
                        
                        return 1;
                    }));
        });
	}
	
	private int summonFakePlayer(ServerCommandSource source) {
		
		TungstenModDataContainer.world = source.getWorld();
		
		player = FakePlayerAPIMod.createFakePlayer(source.getWorld(), source.getServer(), source.getPlayer(), this::tick);

		
		
        TungstenModDataContainer.player = source.getPlayer();
		return 1;
	}
	
	private void tick() {
		Agent.INSTANCE = Agent.of(player);
		Agent.INSTANCE.tick(player.getWorld());
		if (TungstenModDataContainer.EXECUTOR.getPath() != null) TungstenModDataContainer.EXECUTOR.tick(player);
	}
	
}
