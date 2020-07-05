package nl.SBDeveloper.V10Lift.api.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;

/** A liftblock object, for a block in a lift. */
@NoArgsConstructor @AllArgsConstructor
public class LiftBlock implements Comparable<LiftBlock> {

    @Getter @Setter private String world;
    @Getter private int x;
    @Getter @Setter private int y;
    @Getter private int z;

    //Only used for cabine blocks, because those need caching!
    @Getter @Setter private Material mat;
    @Getter private LiftBlockData blockData;

    //Only used for inputs!
    @Getter @Setter private String floor;
    @Getter @Setter private transient boolean active = false;

    //Only used for chests
    public Map<String, Object>[] serializedItemStacks = null;

    /**
     * Construct a new LiftBlock from a normal Block
     *
     * @param block The block
     */
    public LiftBlock(@Nonnull Block block) {
        this.world = block.getWorld().getName();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.mat = block.getType();
        this.blockData = LiftBlockData.fromBlock(block);
    }

    /**
     * Construct a new LiftBlock from a normal Block, with a floor (for inputs only!)
     *
     * @param block The block
     * @param floor The floor
     */
    public LiftBlock(@Nonnull Block block, String floor) {
        this.world = block.getWorld().getName();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.mat = block.getType();
        this.blockData = LiftBlockData.fromBlock(block);
        this.floor = floor;
    }

    /**
     * Construct a new LiftBlock from a existing LiftBlock, with a floor (for inputs only!)
     *
     * @param oldBlock The old Liftblock
     * @param floor The floor
     */
    public LiftBlock(@Nonnull LiftBlock oldBlock, String floor) {
        this.world = oldBlock.getWorld();
        this.x = oldBlock.getX();
        this.y = oldBlock.getY();
        this.z = oldBlock.getZ();
        this.mat = oldBlock.getMat();
        this.blockData = oldBlock.getBlockData();
        this.floor = floor;
    }

    @Override
    public int compareTo(@Nonnull LiftBlock lb) {
        int ret = Integer.compare(y, lb.y);
        if (ret == 0) ret = Integer.compare(x, lb.x);
        if (ret == 0) ret = Integer.compare(z, lb.z);

        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LiftBlock)) {
            if (!(o instanceof LiftSign)) return false;
            LiftSign other = (LiftSign) o;
            return world.equals(other.getWorld()) &&
                    x == other.getX() &&
                    y == other.getY() &&
                    z == other.getZ();
        }
        LiftBlock other = (LiftBlock) o;
        return world.equals(other.world) &&
                x == other.x &&
                y == other.y &&
                z == other.z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "LiftBlock{" +
                "world='" + world + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", mat=" + mat +
                ", blockData=" + blockData +
                ", floor='" + floor + '\'' +
                ", active=" + active +
                ", serializedItemStacks=" + Arrays.toString(serializedItemStacks) +
                '}';
    }
}
