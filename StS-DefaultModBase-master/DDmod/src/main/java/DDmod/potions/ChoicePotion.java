package DDmod.potions;

import DDmod.DDmod;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.BandageUp;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ChoicePotion extends AbstractPotion implements ModalChoice.Callback{


    public static final String POTION_ID = DDmod.makeID("ChoicePotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    private ModalChoice modal;
    public ChoicePotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.ANCIENT);
        
        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();
        
        // Initialize the Description
        description = DESCRIPTIONS[0];
        
       // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;
        
        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));

        modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                .setColor(AbstractCard.CardColor.RED) // Sets color of any following cards to red
                //.addOption("Gain 3 Strength.", AbstractCard.CardTarget.SELF)
                .addOption(new Strike_Red())
                .setColor(AbstractCard.CardColor.GREEN) // Sets color of any following cards to green
               // .addOption("Gain 3 Dexterity.", AbstractCard.CardTarget.SELF)
                .addOption(new Defend_Red())
                .setColor(AbstractCard.CardColor.COLORLESS) // Sets color of any following cards to colorless
              //  .addOption("Heal 15 HP.", AbstractCard.CardTarget.SELF)
                .addOption(new BandageUp())
                .create();
        
    }

    @Override
    public void use(AbstractCreature target) {
        modal.open();
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new ChoicePotion();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 2;
    }

    public void upgradePotion()
    {
      potency += 1;
     // tips.clear();
    //  tips.add(new PowerTip(name, description));
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
            //AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, 15));
        }
        if(color == AbstractCard.CardColor.RED){
           // AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 3)));
        }
        if(color == AbstractCard.CardColor.GREEN){
           // AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 3)));
        }
    }
}
