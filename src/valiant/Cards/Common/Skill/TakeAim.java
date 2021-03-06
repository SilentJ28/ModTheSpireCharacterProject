package valiant.Cards.Common.Skill;
import valiant.Actions.TakeAimAction;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class TakeAim extends CustomCard
{
    public static final String ID = "TakeAim";
    public static final String NAME = "Take Aim";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 0;
    private static final int POOL = 1;
    private static final int COST_REDUCTION = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;


    public TakeAim() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.TAKE_AIM), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = this.magicNumber = COST_REDUCTION;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new TakeAimAction(p, p, -this.magicNumber));
    }

    public boolean HasReducableCost()
    {
        int SmiteCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cost > 0 && c != this)
                return true;
            SmiteCount++;
        }
        return false;

    }
    @Override
    public AbstractCard makeCopy() {
        return new TakeAim();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}