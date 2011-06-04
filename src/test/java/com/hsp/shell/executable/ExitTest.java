package com.hsp.shell.executable;

import com.hsp.shell.core.exception.ExecutionResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author georgepapas
 */
public class ExitTest extends AbstractExecutableTestBase {

   private Exit exit;

   @Override
   protected void setup() {
      exit = new Exit();
   }

   @Test
   public void shouldHaveExitBooleanAsTrueInExecutionResult() {
      ExecutionResult result = exit.execute(commandLine, ps, mockEnvironment);
      assertThat(result.isExitShell(), is(true));
   }

}
