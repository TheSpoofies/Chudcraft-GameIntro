package cool.intro;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class ChudcraftIntroClient implements ClientModInitializer {

    public static final Identifier INTRO_MUSIC_ID = Identifier.of("chudcraftintro", "intro_music");
    public static final SoundEvent INTRO_MUSIC_EVENT = SoundEvent.of(INTRO_MUSIC_ID);


    @Override
    public void onInitializeClient() {

    }



}