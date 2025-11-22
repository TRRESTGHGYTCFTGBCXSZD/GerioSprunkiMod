package geriosb.technicalsprunki.common.entity.sprunki;

import at.petrak.hexcasting.xplat.IXplatAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

public class BrainSweepChecker {
    public static NoBrainSweep retrieve(){
        if (ModList.get().isLoaded("hexcasting")) {
            return new NoBrainSweep.BrainSweep();
        } else {
            return new NoBrainSweep();
        }
    }
    public static class NoBrainSweep {
        public boolean CheckIfBrainSwept(Mob entity) {
            return false;
        }
        public static class BrainSweep extends NoBrainSweep {
            @Override
            public boolean CheckIfBrainSwept(Mob entity){
                var self = (Mob) (Object) entity;
                return IXplatAbstractions.INSTANCE.isBrainswept(self);
            }
        }
    }
}