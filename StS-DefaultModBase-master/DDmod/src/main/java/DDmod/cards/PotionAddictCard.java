package DDmod.cards;

import DDmod.powers.EasePower;
import DDmod.powers.PotionAddictPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DDmod.DDmod;
import DDmod.characters.TheDefault;
import DDmod.powers.CommonPower;

import static DDmod.DDmod.makeCardPath;

public class PotionAddictCard extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(PotionAddictCard.class.getSimpleName());
    public static final String IMG = makeCardPath("PotionAddictPower.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_COST = 1;

    // /STAT DECLARATION/


    public PotionAddictCard() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PotionAddictPower(p, magicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
           // upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}