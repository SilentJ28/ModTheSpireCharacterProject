package Cards.Rare.Attack;

import Actions.ExecutionAction;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.CorpseExplosionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class Execution extends CustomCard
{
    public static final String ID = "Execution";
    public static final String NAME = "Execution";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;


    public Execution() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getPower("Frail") == null) {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, "That enemy isn't frail...", true));
        } else {
            this.baseDamage = m.getPower("Frail").amount;
            this.baseDamage *= 3;


        this.calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ExecutionAction(m, p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Execution();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }

    }
}
