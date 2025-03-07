package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.InputHandler;

public class Robot extends Mechanism {
    public Drive_base db;
    public ScoringAssembly scorer = new ScoringAssembly();
    InputHandler inputHandler = new InputHandler();

    boolean isAuto;
    Pose2d startingPose;

    public Robot (Pose2d startingPose, boolean isAuto) {
        this.startingPose = startingPose;
        this.isAuto = isAuto;
        db = new Drive_base(startingPose);
    }
    public Robot(boolean isAuto) {
        this.isAuto = isAuto;
    }

    enum RobotState {
        RESETTING,
        SEARCHING,
        DROP_SPECIMEN,
        SCORE_PREPING,
        SCOREING,
        HANGING
    }

    enum ScoringElement {
        SPECIMEN,
        SAMPLE
    }
    ScoringElement activeScoringElementType = ScoringElement.SPECIMEN;

    RobotState activeState = RobotState.RESETTING;


    @Override
    public void init(HardwareMap hwMap) {
        db.init(hwMap);
        scorer.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimpad1, AIMPad aimpad2) {
        scorer.loop(aimpad1, aimpad2);

        if (!isAuto) {
            inputHandler.updateInputs(aimpad1, aimpad2);
            db.loop(aimpad1);
            switch (activeState) {
                case RESETTING:
                    resettingState();
                    break;
                case SEARCHING:
                    searchingState();
                    break;
                case DROP_SPECIMEN:
                    dropSpecimenState();
                    break;
                case SCORE_PREPING:
                    scorePrepState();
                    break;
                case SCOREING:
                    scoreState();
                    break;
                case HANGING:
                    hangState();
                    break;
            }

            if (inputHandler.TOGGLE_HANG) {
                if (scorer.ll.getCurrentPosition() == 0) {
                    scorer.ll.setTargetPosition(LinLifts.SlidePosition.HANG);
                } else {
                    scorer.ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
                }
            }
        }
    }
    private void resettingState() {

            scorer.startSearch();

            if (inputHandler.SEARCH_ADVANCE) {
                activeState = RobotState.SEARCHING;
            }

            if(inputHandler.RETREAT) {
                activeState = RobotState.SCORE_PREPING;
            }

        }

    private void searchingState() {


                scorer.search();

                scorer.ll.updateManualPower(inputHandler.MOVE_SLIDES);

                if (inputHandler.SAMPLE_ADVANCE) {
                    scorer.endSearch();
                    activeScoringElementType = ScoringElement.SAMPLE;
                    activeState = RobotState.SCORE_PREPING;
                }

                if (inputHandler.SPECIMEN_ADVANCE) {
                    scorer.endSearch();
                    activeScoringElementType = ScoringElement.SPECIMEN;
                    activeState = RobotState.DROP_SPECIMEN;
                } else if (inputHandler.SPEC_ADVANCE_ADVANCE) {
                    activeState = RobotState.SCORE_PREPING;
                }
                if (inputHandler.ROTATE_ONE_LEFT) {
                    scorer.arm.wrist.toggleLeft();
                } else if (inputHandler.ROTATE_ONE_RIGHT) {
                    scorer.arm.wrist.toggleRight();
                }

                if (inputHandler.TOGGLE_HAND) {
                    scorer.arm.hand.toggle();
                }




        if(inputHandler.RETREAT) {
            activeState = RobotState.RESETTING;
        }
    }

    private void dropSpecimenState() {

        scorer.ll.updateManualPower(inputHandler.MOVE_SLIDES);

        if (inputHandler.TOGGLE_HAND) {
            scorer.arm.hand.open();

        }
        if (inputHandler.SPECIMEN_ADVANCE) {
            activeState = RobotState.SEARCHING;
        }

        if (inputHandler.RETREAT) {
            activeScoringElementType = ScoringElement.SAMPLE;
            activeState = RobotState.SEARCHING;
        }

    }

    private void searchingSpecState() {

        scorer.search();

        scorer.ll.updateManualPower(inputHandler.MOVE_SLIDES);

        if (inputHandler.SPECIMEN_ADVANCE) {
            scorer.endSearch();
            activeScoringElementType = ScoringElement.SPECIMEN;
            activeState = RobotState.SCORE_PREPING;
        }
        if (inputHandler.ROTATE_ONE_LEFT) {
            scorer.arm.wrist.toggleLeft();
        } else if (inputHandler.ROTATE_ONE_RIGHT) {
            scorer.arm.wrist.toggleRight();
        }

        if (inputHandler.TOGGLE_HAND) {
            scorer.arm.hand.toggle();
        }
    }
    private void scorePrepState() {
        scorer.ll.setActiveControlState(LinLifts.llControlState.AUTONOMOUS);
        switch (activeScoringElementType) {

            case SPECIMEN:
                scorer.arm.neutralClosed();
                scorer.ll.setTargetPosition(LinLifts.SlidePosition.HANG_SPECIMEN);
                scorer.pivot.setTargetPosition(Pivot.pivotPosition.HANG_SPECIMEN);
                if (inputHandler.SPECIMEN_ADVANCE) {
                    activeState = RobotState.SCOREING;
                }
                break;
            case SAMPLE:

                    scorer.arm.neutralClosed();
                scorer.ll.setTargetPosition(LinLifts.SlidePosition.LOW_BUCKET);
                scorer.pivot.setTargetPosition(Pivot.pivotPosition.LOW_BUCKET);

                    if (inputHandler.SAMPLE_ADVANCE) {
                        activeState = RobotState.SCOREING;
                    }

                break;
        }

        if (inputHandler.RETREAT) {
            activeState = RobotState.RESETTING;
        }
    }
    private void scoreState() {
        switch (activeScoringElementType) {
            case SAMPLE:

                if (inputHandler.TOGGLE_HAND) {
                    scorer.arm.hand.toggle();
                }
                if (inputHandler.ROTATE_ONE_LEFT) {
                    scorer.arm.wrist.toggleLeft();
                } else if (inputHandler.ROTATE_ONE_RIGHT) {
                    scorer.arm.wrist.toggleRight();
                }
                if (inputHandler.RETREAT) {
                    activeState = RobotState.SCORE_PREPING;
                }

                break;
            case SPECIMEN:

                if(inputHandler.TOGGLE_SPECIMEN) {
                    scorer.toggleSpecimen();
                } else if (inputHandler.TOGGLE_HAND) {
                    scorer.arm.hand.toggle();
                }
                if (inputHandler.RETREAT) {
                    activeState = RobotState.SEARCHING;
                }
                break;


        }

        if (inputHandler.SPECIMEN_ADVANCE || inputHandler.SAMPLE_ADVANCE) {
            activeState = RobotState.RESETTING;
        }
    }
    private void hangState() {
        if (inputHandler.TOGGLE_HANG) {
            if (scorer.ll.getCurrentPosition() == 0) {
            scorer.ll.setTargetPosition(LinLifts.SlidePosition.HANG);
            } else {
                scorer.ll.setTargetPosition(LinLifts.SlidePosition.FLOOR);
            }
        }
    }






    @Override
    public void telemetry(Telemetry telemetry) {
        scorer.telemetry(telemetry);
        telemetry.addData("Current State:", activeState);
        telemetry.addData("Current Element:", activeScoringElementType);
    }
}
