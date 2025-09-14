package cool.intro.mixin.client;

import cool.intro.ChudcraftIntroClient;
import cool.intro.startintro.video;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cool.intro.ChudcraftIntroClient.*;

@Mixin(TitleScreen.class)
public class ExampleClientMixin {
    private static boolean shownVideo = false;

    @Inject(at = @At("TAIL"), method = "init")
    private void onInit(CallbackInfo ci) {
        if (!shownVideo) {
            shownVideo = true;
            MinecraftClient.getInstance().setScreen(new video());
        }
    }

}
