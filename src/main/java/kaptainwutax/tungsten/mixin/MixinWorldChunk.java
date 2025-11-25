package kaptainwutax.tungsten.mixin;

import kaptainwutax.tungsten.TungstenMod;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundTag; // Na 1.16.5 yarn é CompoundTag
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldChunk.class)
public abstract class MixinWorldChunk {

	@Shadow public abstract World getWorld();
	@Shadow public abstract BlockState getBlockState(BlockPos pos);
	@Shadow public abstract FluidState getFluidState(BlockPos pos);

	@Inject(method = "loadFromPacket", at = @At("RETURN"))
	private void loadFromPacket(@Nullable BiomeArray biomes, PacketByteBuf buf, CompoundTag nbt, int verticalStripBitmask, CallbackInfo ci) {
		if(TungstenMod.WORLD == null || this.getWorld() != TungstenMod.WORLD.parent) {
			return;
		}
	}

}
