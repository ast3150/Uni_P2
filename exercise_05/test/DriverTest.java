import exercise05.Driver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by ast on 10.04.17.
 */
public class DriverTest {
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void testDoesReturnOnMain() throws Exception {
		// expect
		exit.expectSystemExitWithStatus(0);

		// given
		systemInMock.provideLines("L", "R", "D", "U", "D");

		// when
		String[] args = {"games/drivertestconfig.txt"};
		Driver.main(args);

		// then expectation should hold
	}
}
