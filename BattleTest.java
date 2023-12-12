package khnu.mizhfac.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static khnu.mizhfac.game.WarriorClasses.KNIGHT;
import static khnu.mizhfac.game.WarriorClasses.WARRIOR;
import static org.junit.jupiter.api.Assertions.*;
public class BattleTest {


    @Test
    @DisplayName("1. Battle: army of 1 warroior looses to army of 2 warriors")
    void battle01() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 1);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 2);

        var res = Game.fight(army1, army2);
        assertFalse(res);
    }
    @Test
    @DisplayName("2. Battle: army of 2 warroior looses to army of 3 warriors")
    void battle02() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 2);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 3);

        var res = Game.fight(army1, army2);
        assertFalse(res);
    }
    @Test
    @DisplayName("3. Battle: army of 5 warroior looses to army of 7 warriors")
    void battle03() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 5);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 7);

        var res = Game.fight(army1, army2);
        assertFalse(res);
    }
    @Test
    @DisplayName("4. Battle: army of 20 warroior looses to army of 21 warriors")
    void battle04() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 20);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 21);

        var res = Game.fight(army1, army2);
        assertTrue(res);
    }
    @Test
    @DisplayName("5. Battle: army of 10 warroior looses to army of 11 warriors")
    void battle05() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 10);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 11);

        var res = Game.fight(army1, army2);
        assertTrue(res);
    }
    @Test
    @DisplayName("6. Battle: army of 11 warroior win to army of 7 warriors")
    void battle06() {
        var army1 = new Army();
        var army2 = new Army();
        //army1.addUnits(WarriorClasses.KNIGHT, 5);
        army1.addUnits(WARRIOR::make, 11);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 7);

        var res = Game.fight(army1, army2);
        assertTrue(res);
    }

    @Test
    @DisplayName("7. Battle: army of 20 warriors + 5 knights looses to army of 30 warriors")
    void battle07() {
        var army1 = new Army()
                .addUnits(WARRIOR::make, 20)
                .addUnits(KNIGHT, 5);
        var army2 = new Army()
                .addUnits(WARRIOR, 30);

        var res = Game.fight(army2, army1);
        assertTrue(res);
    }

    @Test
    @DisplayName("Army(3Warrior) > Army(2Warrior) + Army(1Knight)")
    void testAgainstTwoArmies() {
        var army1 = new Army().addUnits(WARRIOR, 3);
        var army2 = new Army().addUnits(WARRIOR, 2);
        var army3 = new Army().addUnits(KNIGHT, 1);

        Game.fight(army1, army2);
        var res = Game.fight(army1, army3);

        assertTrue(res);
    }
    @Test
    @DisplayName("Army(3Warrior) > Army(2Warrior + 1Knight)")
    void testAgainstSingleArmy() {
        var army1 = new Army().addUnits(WARRIOR, 3);
        var army2 = new Army()
                .addUnits(WARRIOR, 2)
                .addUnits(KNIGHT, 1);

        var res = Game.fight(army1, army2);

        assertTrue(res);
    }
}
