package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Robot;


@Autonomous(name = "Auto high basket park", group = "AAA_COMP", preselectTeleOp="teleop")
public final class Autohighbasketpark extends LinearOpMode {

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
        Action traj1 = robot.db.drive.actionBuilder(constants.startingPose)
                .splineTo(new Vector2d(4, -32), Math.toRadians(90))
                .strafeTo(new Vector2d(30,-40))
                .splineToConstantHeading(new Vector2d(40, -10), Math.toRadians(0))
                .splineTo(new Vector2d(45, -10), Math.toRadians(0))
                .strafeTo(new Vector2d(48,-60))
                .splineToConstantHeading(new Vector2d(55, -10), Math.toRadians(0))
                .strafeTo(new Vector2d(55,-60))
                .splineToConstantHeading(new Vector2d(62, -10), Math.toRadians(0))
                .strafeTo(new Vector2d(62,-60))
                .build();
//                .strafeTo(constants.test.position)
//                .build();

        Action traj2 = robot.db.drive.actionBuilder(constants.zero)
                .waitSeconds(25)

                .strafeTo(constants.test.position)
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
                    traj2
//                    new SequentialAction(traj1,traj2)
            );


        }

    }
}
