package de.mxscha.en.endoflife.utils.manager.region;

import org.bukkit.Location;

public class Region {

    public boolean isIn(Location playerLocation, Location locationA, Location locationB) {
        Double maxX = (locationA.getX() > locationB.getX() ? locationA.getX() : locationB.getX());
        Double minX = (locationA.getX() < locationB.getX() ? locationA.getX() : locationB.getX());
        Double maxY = (locationA.getY() > locationB.getY() ? locationA.getY() : locationB.getY());
        Double minY = (locationA.getY() < locationB.getY() ? locationA.getY() : locationB.getY());
        Double maxZ = (locationA.getZ() > locationB.getZ() ? locationA.getZ() : locationB.getZ());
        Double minZ = (locationA.getZ() < locationB.getZ() ? locationA.getZ() : locationB.getZ());

        if (playerLocation.getX() <= maxX && playerLocation.getX() >= minX) {
            if (playerLocation.getY() <= maxY && playerLocation.getY() >= minY) {
                if (playerLocation.getZ() <= maxZ && playerLocation.getZ() >= minZ) {
                    return true;
                }
            }
        }

        return false;
    }
}
