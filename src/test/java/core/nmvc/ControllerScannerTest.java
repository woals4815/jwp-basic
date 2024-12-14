package core.nmvc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class ControllerScannerTest {
    private ControllerScanner controllerScanner;
    @Before
    public void setUp() throws Exception {
        this.controllerScanner = new ControllerScanner();
    }

    @Test
    public void getControllers() {
        assertEquals(1, this.controllerScanner.getControllers().size());
    }
}