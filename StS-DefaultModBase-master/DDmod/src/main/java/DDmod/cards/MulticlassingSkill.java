package DDmod.cards;

import basemod.BaseMod;
import basemod.devcommands.hand.Hand;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

import static DDmod.DDmod.makeCardPath;

public class MulticlassingSkill extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(MulticlassingSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("ScaleUp.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 3;
    private static final int UPGRADE_COST= 1;

    // /STAT DECLARATION/


    public MulticlassingSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard randomCard = CardLibrary.getAnyColorCard(CardRarity.RARE);
        while(randomCard.color == p.getCardColor()){
            randomCard = CardLibrary.getAnyColorCard(CardRarity.RARE);
        }
        randomCard.costForTurn = 0;
        AbstractDungeon.player.hand.addToHand(randomCard);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
