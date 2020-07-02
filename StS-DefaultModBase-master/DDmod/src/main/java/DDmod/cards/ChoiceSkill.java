package DDmod.cards;

import DDmod.DDmod;
import DDmod.util.CustomTags;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DDmod.DDmod.makeCardPath;

public class ChoiceSkill extends AbstractDynamicCard implements ModalChoice.Callback {


    // TEXT DECLARATION

    public static final String ID = DDmod.makeID(ChoiceSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("Spikes.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC = 1;
    private ModalChoice modal;


    // /STAT DECLARATION/


    public ChoiceSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                .setColor(AbstractCard.CardColor.RED) // Sets color of any following cards to red
                .addOption("Gain 3 Strength.", AbstractCard.CardTarget.SELF)
                .setColor(AbstractCard.CardColor.GREEN) // Sets color of any following cards to green
                .addOption("Gain 3 Dexterity.", AbstractCard.CardTarget.SELF)
                .setColor(AbstractCard.CardColor.COLORLESS) // Sets color of any following cards to colorless
                .addOption("Heal 15 HP.", AbstractCard.CardTarget.SELF)
                .create();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        modal.open();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //exhaust = false;
           // this.rawDescription = UPGRADE_DESC;
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        AbstractCard.CardColor color;
        switch (i) {
            case 0:
                color = AbstractCard.CardColor.RED;
                break;
            case 1:
                color = AbstractCard.CardColor.GREEN;
                break;
            case 2:
                color = AbstractCard.CardColor.COLORLESS;
                break;
            default:
                return;
        }

        if (color == AbstractCard.CardColor.COLORLESS) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, 15));
        }
        if(color == AbstractCard.CardColor.RED){
             AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 3)));
        }
        if(color == AbstractCard.CardColor.GREEN){
             AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 3)));
        }
    }
}
