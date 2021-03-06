package valiant.Cards.Starter;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.*;

public class BlindingLight extends CustomCard
{
    public static final String ID = "BlindingLight";
    public static final String NAME = "Minor Healing";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 0;
    private static final int WEAK_AMOUNT = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.BASIC;
    private static final CardTarget target = CardTarget.ALL_ENEMY;

    public BlindingLight() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.BLINDING_LIGHT), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                CardRarity.BASIC, CardTarget.ALL_ENEMY);
        this.baseMagicNumber =WEAK_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlindingLight();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(WEAK_AMOUNT);
        }

    }
}
