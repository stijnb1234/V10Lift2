package nl.SBDeveloper.V10Lift.api.objects;

import lombok.Getter;
import lombok.Setter;
import nl.SBDeveloper.V10Lift.api.blockdata.*;
import nl.SBDeveloper.V10Lift.utils.XMaterial;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

@Getter @Setter
public class LiftBlockData {
    private WrappedBisected bisected = null;
    private WrappedDirectional directional = null;
    private WrappedFaceAttachable faceAttachable = null;
    private WrappedLevelled levelled = null;
    private WrappedLightable lightable = null;
    private WrappedMultipleFacing multipleFacing = null;
    private WrappedOpenable openable = null;
    private WrappedPowerable powerable = null;
    private WrappedRail rail = null;
    private WrappedRotatable rotatable = null;
    private WrappedSlab slab = null;
    private String[] signLines = null;
    private byte data;

    @Nonnull
    public static LiftBlockData fromBlock(@Nonnull Block block) {
        LiftBlockData lbd = new LiftBlockData();

        if (XMaterial.isNewVersion()) {
            if (block.getBlockData() instanceof org.bukkit.block.data.Bisected) {
                org.bukkit.block.data.Bisected bukkitBisected = (org.bukkit.block.data.Bisected) block.getBlockData();
                WrappedBisected bisected = new WrappedBisected(WrappedBisected.Half.valueOf(bukkitBisected.getHalf().name()));
                lbd.setBisected(bisected);
            }

            if (block.getBlockData() instanceof org.bukkit.block.data.Directional) {
                org.bukkit.block.data.Directional bukkitDirectional = (org.bukkit.block.data.Directional) block.getBlockData();
                WrappedDirectional directional = new WrappedDirectional(bukkitDirectional.getFacing());
                lbd.setDirectional(directional);
            }

            if (block.getBlockData() instanceof org.bukkit.block.data.FaceAttachable) {
                org.bukkit.block.data.FaceAttachable bukkitFaceAttachable = (org.bukkit.block.data.FaceAttachable) block.getBlockData();
                WrappedFaceAttachable faceAttachable = new WrappedFaceAttachable(WrappedFaceAttachable.AttachedFace.valueOf(bukkitFaceAttachable.getAttachedFace().name()));
                lbd.setFaceAttachable(faceAttachable);
            }

            if (block.getBlockData() instanceof org.bukkit.block.data.Levelled) {
                org.bukkit.block.data.Levelled bukkitLevelled = (org.bukkit.block.data.Levelled) block.getBlockData();
                WrappedLevelled levelled = new WrappedLevelled(bukkitLevelled.getLevel());
                lbd.setLevelled(levelled);
            }
        } else {
            lbd.setData(block.getData());
        }

        return lbd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiftBlockData that = (LiftBlockData) o;
        return data == that.data &&
                Objects.equals(bisected, that.bisected) &&
                Objects.equals(directional, that.directional) &&
                Objects.equals(faceAttachable, that.faceAttachable) &&
                Objects.equals(levelled, that.levelled) &&
                Objects.equals(lightable, that.lightable) &&
                Objects.equals(multipleFacing, that.multipleFacing) &&
                Objects.equals(openable, that.openable) &&
                Objects.equals(powerable, that.powerable) &&
                Objects.equals(rail, that.rail) &&
                Objects.equals(rotatable, that.rotatable) &&
                Objects.equals(slab, that.slab) &&
                Arrays.equals(signLines, that.signLines);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bisected, directional, faceAttachable, levelled, lightable, multipleFacing, openable, powerable, rail, rotatable, slab, data);
        result = 31 * result + Arrays.hashCode(signLines);
        return result;
    }
}
