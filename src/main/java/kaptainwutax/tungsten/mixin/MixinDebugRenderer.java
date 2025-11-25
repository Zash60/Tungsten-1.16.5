package kaptainwutax.tungsten.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import kaptainwutax.tungsten.TungstenMod;
import kaptainwutax.tungsten.render.Color;
import kaptainwutax.tungsten.render.Cuboid;
import net.minecraft.client.render.*;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRenderer.class)
public class MixinDebugRenderer {

    @Inject(method = "render", at = @At("RETURN"))
    public void render(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        RenderSystem.disableDepthTest();
        // 1.16 doesn't use setShader here, just disable texture for colored lines
        RenderSystem.disableTexture();
        RenderSystem.disableBlend();
        RenderSystem.lineWidth(2.0F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        // 1.16.5 format
        buffer.begin(GL11.GL_LINE_STRIP, VertexFormats.POSITION_COLOR);
        Cuboid goal = new Cuboid(TungstenMod.TARGET.subtract(0.5D, 0D, 0.5D), new Vec3d(1.0D, 2.0D, 1.0D), Color.GREEN);
        goal.render();
        tessellator.draw();

        TungstenMod.RENDERERS.forEach(r -> {
            buffer.begin(GL11.GL_LINE_STRIP, VertexFormats.POSITION_COLOR);
            r.render();
            tessellator.draw();
        });
        
        TungstenMod.TEST.forEach(r -> {
            buffer.begin(GL11.GL_LINE_STRIP, VertexFormats.POSITION_COLOR);
            r.render();
            tessellator.draw();
        });

        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
    }
}
