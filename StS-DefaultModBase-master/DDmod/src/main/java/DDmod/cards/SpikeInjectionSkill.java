package DDmod.cards;

import basemod.BaseMod;
import basemod.devcommands.hand.Hand;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.characters.Watcher;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.mods.BlueCards;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DDmod.DDmod;
import DDmod.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

import static DDmod.DDmod.makeCardPath;

public class SpikeInjectionSkill extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(SpikeInjectionSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("MulticlassingSkill.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;
    private static final int THORNS = 3;

    // /STAT DECLARATION/


    public SpikeInjectionSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, THORNS), THORNS));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Wound(), 1, true, true));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
