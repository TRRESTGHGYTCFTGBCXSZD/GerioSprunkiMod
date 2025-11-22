package geriosb.technicalsprunki.common.entity.sprunki;

import at.petrak.hexcasting.xplat.IXplatAbstractions;
import geriosb.technicalsprunki.init.Sprunkis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;


public abstract class SprunkiEntity extends PathfinderMob {
    public SprunkiEntity(EntityType<? extends SprunkiEntity> type, Level world) {
        super(type, world);
        setMaxUpStep(0.6f);

        xpReward = 0;
        setNoAi(false);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, (double)1.25F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.35D;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.sheep.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.sheep.death"));
    }

    @Override
    public void tick() {
        if (BrainSweepChecker.retrieve().CheckIfBrainSwept(this)){
            BrainSweep(this); // really funny, look at at/petrak/hexcasting/common/casting/actions/spells/great/OpBrainsweep.kt
            return;
        }
        super.tick();
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 10);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        return builder;
    }

    public static void BrainSweep(SprunkiEntity thatentity){ // flaying sprunki's mind will result in replacing with polo
        Level level = thatentity.level();
        PoloSprunkiEntity polo = Sprunkis.POLO.get().create(level);
        assert polo != null;
        polo.setHealth(thatentity.getHealth());
        polo.moveTo(thatentity.getX(),thatentity.getY(),thatentity.getZ(),thatentity.getYRot(),thatentity.getXRot());
        polo.setCustomName(thatentity.getCustomName());
        CompoundTag thetag = new CompoundTag();
        thatentity.addAdditionalSaveData(thetag);
        if (thetag.getBoolean("PersistenceRequired")) { // pretty much a stupid workaround but makes sure that PersistenceRequired is available on public
            polo.setPersistenceRequired();
        }
        level.addFreshEntity(polo);
        thatentity.remove(RemovalReason.KILLED);
    }
}
