//package org.firstinspires.ftc.teamcode.opmodes.Tests;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Settings.InputHandler;
//import org.firstinspires.ftc.teamcode.subsystems.Arm.Arm;
//
//@TeleOp(name="ArmTest", group="AAA_COMPETITION")
//public class ArmTest extends OpMode {
//
//    Arm arm = new Arm();
//    InputHandler inputHandler = new InputHandler();
//
//    AIMPad aimPad1;
//    AIMPad aimPad2;
//
//    enum TestingState {
//        HAND, WRIST, ELBOW, SHOULDERS, FULL
//    }
//
//    TestingState activeTestingState = TestingState.HAND;
//
//    @Override
//    public void init() {
//        arm.init(hardwareMap);
//
//        aimPad1 = new AIMPad(gamepad1);
//        aimPad2 = new AIMPad(gamepad2);
//    }
//
//    @Override
//    public void loop() {
//        aimPad1.update(gamepad1);
//        aimPad2.update(gamepad2);
//        inputHandler.updateInputs(aimPad1, aimPad2);
//
//        switch (activeTestingState) {
//            case HAND:
//                handTest();
//                break;
//            case WRIST:
//                wristTest();
//                break;
//            case ELBOW:
//                elbowTest();
//                break;
//            case SHOULDERS:
//                shouldersTest();
//                break;
//            case FULL:
//                fullTest();
//                break;
//        }
//        telemetry.addData("Advance Pressed", aimPad1.isStartPressed());
//        telemetry.addData("Advance Released", aimPad1.isStartReleased());
//        telemetry.addData("Previous State", aimPad1.getPreviousState());
//        telemetry.addData("Current State", aimPad1.getCurrentState());
//        telemetry.addData("Current Testing State", activeTestingState);
//        telemetry.update();
//    }
//
//    public void handTest() {
//        arm.hand.hand.systemsCheck(aimPad1, telemetry);
//        if (aimPad1.isStartPressed()) {
//            activeTestingState = TestingState.WRIST;
//        }
//    }
//
//    public void wristTest() {
//        arm.wrist.wrist.systemsCheck(aimPad1, telemetry);
//        if (aimPad1.isStartPressed()) {
//            activeTestingState = TestingState.ELBOW;
//        }
//    }
//
//    public void elbowTest() {
//        arm.elbow.elbow.systemsCheck(aimPad1, telemetry);
//        if (aimPad1.isStartPressed()) {
//            activeTestingState = TestingState.SHOULDERS;
//        }
//    }
//
//    public void shouldersTest() {
//        arm.shoulders.leftShoulder.systemsCheck(aimPad1, telemetry);
//        arm.shoulders.rightShoulder.systemsCheck(aimPad1, telemetry);
//        if (aimPad1.isStartPressed()) {
//            activeTestingState = TestingState.FULL;
//        }
//    }
//
//
//    public void fullTest() {
//        arm.loop(aimPad1, aimPad2);
//        if (inputHandler.FLEX_DOWN) {
//            arm.elbow.flexDown();
//        } else if (inputHandler.FLEX_NEUTRAL) {
//            arm.elbow.flexNeutral() ;
//        }
//
//        if (inputHandler.ROTATE_RIGHT) {
//            arm.wrist.rotateRight();
//        } else if (inputHandler.ROTATE_LEFT) {
//            arm.wrist.rotateLeft();
//        } else {
//            arm.wrist.rotateCenter();
//        }
//        //why togle why not just systems check
//        if (inputHandler.TOGGLE_HAND_ARM) {
//            arm.hand.toggle();
//        }
//        if (aimPad2.isStartReleased()) {
//            activeTestingState = TestingState.HAND;
//        }
//    }
//}
