package nl.SBDeveloper.V10Lift.api.blockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WrappedBisected {
    private Half half;

    public enum Half {
        TOP,
        BOTTOM;
    }
}