package org.firstinspires.ftc.teamcode.tests;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.LinLifts;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;
import org.firstinspires.ftc.teamcode.subsystems.ScoringAssembly;

@TeleOp(name="SystemsCheck", group="AAA_COMPETITION")

public class SystemsCheck extends OpMode {
        ScoringAssembly scorer = new ScoringAssembly();
        AIMPad aimPad1;
        AIMPad aimPad2;

        enum TestingState {
                LIFTS, PIVOT, ARM
        }

        TestingState activeTestingState = TestingState.LIFTS;

        @Override
        public void init() {
                scorer.init(hardwareMap);
                aimPad1 = new AIMPad(gamepad1);
                aimPad2 = new AIMPad(gamepad2);
        }


        @Override
        public void loop() {

                aimPad1.update(gamepad1);
                aimPad2.update(gamepad2);

                switch(activeTestingState) {
                        case LIFTS:
                                slidesTest();
                                break;
                        case PIVOT:
                                pivotTest();
                                break;
                        case ARM:
                                armTest();
                                break;

                }

                telemetry.addData("Previous State", aimPad1.getPreviousState());
                telemetry.addData("Current State", aimPad1.getCurrentState());
                telemetry.addData("Current Testing State", activeTestingState);
                telemetry.update();

        }

        private void slidesTest() {
                scorer.ll.loop(aimPad1);
                scorer.ll.telemetry(telemetry);
                if (aimPad1.isAPressed()) {
                        scorer.ll.setTargetPosition(LinLifts.SlidePosition.LOW_BUCKET);
                } else if (aimPad1.isXPressed()) {
                        scorer.ll.setTargetPosition(LinLifts.SlidePosition.HANG_SPECIMEN);
                } else if (aimPad1.isDPadRightPressed()) {
                        scorer.ll.setTargetPosition(LinLifts.SlidePosition.HANG);
                }

                if (aimPad1.isYHeld()) {
                        scorer.ll.updateManualPower(-aimPad1.getRightStickY());
                }
                if (aimPad1.isStartPressed()) {
                        activeTestingState = TestingState.PIVOT;
                }

        }


        private void pivotTest(){
                scorer.pivot.loop(aimPad1);
                scorer.pivot.telemetry(telemetry);
                if (aimPad1.isBPressed()) {
                        scorer.pivot.setTargetPosition(Pivot.pivotPosition.LOW_BUCKET);
                } else if (aimPad1.isXPressed()) {
                        scorer.pivot.setTargetPosition(Pivot.pivotPosition.HANG_SPECIMEN);
                } else if (aimPad1.isDPadLeftPressed()) {
                        scorer.pivot.setTargetPosition(Pivot.pivotPosition.ABOVE_PAR);
                } else if (aimPad1.isDPadUpPressed()) {
                        scorer.pivot.setTargetPosition(Pivot.pivotPosition.HANG);
                }

                if (aimPad1.isYHeld()) {
                        scorer.pivot.updateManualPower(-aimPad1.getRightStickY());
                }
                if (aimPad1.isStartPressed()) {
                        activeTestingState = TestingState.ARM;
                }
        }
        private void armTest() {
                scorer.arm.loop(aimPad1);

                scorer.arm.telemetry(telemetry);
                scorer.arm.hand.telemetry(telemetry);
                scorer.arm.wrist.telemetry(telemetry);


                if (aimPad1.isAPressed()) {
                        scorer.arm.hand.open();
                } else if (aimPad1.isBPressed()) {
                        scorer.arm.hand.close();
                } else if (aimPad1.isDPadUpPressed()) {
                        scorer.arm.hand.toggle();
                }

                if (aimPad1.isYPressed()) {
                        scorer.arm.wrist.rotateCenter();
                } else if (aimPad1.isXPressed()) {
                        scorer.arm.wrist.rotateLeft();
                } else if (aimPad1.isDPadRightPressed()) {
                        scorer.arm.wrist.rotateRight();
                }

                if (aimPad1.isStartPressed()) {
                        activeTestingState = TestingState.LIFTS;
                }
        }

}

