package DDmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DDmod.DDmod;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static DDmod.DDmod.makeCardPath;

public class PlatedThornsSkill extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(PlatedThornsSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 3;
    private static final int UPGRADE_COST = -1;
    int initialHandSize = 0;
    private AbstractPlayer p;

    // /STAT DECLARATION/


    public PlatedThornsSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int initialHandSize = p.gameHandSize;
        this.p = p;
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(false, true, true));

    }

@Override
public void triggerOnEndOfPlayerTurn(){
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p,initialHandSize - p.gameHandSize), initialHandSize - p.gameHandSize));
    DDmod.logger.info("final hand size" + p.gameHandSize);
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
