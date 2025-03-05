package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Robot;


@Autonomous(name = "Auto push blocks", group = "AAA_COMP", preselectTeleOp="teleop")
public final class AutoPushBlocks extends LinearOpMode {

    AutoConstants constants = new AutoConstants();
    Robot robot = new Robot(constants.startingPose,true);

    AIMPad aimPad1;
    AIMPad aimPad2;

    boolean isAutoComplete = true;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        aimPad1 = new AIMPad(gamepad1);
        aimPad2 = new AIMPad(gamepad2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, constants.startingPose);

//        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
//                .strafeRight(10)
//                .forward(5)
//                .build();
//
        Action pushBlocks = robot.db.drive.actionBuilder(constants.startingPose)
                .splineToLinearHeading(new Pose2d(34, -37, Math.toRadians(270)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(40, -15, Math.toRadians(270)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(44, -51, Math.toRadians(270)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(50, -15, Math.toRadians(270)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(50, -51, Math.toRadians(270)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(62, -17, Math.toRadians(270)), Math.toRadians(270))
                                .splineToLinearHeading(new Pose2d(62, -51, Math.toRadians(270)), Math.toRadians(270))
                .build();


        while (!isStarted() && !isStopRequested()) {
//            robot.scoringSystem.resetMechs();

            robot.loop(aimPad1, aimPad2);

            aimPad1.update(gamepad1);
            aimPad2.update(gamepad2);

            robot.telemetry(telemetry);

            telemetry.update();
        }

        while (opModeIsActive()) {

//            new TrajectoryBuilder(new Pose2d(0,0,0) I tried to put this in the sequential action
            Actions.runBlocking(
                    pushBlocks
//                    new SequentialAction(traj1,traj2)
            );



        }

    }
}
