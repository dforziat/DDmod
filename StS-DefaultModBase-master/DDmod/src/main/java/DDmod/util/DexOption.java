package DDmod.util;

import DDmod.DDmod;
import DDmod.relics.StuhbiRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;

import static DDmod.DDmod.makeUIPath;

public class DexOption extends AbstractCampfireOption {
    public static final String ID = DDmod.makeID(DexOption.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String IMG = makeUIPath("stuhbi.png");
    private static final Texture texture = TextureLoader.getTexture(IMG);

    public DexOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = texture;
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireLiftEffect());
            if(AbstractDungeon.player.hasRelic(StuhbiRelic.ID)){
                AbstractDungeon.player.getRelic(StuhbiRelic.ID).counter +=1;
            }
        }

    }
}
