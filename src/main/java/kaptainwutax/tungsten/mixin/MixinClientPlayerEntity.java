package kaptainwutax.tungsten.mixin;

import com.mojang.authlib.GameProfile;
import kaptainwutax.tungsten.TungstenMod;
import kaptainwutax.tungsten.agent.Agent;
import kaptainwutax.tungsten.path.PathFinder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

	public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	public void start(CallbackInfo ci) {
		if(TungstenMod.EXECUTOR.isRunning()) {
            // Chamada atualizada sem o segundo argumento
			TungstenMod.EXECUTOR.tick((ClientPlayerEntity)(Object)this);
		}

		if(!this.abilities.flying) {
			Agent.INSTANCE = Agent.of((ClientPlayerEntity)(Object)this);
			Agent.INSTANCE.tick(this.world);
		}

		if(MinecraftClient.getInstance().options.keySwapHands.isPressed() && !PathFinder.active) {
			PathFinder.find(this.world, TungstenMod.TARGET);
		}
		if (MinecraftClient.getInstance().options.keySocialInteractions.isPressed() && PathFinder.thread != null && PathFinder.thread.isAlive()) {
			PathFinder.thread.interrupt();
		}
	}

	@Inject(method = "tick", at = @At(value = "RETURN"))
	public void end(CallbackInfo ci) {
		if(!this.abilities.flying && Agent.INSTANCE != null) {
			Agent.INSTANCE.compare((ClientPlayerEntity)(Object)this, false);
		}
	}

	@Inject(method="getPitch", at=@At("RETURN"), cancellable = true)
	public void getPitch(float tickDelta, CallbackInfoReturnable<Float> ci) {
		if(TungstenMod.EXECUTOR.isRunning()) {
			ci.setReturnValue(super.getPitch(tickDelta));
		}
	}

	@Inject(method="getYaw", at=@At("RETURN"), cancellable = true)
	public void getYaw(float tickDelta, CallbackInfoReturnable<Float> ci) {
		if(TungstenMod.EXECUTOR.isRunning()) {
			ci.setReturnValue(super.getYaw(tickDelta));
		}
	}
}
