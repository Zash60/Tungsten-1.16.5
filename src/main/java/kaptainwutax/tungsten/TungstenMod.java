package kaptainwutax.tungsten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.lwjgl.glfw.GLFW;

import kaptainwutax.tungsten.path.PathExecutor;
import kaptainwutax.tungsten.world.VoxelWorld;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import kaptainwutax.tungsten.render.Renderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;

public class TungstenMod implements ClientModInitializer {

	public static final String MOD_ID = "tungsten";
//    public static final ModMetadata MOD_META;
//    public static final String NAME;
    
	public static Collection<Renderer> RENDERERS = Collections.synchronizedCollection(new ArrayList<>());
	public static Collection<Renderer> TEST = Collections.synchronizedCollection(new ArrayList<>());
	public static Renderer COLLISION_BOX = null;
	public static Vec3d TARGET = new Vec3d(0.5D, 10.0D, 0.5D);
	public static PathExecutor EXECUTOR = new PathExecutor();
	public static VoxelWorld WORLD;
	public static KeyBinding pauseKeyBinding;
	public static KeyBinding runKeyBinding;
	public static KeyBinding runBlockSearchKeyBinding;
	public static KeyBinding createGoalKeyBinding;
	
	
	static {
//        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
//        NAME = MOD_META.getName();
        //DEV_BUILD = MOD_META.getCustomValue(TungstenMod.MOD_ID + ":devbuild").getAsString();
		
	}

	@Override
	public void onInitializeClient() {
		pauseKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungstenmod.pause", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_P, // The keycode of the key
	            "category.tungstenmod.test" // The translation key of the keybinding's category.
        ));
		runKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungstenmod.run", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_G, // The keycode of the key
	            "category.tungstenmod.test" // The translation key of the keybinding's category.
        ));
		runBlockSearchKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungstenmod.run_block_search", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_J, // The keycode of the key
	            "category.tungstenmod.test.development" // The translation key of the keybinding's category.
        ));
		createGoalKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
	            "key.tungstenmod.create_goal", // The translation key of the keybinding's name
	            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
	            GLFW.GLFW_KEY_H, // The keycode of the key
	            "category.tungstenmod.test" // The translation key of the keybinding's category.
        ));
	}

}
