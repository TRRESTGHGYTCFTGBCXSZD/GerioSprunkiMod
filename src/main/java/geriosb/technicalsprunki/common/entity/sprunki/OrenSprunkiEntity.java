package geriosb.technicalsprunki.common.entity.sprunki;

import at.petrak.hexcasting.xplat.IXplatAbstractions;
import geriosb.technicalsprunki.init.Sprunkis;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class OrenSprunkiEntity extends SprunkiEntity {
    public OrenSprunkiEntity(EntityType<OrenSprunkiEntity> type, Level level) {
        super(type, level);
    }
    public OrenSprunkiEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(Sprunkis.OREN.get(), world);
    }
    public static void init() {
    }
}
