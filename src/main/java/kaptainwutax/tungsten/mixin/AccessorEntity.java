package kaptainwutax.tungsten.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface AccessorEntity {

	@Accessor
	Vec3d getMovementMultiplier();

	@Accessor
	boolean getFirstUpdate();

    // 'submergedFluidTag' e 'collidedSoftly' não existem na 1.16.5 e foram removidos

}
