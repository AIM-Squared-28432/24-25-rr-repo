package org.firstinspires.ftc.teamcode.Settings;
import com.aimrobotics.aimlib.gamepad.AIMPad;
public class InputHandler {
//      DECLARE ALL FUNCTIONS AN THEIR AIMPAD INPUTS

    //ADVANCERS
    public boolean SEARCH_ADVANCE = false;
    public boolean SAMPLE_ADVANCE = false;
    public boolean SPECIMEN_ADVANCE = false;
    public boolean SPEC_ADVANCE_ADVANCE = false;
    public boolean RETREAT = false;

    //TOGGLE STATES

    public boolean BACK_TO_SEARCH = false;

    //SEARCH STATE
    public boolean ROTATE_ONE_RIGHT = false;
    public boolean ROTATE_ONE_LEFT= false;
    public double MOVE_SLIDES = 0;
    public boolean TOGGLE_SPECIMEN_INTAKE = false;

    //SCORING
    public boolean LOW_BUCKET = false;
    public boolean TOGGLE_SPECIMEN = false;

    //HANG
    public boolean TOGGLE_HANG = false;

    //MULTI USE
    public boolean TOGGLE_HAND = false;



    public void updateInputs (AIMPad aimpad1, AIMPad aimpad2) {
        //SET DIFFERENT ACTIONS TO INPUTS
        SEARCH_ADVANCE = aimpad2.isRightBumperPressed();
        SAMPLE_ADVANCE = aimpad2.isLeftBumperPressed();
        SPECIMEN_ADVANCE = aimpad2.isRightBumperPressed();
        SPEC_ADVANCE_ADVANCE = aimpad2.isDPadLeftPressed();
        RETREAT = aimpad2.isDPadDownPressed();

        MOVE_SLIDES = -aimpad2.getLeftStickY();
        TOGGLE_SPECIMEN_INTAKE = aimpad2.isAPressed();

        LOW_BUCKET = aimpad2.isAPressed();
        TOGGLE_SPECIMEN = aimpad2.isAPressed();

        TOGGLE_HANG = aimpad1.isXPressed();

        TOGGLE_HAND = aimpad2.isXPressed();

        ROTATE_ONE_LEFT = aimpad2.getLeftTrigger() > .01;
        ROTATE_ONE_RIGHT = aimpad2.getRightTrigger() > .01;

    }
}
