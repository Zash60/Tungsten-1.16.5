package kaptainwutax.tungsten.path;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
// Import removido para evitar erro de pacote
// import net.minecraft.client.options.GameOptions; 

import java.util.List;

public class PathExecutor {

    protected List<Node> path;
    protected int tick = 0;

    public PathExecutor() {}

    public void setPath(List<Node> path) {
        this.path = path;
        this.tick = 0;
    }

    public boolean isRunning() {
        return this.path != null && this.tick <= this.path.size();
    }

    public void tick(ClientPlayerEntity player) {
        // Acessa as opcoes diretamente sem declarar a variavel 'GameOptions'
        // para evitar problemas de importacao (singular vs plural em versoes diferentes)
        if(MinecraftClient.getInstance().options.keySocialInteractions.isPressed()) {
            this.tick = this.path.size();
        }
        
        if(this.tick == this.path.size()) {
            MinecraftClient.getInstance().options.keyForward.setPressed(false);
            MinecraftClient.getInstance().options.keyBack.setPressed(false);
            MinecraftClient.getInstance().options.keyLeft.setPressed(false);
            MinecraftClient.getInstance().options.keyRight.setPressed(false);
            MinecraftClient.getInstance().options.keyJump.setPressed(false);
            MinecraftClient.getInstance().options.keySneak.setPressed(false);
            MinecraftClient.getInstance().options.keySprint.setPressed(false);
        } else {
            Node node = this.path.get(this.tick);

            if(this.tick != 0) {
                this.path.get(this.tick - 1).agent.compare(player, true);
            }

            if(node.input != null) {
                if (player.isFallFlying()) {
                    // Logica de elytra
                }
                MinecraftClient.getInstance().options.keyForward.setPressed(node.input.forward);
                MinecraftClient.getInstance().options.keyBack.setPressed(node.input.back);
                MinecraftClient.getInstance().options.keyLeft.setPressed(node.input.left);
                MinecraftClient.getInstance().options.keyRight.setPressed(node.input.right);
                MinecraftClient.getInstance().options.keyJump.setPressed(node.input.jump);
                MinecraftClient.getInstance().options.keySneak.setPressed(node.input.sneak);
                MinecraftClient.getInstance().options.keySprint.setPressed(node.input.sprint);
                
                player.prevYaw = player.yaw;
                player.prevPitch = player.pitch;
                player.yaw = node.input.yaw;
                player.pitch = node.input.pitch;
            }
        }

        this.tick++;
    }

}
