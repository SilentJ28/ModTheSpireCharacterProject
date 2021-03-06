package valiant.Cards.Uncommon.Skill;

import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class MaddeningRevelation extends CustomCard
{
    public static final String ID = "MaddeningRevelation";
    public static final String NAME = "Maddening Revelation";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 0;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private static final int CARDS_DRAWN = 3;


    public MaddeningRevelation() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.MADDENING_REVELATION), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = this.magicNumber = CARDS_DRAWN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 4));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p , this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MaddeningRevelation();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }
}
