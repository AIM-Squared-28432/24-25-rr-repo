//package org.firstinspires.ftc.teamcode.opmodes.auto;
//
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.subsystems.Pivot;
//import org.firstinspires.ftc.teamcode.subsystems.Robot;
//
//
//@Autonomous(name = "Auto Test", group = "AAA_COMP", preselectTeleOp="teleop")
//public final class AutoTest extends LinearOpMode {
//
//    AutoConstants constants = new AutoConstants();
//    Robot robot = new Robot(constants.startingPose,true);
//
//    AIMPad aimPad1;
//    AIMPad aimPad2;
//
//    boolean isDone = false;
//
//    @Override
//    public void runOpMode() {
//        robot.init(hardwareMap);
//        aimPad1 = new AIMPad(gamepad1);
//        aimPad2 = new AIMPad(gamepad2);
//        MecanumDrive drive = new MecanumDrive(hardwareMap, constants.startingPose);
//        //might be because this is at the starting pose not at 0 nvm
////
////        Action myTrajectory = robot.db.drive.actionBuilder(constants.zero)
////                .strafeTo()
////                .forward(5)
////                .build();
////
////        Action traj1 = robot.db.drive.actionBuilder(constants.startingPose)
////                .splineTo(new Vector2d(4, -32), Math.toRadians(90))
////                .strafeTo(new Vector2d(30,-40))
////                .splineToConstantHeading(new Vector2d(40, -10), Math.toRadians(0))
////                .splineTo(new Vector2d(45, -10), Math.toRadians(0))
////                .strafeTo(new Vector2d(48,-60))
////                .splineToConstantHeading(new Vector2d(55, -10), Math.toRadians(0))
////                .strafeTo(new Vector2d(55,-60))
////                .splineToConstantHeading(new Vector2d(62, -10), Math.toRadians(0))
////                .strafeTo(new Vector2d(62,-60))
////                .build();
////                .strafeTo(constants.test.position)
////                .build();
//
//        Action traj2 = robot.db.drive.actionBuilder(robot.db.drive.localizer.getPose())
//                .splineTo(new Vector2d(-34,-35), Math.toRadians(180))
//                        .strafeTo(new Vector2d(-34,-27))
//                        //do stuff
//                        .splineToConstantHeading(new Vector2d(-50,-50), Math.toRadians(0))
//                        .turn(.8)
//                        .splineTo(new Vector2d(-46,-27), Math.toRadians(180))
//                        .splineToConstantHeading(new Vector2d(-50,-50), Math.toRadians(0))
//                        .turn(.8)
//                .build();
//
//        Action blockPush = robot.db.drive.actionBuilder(robot.db.drive.localizer.getPose())
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(34, -37, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(40, -15, Math.toRadians(270)), Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(44, -51, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(50, -15, Math.toRadians(270)), Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(50, -51, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(62, -17, Math.toRadians(270)), Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(62, -51, Math.toRadians(270)), Math.toRadians(270))
//                .build();
//        while (!isStarted() && !isStopRequested()) {
////            robot.scoringSystem.resetMechs();
//
//            robot.loop(aimPad1, aimPad2);
//
//            aimPad1.update(gamepad1);
//            aimPad2.update(gamepad2);
//
//            robot.telemetry(telemetry);
//
//            telemetry.update();
//        }
//
//        while (opModeIsActive()) {
//
////            new TrajectoryBuilder(new Pose2d(0,0,0) I tried to put this in the sequential action
//            Actions.runBlocking(
//                    new ParallelAction(
//                            (telemetryPacket) -> {
//                                robot.loop(aimPad1, aimPad2);
//                                return !isDone;
//                            },
//                            new SequentialAction(
//                                    (telemetryPacket) -> {
//                                        robot.pivot.setTargetPosition(Pivot.pivotPosition.PERP);
//                                        return false;
//                                    },
//                                    new SleepAction(2),
//                                    traj2,
//                                    (telemetryPacket) -> {
//                                        robot.pivot.setTargetPosition(Pivot.pivotPosition.HANG);
//                                        return false;
//                                    },
//                                    new SleepAction(2),
//                                    (telemetryPacket) -> {
//                                        isDone = true;
//                                        return false;
//                                    }
//                            )
//                    )
////                    new SequentialAction(traj1,traj2)
//            );
//            break;
//        }
//
//    }
//}
