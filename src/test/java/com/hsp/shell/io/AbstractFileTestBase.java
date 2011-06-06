package com.hsp.shell.io;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

/**
 * @author georgepapas
 */
public abstract class AbstractFileTestBase {

   protected void onSetup() {
   }

   @Mock
   protected File mockFile;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      onSetup();
   }
}
