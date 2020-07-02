package DDmod.relics;

import DDmod.DDmod;
import DDmod.util.DexOption;
import DDmod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.LiftOption;

import java.util.ArrayList;

import static DDmod.DDmod.makeRelicPath;

public class FlexRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = DDmod.makeID("FlexRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BarbedSlag64.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicPath("BarbedSlag64.png"));

    public static final int DEX_LIMIT = 3;

    public FlexRelic() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 0;
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        if (this.counter != 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new DexOption(this.counter < 3));
    }

}
