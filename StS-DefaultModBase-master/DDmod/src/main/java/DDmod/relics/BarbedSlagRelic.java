package DDmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import DDmod.DDmod;
import DDmod.util.TextureLoader;

import static DDmod.DDmod.makeRelicOutlinePath;
import static DDmod.DDmod.makeRelicPath;

public class BarbedSlagRelic extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * At the start of each combat, gain 1 Strength (i.e. Vajra)
     */

    // ID, images, text.
    public static final String ID = DDmod.makeID("BarbedSlagRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BarbedSlag64.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicPath("BarbedSlag64.png"));

    private final int thornGain = 1;

    public BarbedSlagRelic() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);
    }


    public int getThorns(){
        return this.thornGain;
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
