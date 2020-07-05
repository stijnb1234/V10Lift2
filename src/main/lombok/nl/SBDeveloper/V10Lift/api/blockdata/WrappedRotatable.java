package nl.SBDeveloper.V10Lift.api.blockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.BlockFace;

@Getter
@Setter
@AllArgsConstructor
public class WrappedRotatable {
    private BlockFace rotation;
}
