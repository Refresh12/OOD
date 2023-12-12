package khnu.mizhfac.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CanHit extends HasAttack {
    Logger log = LoggerFactory.getLogger(CanHit.class);
    default void hit(CanAcceptDamage opponent){
        log.info("Warrior {} hits {}", this, opponent);
        opponent.acceptDamage(getAttack());
    }
}
