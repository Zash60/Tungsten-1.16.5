package kaptainwutax.tungsten.agent;

import net.minecraft.client.input.Input;
import net.minecraft.util.PlayerInput;

public class AgentInput extends Input {

	private final Agent agent;

	public AgentInput(Agent agent) {
		this.agent = agent;
	}
	
	public void tick(boolean slowDown, float sneakSpeed) {
		this.playerInput = new PlayerInput(
				this.agent.keyForward,
				this.agent.keyBack,
				this.agent.keyLeft,
				this.agent.keyRight,
				this.agent.keyJump,
				this.agent.keySneak,
				this.agent.sprinting
		);
		this.movementForward = this.playerInput.forward() == this.playerInput.backward() ? 0.0f : (this.playerInput.forward() ? 1.0f : -1.0f);
		this.movementSideways = this.playerInput.left() == this.playerInput.right() ? 0.0f : (this.playerInput.left() ? 1.0f : -1.0f);

		if(slowDown) {
			this.movementSideways *= sneakSpeed;
			this.movementForward *= sneakSpeed;
		}
	}

}
