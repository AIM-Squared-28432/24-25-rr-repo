package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Arm.Arm;

public class ScoringAssembly extends Mechanism {

    public Arm arm;
    public LinLifts ll;
    public Pivot pivot;

    @Override
    public void init (HardwareMap hwMap) {
        ll = new LinLifts();
        ll.init(hwMap);
        pivot = new Pivot();
        pivot.init(hwMap);
        arm = new Arm();
        arm.init(hwMap);
    }

    public void loop(AIMPad aimpad, AIMPad aimpad2) {
        pivot.loop(aimpad);
        ll.loop(aimpad);
        arm.loop(aimpad);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        pivot.telemetry(telemetry);
        arm.telemetry(telemetry);
        ll.telemetry((telemetry));
    }

    public void fullFix () {
        arm.neutralOpen();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.PERP);
    }

    public void startSearch() {
//        arm.neutralClosed();
//        arm.wrist.rotateCenter();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.ABOVE_PAR);
    }

    public void search() {
//        arm.neutralOpen();
        arm.wrist.rotateCenter();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.PAR);
    }

    public void endSearch() {
//        arm.neutralClosed();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.ABOVE_PAR);
    }
    public void dropSpecimen() {
//        arm.neutralClosed();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.PAR);
    }

    public void intakeSpecimen() {
//        arm.neutralOpen();
        ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
        pivot.setTargetPosition(Pivot.pivotPosition.PAR);
    }

    public void hangSpecimenPrep() {
        arm.neutralClosed();
        ll.setTargetPosition(LinLifts.SlidePosition.HANG_SPECIMEN);
        pivot.setTargetPosition(Pivot.pivotPosition.HANG_SPECIMEN);
    }


    public void outtakeLowPrep() {
        arm.neutralClosed();
        ll.setTargetPosition(LinLifts.SlidePosition.LOW_BUCKET);
        pivot.setTargetPosition(Pivot.pivotPosition.LOW_BUCKET);
    }


    public void toggleSpecimen() {
        if(ll.activeSlidesPosition == LinLifts.SlidePosition.HANG_SPECIMEN){
            ll.setTargetPosition(LinLifts.SlidePosition.HANG_SPECIMEN_DOWN);
        } else {
            ll.setTargetPosition(LinLifts.SlidePosition.HANG_SPECIMEN);
        }
    }



    public void hang() {
        arm.neutralClosed();
        ll.setTargetPosition(LinLifts.SlidePosition.HANG);
        pivot.setTargetPosition(Pivot.pivotPosition.HANG);
    }




    public boolean areMotorsAtTargetPresets() {
        return pivot.isAtTargetPosition() && ll.isAtTargetPosition();
    }

}
