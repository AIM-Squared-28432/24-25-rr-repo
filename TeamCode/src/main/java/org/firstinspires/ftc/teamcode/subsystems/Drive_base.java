package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;
import org.firstinspires.ftc.teamcode.Settings.GamepadSettings;
import org.firstinspires.ftc.teamcode.Settings.InputModification;

public class Drive_base extends Mechanism {

    private DcMotorEx frontleftDrive = null;
    private DcMotorEx backleftDrive = null;
    private DcMotorEx frontrightDrive = null;
    private DcMotorEx backrightDrive = null;
    public MecanumDrive drive;


    Pose2D targetPosition = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
//
//    @Override
//    public void init(HardwareMap hwMap) {
//        frontleftDrive = hwMap.get(DcMotorEx.class, ConfigInfo.frontleftDrive.getDeviceName());
//        frontrightDrive = hwMap.get(DcMotorEx.class, ConfigInfo.frontrightDrive.getDeviceName());
//        backleftDrive = hwMap.get(DcMotorEx.class, ConfigInfo.backleftDrive.getDeviceName());
//        backrightDrive = hwMap.get(DcMotorEx.class, ConfigInfo.backrightDrive.getDeviceName());
//    }

    @Override
    public void init(HardwareMap hwMap) {
        drive = new MecanumDrive(hwMap, new Pose2d(new Vector2d(0, 0), 0));
        setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
        drive.leftFront.setZeroPowerBehavior(behavior);
        drive.leftBack.setZeroPowerBehavior(behavior);
        drive.rightFront.setZeroPowerBehavior(behavior);
        drive.leftFront.setZeroPowerBehavior(behavior);
    }

    public void setMode(DcMotorEx.RunMode mode) {
        drive.leftFront.setMode(mode);
        drive.leftBack.setMode(mode);
        drive.rightFront.setMode(mode);
        drive.leftFront.setMode(mode);
    }
    @Override
    public void loop(AIMPad gamepad1) {
        manualDrive(gamepad1);
    }

    public void manualDrive(AIMPad gamepad) {
        double y = InputModification.poweredInput(deadzonedStickInput(-gamepad.getLeftStickY()), GamepadSettings.EXPONENT_MODIFIER);
        double x = InputModification.poweredInput(deadzonedStickInput(gamepad.getLeftStickX()), GamepadSettings.EXPONENT_MODIFIER);
        double rx = InputModification.poweredInput(deadzonedStickInput(gamepad.getRightStickX()), GamepadSettings.EXPONENT_MODIFIER);

        // Create left stick vector
        Vector2d leftStick = new Vector2d(-y, x);
        //i fucked this up


        // Set drive powers
        drive.setDrivePowers(new PoseVelocity2d(leftStick, -rx));
    }
//    double y, double x, double rx (previous inputs to drive meathod
//    void manualDrive(AIMPad gamepad) {
//        double frontleftPower;
//        double frontrightPower;
//        double backleftPower;
//        double backrightPower;
//
//        double y = InputModification.poweredInput(deadzonedStickInput(-gamepad.getLeftStickY()), GamepadSettings.EXPONENT_MODIFIER);
//        double x = InputModification.poweredInput(deadzonedStickInput(gamepad.getLeftStickX()), GamepadSettings.EXPONENT_MODIFIER);
//        double rx = InputModification.poweredInput(deadzonedStickInput(gamepad.getRightStickX()), GamepadSettings.EXPONENT_MODIFIER);
//
//        // Choose to drive using either Tank Mode, or POV Mode
//        // Comment out the method that's not used.  The default below is POV.
//
//        // POV Mode uses left stick to go forward, and right stick to turn.
//        // - This uses basic math to combine motions and is easier to drive straight.
//        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//        frontleftPower = (y + x + rx) / denominator;
//        backleftPower = (y - x + rx) / denominator;
//        frontrightPower = (y - x - rx) / denominator;
//        backrightPower = (y + x - rx) / denominator;
//
//        // Tank Mode uses one stick to control each wheel.
//        // - This requires no math, but it is hard to drive forward slowly and keep straight.
//        // leftPower  = -gamepad1.left_stick_y ;
//        // rightPower = -gamepad1.right_stick_y ;
//
//        // Send calculated power to wheels
//        frontleftDrive.setPower(frontleftPower);
//        backleftDrive.setPower(backleftPower);
//        frontrightDrive.setPower(frontrightPower);
//        backrightDrive.setPower(backrightPower);
//    }

    void setTargetPosition (Pose2D target) {targetPosition = target;}

    private double deadzonedStickInput(double input) {
        if (Math.abs(input) > GamepadSettings.GP1_STICK_DEADZONE) {
            return input;
        } else {
            return 0;
        }
    }
}
