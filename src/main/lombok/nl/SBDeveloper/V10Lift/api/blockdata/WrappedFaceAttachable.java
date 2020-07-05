package nl.SBDeveloper.V10Lift.api.blockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WrappedFaceAttachable {
    private AttachedFace attachedFace;

    public enum AttachedFace {
        FLOOR,
        WALL,
        CEILING;
    }
}
