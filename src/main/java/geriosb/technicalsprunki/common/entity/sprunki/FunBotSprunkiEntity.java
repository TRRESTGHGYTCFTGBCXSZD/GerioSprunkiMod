package geriosb.technicalsprunki.common.entity.sprunki;

import geriosb.technicalsprunki.init.Sprunkis;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class FunBotSprunkiEntity extends SprunkiEntity {
    public FunBotSprunkiEntity(EntityType<FunBotSprunkiEntity> type, Level level) {
        super(type, level);
    }
    public FunBotSprunkiEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(Sprunkis.FUN_BOT.get(), world);
    }
    public static void init() {
    }

}
