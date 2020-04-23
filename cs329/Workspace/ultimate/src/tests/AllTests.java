package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author mb5053
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ FirstMoveIsCenterCheckerTest.class,
                FiveInARowWinConditionTest.class, MoveIsEmptyCheckerTest.class,
                NumberCapturesWinConditionTest.class, PenteBoardTest.class,
                PlayerTest.class,
                SecondMoveIsWithinRangeOfCenterCheckerTest.class,
                ExactlyFiveInARowWinConditionTest.class
                })
public class AllTests {

}
