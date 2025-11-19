package geriosb.technicalsprunki.common.entity.sprunki;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SprunkiEntity extends PathfinderMob {
    public SprunkiEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
    public void BrainSweep(){
        this.remove(RemovalReason.KILLED);
    }
}
