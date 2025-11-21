package geriosb.technicalsprunki.common.entity.sprunki;

import geriosb.technicalsprunki.init.Sprunkis;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class PoloSprunkiEntity extends SprunkiEntity {
    public PoloSprunkiEntity(EntityType<PoloSprunkiEntity> type, Level level) {
        super(type, level);
    }
    public PoloSprunkiEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(Sprunkis.POLO.get(), world);
    }
    public static void init() {
    }
}
