package ca.fxco.disableNotification.mixin;

import net.minecraft.client.PeriodicNotificationManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(PeriodicNotificationManager.class)
public abstract class PeriodicNotificationManager_disableMixin {

    @Shadow protected abstract void stopTimer();


    @Inject(
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void apply(Map<String, List<PeriodicNotificationManager.Notification>> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
        this.stopTimer();
        ci.cancel();
    }
}
