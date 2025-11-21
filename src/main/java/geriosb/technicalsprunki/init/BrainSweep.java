package geriosb.technicalsprunki.init;

import at.petrak.hexcasting.api.HexAPI;
import geriosb.technicalsprunki.common.entity.sprunki.SprunkiEntity;

public class BrainSweep {
    public static void BrainSweepHandler(){
        HexAPI.instance().registerCustomBrainsweepingBehavior(Sprunkis.OREN.get(), SprunkiEntity::BrainSweep);
    }
}
