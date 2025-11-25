package kaptainwutax.tungsten.path;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
// Import removido para evitar erro de pacote options/option
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

    // Removido argumento GameOptions para evitar erro de import
    public void tick(ClientPlayerEntity player) {
        // Pegamos as opcoes aqui dentro
        var options = MinecraftClient.getInstance().options;

        if(options.keySocialInteractions.isPressed()) {
            this.tick = this.path.size();
        }
        if(this.tick == this.path.size()) {
            options.keyForward.setPressed(false);
            options.keyBack.setPressed(false);
            options.keyLeft.setPressed(false);
            options.keyRight.setPressed(false);
            options.keyJump.setPressed(false);
            options.keySneak.setPressed(false);
            options.keySprint.setPressed(false);
        } else {
            Node node = this.path.get(this.tick);

            if(this.tick != 0) {
                this.path.get(this.tick - 1).agent.compare(player, true);
            }

            if(node.input != null) {
                if (player.isFallFlying()) {
                    // Logica de elytra
                }
                options.keyForward.setPressed(node.input.forward);
                options.keyBack.setPressed(node.input.back);
                options.keyLeft.setPressed(node.input.left);
                options.keyRight.setPressed(node.input.right);
                options.keyJump.setPressed(node.input.jump);
                options.keySneak.setPressed(node.input.sneak);
                options.keySprint.setPressed(node.input.sprint);
                player.prevYaw = player.yaw;
                player.prevPitch = player.pitch;
                player.yaw = node.input.yaw;
                player.pitch = node.input.pitch;
            }
        }

        this.tick++;
    }

}
