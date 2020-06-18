package DDmod.cards;

import DDmod.powers.AllInPower;
import DDmod.powers.EasePower;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DDmod.DDmod;
import DDmod.characters.TheDefault;

import static DDmod.DDmod.makeCardPath;

public class AllInSkill extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(AllInSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("AllIn.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 3;
    private static final int MAGIC_NUMBER = 2; //turns skipped
    private static int energyGained = 0;


    // /STAT DECLARATION/


    public AllInSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            if(c.cost < 0){
                energyGained += 1;
            }else {
                energyGained += c.cost;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyGained - COST));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AllInPower(p, magicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}
