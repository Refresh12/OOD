package khnu.mizhfac.game;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static khnu.mizhfac.game.Game.straightFight;
import static khnu.mizhfac.game.WarriorClasses.*;
import static org.junit.jupiter.api.Assertions.*;
import static khnu.mizhfac.game.Game.fight;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class GameTest {

    @Test
    @DisplayName("1. Fight: When warrior fights again knight he should lost")
    void fight01() {
        log.info("fight01 is running");
        // given
        Warrior carl = WARRIOR.make();
        Warrior jim = KNIGHT.make();
        log.debug("carl = {}", carl);
        log.debug("jim = {}", jim);
        // when
        boolean res = fight(carl, jim);
        // then
        assertFalse(res);
    }

    @Test
    @DisplayName("2. Fight: When Knight fights again warrior first should win")
    void fight02() {
        // given
        Warrior ramon = KNIGHT.make();
        Warrior slevin = WARRIOR.make();
        // when
        boolean res = fight(ramon, slevin);
        // then
        assertTrue(res);
    }

    @Test
    @DisplayName("3. Fight: When warrior fights again warrior first should remain alive")
    void fight03() {
        // given
        Warrior bob = WARRIOR.make();
        Warrior mars = WARRIOR.make();
        // when
        boolean res = fight(bob, mars);
        // then
        assertTrue(bob.isAlive());
    }

    @Test
    @DisplayName("4. Fight: When Knight fights again warrior first should remain alive")
    void fight004() {
        // given
        Warrior zeus = KNIGHT.make();
        Warrior godkiller = WARRIOR.make();
        // when
        boolean res = fight(zeus, godkiller);
        // then
        assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("5. Fight: When Warrior fights again warrior second should remain alive")
    void fight05() {
        // given
        Warrior husband = WARRIOR.make();
        Warrior wife = WARRIOR.make();
        // when
        boolean res = fight(husband, wife);
        // then
        assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("6. Fight: When Warrior fights again warrior second should remain alive")
    void fight06() {
        // given
        Warrior dragon = WARRIOR.make();
        Warrior knight = KNIGHT.make();
        // when
        boolean res = fight(dragon, knight);
        // then
        assertTrue(knight.isAlive());
    }

    @Test
    @DisplayName("7. Fight: ")
    void fight07() {
        // given
        Warrior unit_1 = WARRIOR.make();
        Warrior unit_2 = KNIGHT.make();
        Warrior unit_3 = WARRIOR.make();
        // when
        fight(unit_1, unit_2);
        boolean res = fight(unit_2, unit_3);
        // then
        assertFalse(res);
    }

    @ParameterizedTest
    @MethodSource("warriorsPairsFirstWin")
    @DisplayName("first warrior should win in the duel")
    void fightTestFirstWin(Warrior first, Warrior second) {
        assertTrue(fight(first, second));
    }

    @ParameterizedTest
    @MethodSource("warriorsPairsSecondWin")
    @DisplayName("second warrior should win in the duel")
    void fightTestSecondWin(Warrior first, Warrior second) {
        assertFalse(fight(first, second));
    }

    static Stream<Arguments> warriorsPairsFirstWin() {
        return Stream.of(
                arguments(WARRIOR.make(), WARRIOR.make()),
                arguments(KNIGHT.make(), WARRIOR.make()),
                arguments(KNIGHT.make(), KNIGHT.make())
        );
    }

    static Stream<Arguments> warriorsPairsSecondWin() {
        return Stream.of(
                arguments(WARRIOR.make(), KNIGHT.make())
        );

    }

    @Test
    void WarriorVsDefender() {
        log.info("executing test WarriorVsDefender");
        var warrior = WARRIOR.make();
        var defender = DEFENDER.make();

        var res = fight(warrior, defender);

        assertAll(
                () -> assertFalse(res),
                () -> assertEquals(-1, ((AbstractWarrior) warrior).getHealth()),
                () -> assertEquals(9, ((AbstractWarrior) defender).getHealth())
        );
    }

    @Test
    void DefenderVsVampire() {
        log.info("executing test WarriorVsDefender");
        var defender = DEFENDER.make();
        var vampire = VAMPIRE.make();

        var res = fight(defender, vampire);

        assertAll(
                () -> assertTrue(res),
                () -> assertEquals(22, ((AbstractWarrior) defender).getHealth()),
                () -> assertEquals(-1, ((AbstractWarrior) vampire).getHealth())
        );
    }

    @Test
    void defenderSmokeTest() {
        var chuck = WARRIOR.make();
        var bruce = WARRIOR.make();
        var carl = KNIGHT.make();
        var dave = WARRIOR.make();
        var mark = WARRIOR.make();
        var bob = DEFENDER.make();
        var mike = KNIGHT.make();
        var rog = WARRIOR.make();
        var lancelot = DEFENDER.make();
        assertTrue(fight(chuck, bruce));
        assertFalse(fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(fight(bob, mike));
        assertTrue(fight(lancelot, rog));
        var my_army = new Army().addUnits(DEFENDER, 1);
        var enemy_army = new Army().addUnits(WARRIOR, 2);
        var army_3 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 1);
        var army_4 = new Army().addUnits(WARRIOR, 2);
        assertFalse(fight(my_army, enemy_army));
        assertTrue(fight(army_3, army_4));


    }

    @Test
    void lancerSmokeTest() {
        var chuck = WARRIOR.make();
        var bruce = WARRIOR.make();
        var carl = KNIGHT.make();
        var dave = WARRIOR.make();
        var mark = WARRIOR.make();
        var bob = DEFENDER.make();
        var mike = KNIGHT.make();
        var rog = WARRIOR.make();
        var lancelot = DEFENDER.make();
        var eric = VAMPIRE.make();
        var adam = VAMPIRE.make();
        var richard = DEFENDER.make();
        var ogre = WARRIOR.make();
        var freelancer = LANCER.make();
        var vampire = VAMPIRE.make();

        assertTrue(fight(chuck, bruce));
        assertFalse(fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(fight(bob, mike));
        assertTrue(fight(lancelot, rog));
        assertFalse(fight(eric, richard));
        assertTrue(fight(ogre, adam));
        assertTrue(fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());

        var my_army = new Army()
                .addUnits(DEFENDER, 2)
                .addUnits(VAMPIRE, 2)
                .addUnits(LANCER, 4)
                .addUnits(WARRIOR, 1);

        var enemy_army = new Army()
                .addUnits(WARRIOR, 2)
                .addUnits(LANCER, 2)
                .addUnits(DEFENDER, 2)
                .addUnits(VAMPIRE, 3);

        var army_3 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(LANCER, 1)
                .addUnits(DEFENDER, 2);

        var army_4 = new Army()
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 1)
                .addUnits(LANCER, 2);

        assertTrue(fight(my_army, enemy_army));
        assertFalse(fight(army_3, army_4));
    }
    @Test
    @DisplayName("Fight 04")
    void fight04() {
        Army army1 = new Army()

        .addUnits(LANCER, 7)
        .addUnits(VAMPIRE, 3)
        .addUnits(HEALER, 1)
        .addUnits(WARRIOR, 4)
        .addUnits(HEALER, 1)
        .addUnits(DEFENDER, 2);//18


        Army army2 = new Army()
        .addUnits(WARRIOR, 4)
        .addUnits(DEFENDER, 4)
        .addUnits(HEALER, 1)
        .addUnits(VAMPIRE, 6)
        .addUnits(LANCER, 4);//19



        var res = straightFight(army1, army2);

        assertFalse(res);

    }

    static class Rookie extends AbstractWarrior {
        public Rookie() {
            super(50);
        }

        @Override
        public int getAttack() {
            return 1;
        }
    }

    @Test

    @DisplayName("Battle: 20 -- straight fight")

    void battle20() {

        var army1 = new Army()
                .addUnits(LANCER, 7)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);

        var army2 = new Army()
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 4);

        var res = straightFight(army1, army2);

        assertTrue(res);

    }
}