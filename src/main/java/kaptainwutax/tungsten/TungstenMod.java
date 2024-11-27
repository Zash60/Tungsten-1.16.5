package kaptainwutax.tungsten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kaptainwutax.tungsten.commandsystem.CommandExecutor;
import kaptainwutax.tungsten.path.PathExecutor;
import kaptainwutax.tungsten.path.PathFinder;
import kaptainwutax.tungsten.render.Renderer;
import kaptainwutax.tungsten.world.VoxelWorld;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;

public class TungstenMod implements ClientModInitializer {

	public static final String MOD_ID = "tungsten";
//    public static final ModMetadata MOD_META;
    public static final String NAME;

    public static MinecraftClient mc;
	public static Collection<Renderer> RENDERERS = Collections.synchronizedCollection(new ArrayList<>());
	public static Collection<Renderer> TEST = Collections.synchronizedCollection(new ArrayList<>());
	public static Vec3d TARGET = new Vec3d(0.5D, 10.0D, 0.5D);
	public static PathExecutor EXECUTOR = new PathExecutor();
	public static PathFinder PATHFINDER = new PathFinder();
	public static final Logger LOG;
	public static VoxelWorld WORLD;
	public static KeyBinding pauseKeyBinding;
	public static KeyBinding runKeyBinding;
	public static KeyBinding runBlockSearchKeyBinding;
	public static KeyBinding createGoalKeyBinding;
    private static CommandExecutor _commandExecutor;
	
	
	static {
//        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
        NAME = "Tungsten";
        //DEV_BUILD = MOD_META.getCustomValue(TungstenMod.MOD_ID + ":devbuild").getAsString();
        LOG = LoggerFactory.getLogger(NAME);
		
	}

	@Override
	public void onInitializeClient() {
		pauseKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungsten.pause", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_P, // The keycode of the key
	            "key.category.tungsten.test" // The translation key of the keybinding's category.
        ));
		runKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungsten.run", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_G, // The keycode of the key
	            "key.category.tungsten.test" // The translation key of the keybinding's category.
        ));
		runBlockSearchKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungsten.run_block_search", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_J, // The keycode of the key
	            "key.category.tungsten.test.development" // The translation key of the keybinding's category.
        ));
		createGoalKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungsten.create_goal", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_H, // The keycode of the key
	            "key.category.tungsten.test" // The translation key of the keybinding's category.
        ));
        _commandExecutor = new CommandExecutor(this);

        // Global minecraft client accessor
        mc = MinecraftClient.getInstance();

        initializeCommands();
	}
	
	 public static String getCommandPrefix() {
		 return ";";
	 }
	 
	// List all command sources here.
    private void initializeCommands() {
        try {
            // This creates the commands. If you want any more commands feel free to initialize new command lists.
            new TungstenCommands(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 
	 /**
     * Executes commands
     */
    public static CommandExecutor getCommandExecutor() {
        return _commandExecutor;
    }

}
