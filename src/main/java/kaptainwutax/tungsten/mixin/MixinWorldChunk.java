package kaptainwutax.tungsten.mixin;

import kaptainwutax.tungsten.TungstenMod;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(WorldChunk.class)
public abstract class MixinWorldChunk extends Chunk {

    // 1.16.5 constructor signature
	public MixinWorldChunk(World world, ChunkPos pos, Biome[] biomes, UpgradeData upgradeData, @Nullable long[] inhabitedTime, @Nullable ChunkSection[] sections, @Nullable Consumer<WorldChunk> consumer) {
		super(pos, biomes, upgradeData, world, inhabitedTime, sections, consumer);
	}

	@Shadow public abstract World getWorld();
	@Shadow public abstract BlockState getBlockState(BlockPos pos);
	@Shadow public abstract FluidState getFluidState(BlockPos pos);

	@Inject(method = "loadFromPacket", at = @At("RETURN"))
	private void loadFromPacket(@Nullable Biome[] biomes, PacketByteBuf buf, CompoundTag nbt, int verticalStripBitmask, CallbackInfo ci) {
		if(TungstenMod.WORLD == null || this.getWorld() != TungstenMod.WORLD.parent) {
			return;
		}
        // Logic remains commented out as per source
	}
}
