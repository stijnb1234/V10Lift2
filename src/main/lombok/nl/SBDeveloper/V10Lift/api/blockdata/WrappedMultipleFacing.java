package nl.SBDeveloper.V10Lift.api.blockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.block.BlockFace;

import java.util.Set;

@AllArgsConstructor @Getter
public class WrappedMultipleFacing {
    private Set<BlockFace> faces;

    public boolean hasFace(BlockFace face) {
        return faces.contains(face);
    }

    public void setFace(BlockFace face, boolean has) {
        if (has) faces.add(face); else faces.remove(face);
    }
}
