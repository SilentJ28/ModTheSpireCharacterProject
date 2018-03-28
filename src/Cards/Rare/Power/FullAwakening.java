package Cards.Rare.Power;
import MainMod.*;
import Patches.AbstractCardEnum;
import Powers.AscensionPower;
import Powers.SpiritPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class FullAwakening extends CustomCard
{
    public static final String ID = "FullAwakening";
    public static final String NAME = "Full Awakening";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Powers/enigma.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;
    private static final int STACK_BASE = 1;


    public FullAwakening() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.FULL_AWAKENING), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = STACK_BASE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 1.5F));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AscensionPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() {
        return new FullAwakening();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }

    }
}
