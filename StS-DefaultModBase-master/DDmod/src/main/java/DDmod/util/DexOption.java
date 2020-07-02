package DDmod.util;

import DDmod.DDmod;
import DDmod.cards.RapidStrikeAttack;
import DDmod.relics.FlexRelic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;

public class DexOption extends AbstractCampfireOption {
    public static final String ID = DDmod.makeID(DexOption.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;

    public DexOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireLiftEffect());
            if(AbstractDungeon.player.hasRelic(FlexRelic.ID)){
                AbstractDungeon.player.getRelic(FlexRelic.ID).counter +=1;
            }
        }

    }
}
