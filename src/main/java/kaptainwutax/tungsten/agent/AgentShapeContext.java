package kaptainwutax.tungsten.agent;

import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.FlowableFluid; // Importante
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class AgentShapeContext implements ShapeContext {

    private final boolean descending;
    private final double minY;
    private final ItemStack heldItem;

    protected AgentShapeContext(boolean descending, double minY, ItemStack heldItem) {
        this.descending = descending;
        this.minY = minY;
        this.heldItem = heldItem;
    }

    protected AgentShapeContext(Agent agent) {
        this(agent.input.sneaking, agent.box.minY, ItemStack.EMPTY);
    }

    @Override
    public boolean isHolding(Item item) {
        return this.heldItem.getItem() == item;
    }

    @Override
    public boolean isDescending() {
        return this.descending;
    }

    @Override
    public boolean isAbove(VoxelShape shape, BlockPos pos, boolean defaultValue) {
        return this.minY > (double)pos.getY() + shape.getMax(Direction.Axis.Y) - (double)1.0E-5f;
    }

    // Metodo que faltava
    @Override
    public boolean canWalkOnFluid(FluidState state, FlowableFluid fluid) {
        return false; // Implementacao padrao simples
    }

}
