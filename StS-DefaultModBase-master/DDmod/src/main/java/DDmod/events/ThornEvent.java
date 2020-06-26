package DDmod.events;

import DDmod.DDmod;
import DDmod.util.CustomTags;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Random;

import static DDmod.DDmod.makeEventPath;

public class ThornEvent extends AbstractImageEvent {


    public static final String ID = DDmod.makeID("ThornEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("ThornsEvent.jpg");
    private static boolean isIronclad = false;

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;


    public ThornEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        if(AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
            isIronclad = true;
        }
            // The first dialogue options available to us.
        if(isIronclad){
            imageEventText.setDialogOption(OPTIONS[0]); // Gain a random thorns card
        }else{
            imageEventText.setDialogOption(OPTIONS[2]); // Gain a random card
        }
        imageEventText.setDialogOption(OPTIONS[1]); // Leave

    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0: // If you press button the first button (Button at index 0), in this case: Inspiration.
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]); // Update the text of the event
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]); // 1. Change the first button to the [Leave] button
                        this.imageEventText.clearRemainingOptions(); // 2. and remove all others
                        screenNum = 1; // Screen set the screen number to 1. Once we exit the switch (i) statement,
                        // we'll still continue the switch (screenNum) statement. It'll find screen 1 and do it's actions
                        // (in our case, that's the final screen, but you can chain as many as you want like that)

                         ArrayList<AbstractCard> cardList = CardLibrary.getAllCards();
                         ArrayList<AbstractCard> thornCardList = new ArrayList<>();
                         for(AbstractCard card : cardList){
                             if(card.hasTag(CustomTags.THORNS)){
                                 thornCardList.add(card);
                             }
                         }
                        Random rand = new Random();
                        AbstractCard randomThornCard = thornCardList.get(rand.nextInt(thornCardList.size()));
                        AbstractCard randomCard = cardList.get(rand.nextInt(cardList.size()));
                        if(isIronclad) {
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(randomThornCard.makeCopy(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        }else{
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(randomCard.makeCopy(), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        }

                        break; // Onto screen 1 we go.
                    case 1: // If you press button the second button (Button at index 1), in this case: Deinal
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]); // Update the text of the event
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]); // 1. Change the first button to the [Leave] button
                        this.imageEventText.clearRemainingOptions(); // 2. and remove all others
                        screenNum = 1;
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
            case 1: // Welcome to screenNum = 1;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }

    public void update() { // We need the update() when we use grid screens (such as, in this case, the screen for selecting a card to remove)
        super.update(); // Do everything the original update()
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) { // Once the grid screen isn't empty (we selected a card for removal)
            AbstractCard c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0); // Get the card
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2))); // Create the card removal effect
            AbstractDungeon.player.masterDeck.removeCard(c); // Remove it from the deck
            AbstractDungeon.gridSelectScreen.selectedCards.clear(); // Or you can .remove(c) instead of clear,
            // if you want to continue using the other selected cards for something
        }

    }

}
