package nl.SBDeveloper.V10Lift.api.blockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WrappedSlab {
    private Type type;

    public enum Type {
        TOP,
        BOTTOM,
        DOUBLE;
    }
}
