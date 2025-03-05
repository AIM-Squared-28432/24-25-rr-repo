package com.example.meeps;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepsTesting {

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 15)
                //TODO change dimentions
                .setDimensions(18, 16)
                .build();
//        RED SIDE Sample
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, -62, Math.toRadians(270)))

//                        SPECIMEN AUTO
//                push blocks
                //this one is trash
                        .splineToSplineHeading(new Pose2d(36.5, -30, Math.toRadians(90)), Math.toRadians(90))
//                        .setTangent(90)
////                        .splineToLinearHeading(new Pose2d(40,-15, Math.toRadians(90)), Math.toRadians(0))
////                        .splineToLinearHeading(new Pose2d(45, -15, Math.toRadians(90)), Math.toRadians(270))
////                        .strafeTo(new Vector2d(45,-58))
////                        .setTangent(90)
////                        .strafeTo(new Vector2d(46, -15))
////                        .splineToLinearHeading(new Pose2d(54, -15, Math.toRadians(90)), Math.toRadians(270))
////                        .strafeTo(new Vector2d(54,-58))
////                        .setTangent(90)
////                        .splineToLinearHeading(new Pose2d(62, -15, Math.toRadians(90)), Math.toRadians(0))
////                        .strafeTo(new Vector2d(62, -58))
//                //TODO GOOD ONE
//                //block push
//
//                        .setTangent(0)
//                        .
//                .splineToLinearHeading(new Pose2d(34, -37, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(40, -15, Math.toRadians(270)), Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(44, -51, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(50, -15, Math.toRadians(270)), Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(50, -51, Math.toRadians(270)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(62, -17, Math.toRadians(270)), Math.toRadians(270))
//                                .splineToLinearHeading(new Pose2d(62, -51, Math.toRadians(270)), Math.toRadians(270))
//
//
//
//                //Spec hang
//
////                        .splineToConstantHeading(new Vector2d (36, -63), Math.toRadians(270))
////
////                                .strafeToLinearHeading(new Vector2d(5, -34), Math.toRadians(90))
////                                .strafeToLinearHeading(new Vector2d(36, -63), Math.toRadians(270))
////
////                .strafeToLinearHeading(new Vector2d(6, -34), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(36, -63), Math.toRadians(270))
////
////                .strafeToLinearHeading(new Vector2d(7, -34), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(36, -63), Math.toRadians(270))
////
////                .strafeToLinearHeading(new Vector2d(8, -34), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(36, -63), Math.toRadians(270))
////
////                .strafeToLinearHeading(new Vector2d(9, -34), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(50, -60), Math.toRadians(90))
                                .build());}
//
//// TODO END
//
//                //TODO LOW Bucket
//
//
////        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, -62, Math.toRadians(270)))
////
////                        .setTangent(90)
////                        .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
////                                .strafeToLinearHeading(new Vector2d(-48, -40), Math.toRadians(90))
////                        .waitSeconds(.5)
////
////                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
////                .strafeToLinearHeading(new Vector2d(-58, -40), Math.toRadians(90))
////                .waitSeconds(.5)
////
////
////                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
////                .strafeToLinearHeading(new Vector2d(-58, -27), Math.toRadians(180))
////
////
////
////
////                .build());
//
//
//
//
//
//
//
//
//        //TODO End
//                //spec hang
//                //1 slow
////                        .setTangent(130)
////                        .splineToLinearHeading(new Pose2d(58, -54, Math.toRadians(90)), Math.toRadians(180))
////                        .splineToSplineHeading(new Pose2d(35, -63, Math.toRadians(270)), Math.toRadians(270))
////                        .strafeTo(new Vector2d(29, -50))
////                        .splineToSplineHeading(new Pose2d(5, -30, Math.toRadians(90)), Math.toRadians(90))
////                        .strafeTo(new Vector2d(15, -35))
////                        .setTangent(270)
////                        .splineToSplineHeading(new Pose2d(35, -63, Math.toRadians(270)), Math.toRadians(270))
////                        .setTangent(90)
////                        .splineToSplineHeading(new Pose2d(5, -30, Math.toRadians(90)), Math.toRadians(90))
////                        .setTangent(270)
////                        .splineToSplineHeading(new Pose2d(35, -63, Math.toRadians(270)), Math.toRadians(270))
////
//
//
//                        // Specimen Hang
////                .strafeTo(new Vector2d(-10, -33))
////                .waitSeconds(2)
////
////                        // Right Block Pickup AND Drop
////
////                .splineToLinearHeading(new Pose2d(-10, -42, Math.toRadians(270)), Math.toRadians(180))
////                .splineToLinearHeading(new Pose2d(-48, -48, Math.toRadians(270)), Math.toRadians(180))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(225)), Math.toRadians(-180))
////                .waitSeconds(3.5)
////
////                        // Middle Block Pickup AND Drop
////                .splineToLinearHeading(new Pose2d(-58, -48, Math.toRadians(270)), Math.toRadians(-180))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(225)), Math.toRadians(-180))
////                .waitSeconds(3.5)
////
////                        // Left Block Pickup AND Drop
////                .splineToLinearHeading(new Pose2d(-56, -44, Math.toRadians(310)), Math.toRadians(-180))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(225)), Math.toRadians(180))
////
////                .build());
//
//
//        //RED SIDE Specimen
////        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, -62, Math.toRadians(90)))
////                // Specimen Hang 1
////                .strafeTo(new Vector2d(10, -33))
////
//////                // Left Block PUSH
////                .splineToLinearHeading(new Pose2d(22, -38, Math.toRadians(90)), Math.toRadians(0))
////                .splineToLinearHeading(new Pose2d(36, -26, Math.toRadians(90)), Math.toRadians(90))
////                .splineToLinearHeading(new Pose2d(36, -6, Math.toRadians(90)), Math.toRadians(0))
////                .splineToLinearHeading(new Pose2d(42, -48, Math.toRadians(90)), Math.toRadians(90))
////
//////                 Middle Block PUSH
////                .splineToLinearHeading(new Pose2d(50, -13, Math.toRadians(90)), Math.toRadians(0))
////                .splineToLinearHeading(new Pose2d(54, -50, Math.toRadians(90)), Math.toRadians(90))
////
//////                 Right Block PUSH
////                .splineToLinearHeading(new Pose2d(57, -10, Math.toRadians(90)), Math.toRadians(0))
////                .splineToLinearHeading(new Pose2d(61, -25, Math.toRadians(90)), Math.toRadians(100000000))
////                .splineToLinearHeading(new Pose2d(61, -55, Math.toRadians(90)), Math.toRadians(100000000))
////                        .build());
//
////
//////                 HANG 2
////                .strafeTo(new Vector2d(42, -63))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(8, -33))
////                .waitSeconds(1)
////
//////                 HANG 3
////                .strafeTo(new Vector2d(42, -63))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(4, -33))
////                .waitSeconds(1)
////
//////                 HANG 4
////                .strafeTo(new Vector2d(42, -63))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(0, -33))
////
////                .build());
//
////BLUE SIDE Sample NEEDS WORK
////        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, 62, Math.toRadians(270)))
//                // Specimen Hang
////                .strafeTo(new Vector2d(10, 34.5))
////                .waitSeconds(2)
////
////                // Right Block Pickup AND Drop
////                .splineToLinearHeading(new Pose2d(10, 42, Math.toRadians(90)), Math.toRadians(360))
////                .splineToLinearHeading(new Pose2d(48, 40, Math.toRadians(90)), Math.toRadians(-70))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(45)), Math.toRadians(90))
////                .waitSeconds(3.5)
////
////                // Middle Block Pickup AND Drop
////                .splineToLinearHeading(new Pose2d(58, 40, Math.toRadians(90)), Math.toRadians(90))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(45)), Math.toRadians(90))
////                .waitSeconds(3.5)
////
////                // Left Block Pickup AND Drop
////                .splineToLinearHeading(new Pose2d(56, 25, Math.toRadians(180)), Math.toRadians(-90))
////                .waitSeconds(2)
////                .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(45)), Math.toRadians(90))
//
////                .build());
//
//
////        //BLUE SIDE Specimen
////        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, 61.5, Math.toRadians(270)))
////                // Specimen Hang 1
////                .strafeTo(new Vector2d(-10, 34.5))
////                .waitSeconds(1)
////
//////         Block A (RIGHT) PUSH
////                .splineToLinearHeading(new Pose2d(-26, 42, Math.toRadians(270)), Math.toRadians(180))
////                .splineToLinearHeading(new Pose2d(-36, 20, Math.toRadians(270)), Math.toRadians(270))
////                .splineToLinearHeading(new Pose2d(-38, 10, Math.toRadians(270)), Math.toRadians(180))
////                .splineToLinearHeading(new Pose2d(-40, 48, Math.toRadians(270)), Math.toRadians(270))
////
//////                Block B (MIDDLE) PUSH
////                .splineToLinearHeading(new Pose2d(-48, 13, Math.toRadians(270)), Math.toRadians(180))
////                .splineToLinearHeading(new Pose2d(-50, 45, Math.toRadians(270)), Math.toRadians(270))
////
//////                Block C (LEFT) PUSH
////                .splineToLinearHeading(new Pose2d(-60, 10, Math.toRadians(270)), Math.toRadians(-1000000000))
////                .strafeTo(new Vector2d(-60.5, 50))
////
//////                 HANG 2
////                .strafeTo(new Vector2d(-42, 60))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(-8, 34.5))
////                .waitSeconds(1)
////
//////                 HANG 3
////                .strafeTo(new Vector2d(-42, 60))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(-5, 34.5))
////                .waitSeconds(1)
////
//////                 HANG 4
////                .strafeTo(new Vector2d(-42, 60))
////                .waitSeconds(1)
////                .strafeTo(new Vector2d(-2, 34.5))
//////
////                .build());
//
//        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
    }
//}
