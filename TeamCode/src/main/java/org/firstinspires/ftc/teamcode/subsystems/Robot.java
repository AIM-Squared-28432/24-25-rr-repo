package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot extends Mechanism {
    public Drive_base db = new Drive_base();
    Arm arm = new Arm();
    Intake intake = new Intake();
    // Add arguments
    LinLifts ll = new LinLifts();

    @Override
    public void init(HardwareMap hwMap) {
        db.init(hwMap);
        arm.init(hwMap);
        ll.init(hwMap);
        intake.init(hwMap);
    }

    @Override
    public void loop(AIMPad gamepad1, AIMPad gamepad2) {
        db.manualDrive(gamepad1);
//        -gamepad1.getLeftStickY(), gamepad1.getLeftStickX(), gamepad1.getRightStickX() old db.drive inputs
//        db.loop(gamepad1);
//change targets in robot then move them to the indivisual classes and call the enums except for teh intake
        ll.loop(gamepad1);
        arm.loop(gamepad2);
        intake.loop(gamepad2);
        if (gamepad2.isAPressed()) {
            ll.setTargetPosition(1100);
        } else if (gamepad2.isBPressed()) {
            ll.setTargetPosition(500);
        } else if (gamepad2.isXPressed()) {
            ll.setTargetPosition(1550);
        } else if (gamepad2.isYPressed()) {
            ll.setTargetPosition(0);
        }
//
        if (gamepad2.isDPadUpPressed()) {
            arm.setTargetPosition(0);
        } else if (gamepad2.isDPadDownPressed()){
            arm.setTargetPosition(-950);
        } else if (gamepad2.isDPadLeftPressed()) {
            arm.setTargetPosition(-1100);
        } else if (gamepad2.isDPadRightPressed()) {
            arm.setTargetPosition(-590);
        }
//
        if (gamepad2.getRightTrigger() > .1) {
            intake.spinIn();
        } else if (gamepad2.getLeftTrigger() > .1){
            intake.spinOut();
        } else {
            intake.spinOff();
        }


        if (gamepad2.isLeftBumperPressed()) {
            intake.straight();
        } else if (gamepad2.isRightBumperPressed()) {
            intake.side();
        }
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("right Arm position", arm.rightArm.getCurrentPosition());
        telemetry.addData("left Arm position", arm.leftArm.getCurrentPosition());
        telemetry.addData("Arm Target", arm.activeTargetPosition);
        telemetry.addData("ll right Target", ll.rightSlide.getTargetPosition());
        telemetry.addData("rightPowerslide", ll.rightSlide.getPower());
        telemetry.addData("left", ll.leftSlide.getPower());
//        telemetry.addData("wrist position", intake.WristActiveTargetState);
        telemetry.addData("hand power", intake.hand.getPower());
        intake.wrist.telemetry(telemetry);
    }
}
