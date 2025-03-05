package org.firstinspires.ftc.teamcode.opmodes.Tests;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;
//import org.firstinspires.ftc.teamcode.subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="temptest", group="Iterative OpMode")
public class TempPivotTest extends OpMode {

    AIMPad aimPad1;
    Pivot pivot = new Pivot();

    @Override
    public void init() {
        pivot.init(hardwareMap);

        aimPad1 = new AIMPad(gamepad1);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        if (aimPad1.isAPressed()) {
            pivot.setPower(1);
        } else if (aimPad1.isBPressed()) {
            pivot.setTargetPosition(Pivot.pivotPosition.PERP);
        } else if (aimPad1.isYPressed()){
            pivot.setTargetPosition(Pivot.pivotPosition.HANG);
        } else if (aimPad1.isXPressed()) {
            pivot.setTargetPosition(Pivot.pivotPosition.LOW_BUCKET);
        }
    }

}
