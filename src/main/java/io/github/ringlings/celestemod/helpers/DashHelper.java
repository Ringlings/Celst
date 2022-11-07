package io.github.ringlings.celestemod.helpers;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class DashHelper {

	static int currentDashes = 0;
	static int maxDashes;

	static int coolDown = 0;

	public static void dash(ClientPlayerEntity PLAYER) {
		if (currentDashes == 0 || coolDown > 0) {
			return;
		}

		float pitch = PLAYER.getPitch();
		float yaw = PLAYER.getYaw();
		float roll = PLAYER.getRoll();

		float x = ((-MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0))));
		float y = ((-MathHelper.sin((pitch + roll) * (float) (Math.PI / 180.0))));
		float z = ((MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0))));

		PLAYER.setVelocity(x, y, z);
		PLAYER.setNoGravity(true);
		PLAYER.setNoDrag(true);

		currentDashes--;
		coolDown = 10;
	}

	public static void updateDashCount(ClientPlayerEntity PLAYER, int MAX_DASHES) {
		maxDashes = MAX_DASHES;
		if (PLAYER.isOnGround()) {
			currentDashes = maxDashes;
		}

		if (coolDown > 0) {
			coolDown--;
		}

		if (coolDown == 0) {
			PLAYER.setNoGravity(false);
			PLAYER.setNoDrag(false);
			Vec3d vel = PLAYER.getVelocity();
			PLAYER.setVelocity(vel.multiply(0.75));
			coolDown = -1;
		}
	}
}