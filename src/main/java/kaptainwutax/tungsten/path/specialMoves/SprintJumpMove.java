package kaptainwutax.tungsten.path.specialMoves;

import kaptainwutax.tungsten.TungstenMod;
import kaptainwutax.tungsten.agent.Agent;
import kaptainwutax.tungsten.helpers.DirectionHelper;
import kaptainwutax.tungsten.helpers.DistanceCalculator;
import kaptainwutax.tungsten.path.Node;
import kaptainwutax.tungsten.path.PathInput;
import kaptainwutax.tungsten.path.blockSpaceSearchAssist.BlockNode;
import kaptainwutax.tungsten.render.Color;
import net.minecraft.world.WorldView;

public class SprintJumpMove {

	public static Node generateMove(Node parent, BlockNode nextBlockNode) {
		double cost = 0.08;
		WorldView world = TungstenMod.mc.world;
		Agent agent = parent.agent;
		float desiredYaw = (float) DirectionHelper.calcYawFromVec3d(agent.getPos(), nextBlockNode.getPos(true));
		double distance = DistanceCalculator.getHorizontalEuclideanDistance(agent.getPos(), nextBlockNode.getPos(true));
	    Node newNode = new Node(parent, world, new PathInput(false, false, false, false, false, false, false, parent.agent.pitch, desiredYaw),
	    				new Color(0, 255, 150), parent.cost + 0.1);
		int limit = 0;
        // Run forward to the node
		while (distance > 0.2 && limit < 80) {
//        	RenderHelper.renderNode(newNode);
//        	try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        	limit++;
    		distance = DistanceCalculator.getHorizontalEuclideanDistance(newNode.agent.getPos(), nextBlockNode.getPos(true));
    		desiredYaw = (float) DirectionHelper.calcYawFromVec3d(newNode.agent.getPos(), nextBlockNode.getPos(true));
            newNode = new Node(newNode, world, new PathInput(true, false, false, false, true, false, true, parent.agent.pitch, desiredYaw),
            		new Color(0, 255, 150), newNode.cost + cost);
            
        }
            
        return newNode;
	}

}
