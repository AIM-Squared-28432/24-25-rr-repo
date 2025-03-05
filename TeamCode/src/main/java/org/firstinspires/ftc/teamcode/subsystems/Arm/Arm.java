package org.firstinspires.ftc.teamcode.subsystems.Arm;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends Mechanism {

    public Wrist wrist;
    public Hand hand;
    //why no new aimpad does this mean it is coming from somewhere else?

    public void init (HardwareMap hwMap) {
        //why call new stuff
        wrist = new Wrist();
        hand = new Hand();

        wrist.init(hwMap);
        hand.init(hwMap);
    }

    @Override
    public void loop(AIMPad aimPad1) {
        //why do they even need the aimpaad?
        wrist.loop(aimPad1);
        hand.loop(aimPad1);
    }

    public void neutralOpen() {
        hand.open();
        wrist.rotateCenter();
    }

    public void neutralClosed() {
        hand.close();
        wrist.rotateCenter();
    }
// MAYBE TAKE OUT HAND OPEN JUST INCASE THESE ARE ACCIDENTLY HIT
//    public void avoidHit() {
//        wrist.rotateCenter();
//    }
//
//    public void search() {
//        hand.open();
//        wrist.rotateCenter();
//    }
//
//
//    public void outtakeSamplePrep() {
//        hand.close();
//        //hand not really needed
//        wrist.rotateCenter();
//        //think about switching to left or right
//        //TODO maybe make shoulders a bit more back
//
//    }
//
//    public void intakeSpecimen() {
//        hand.open();
//        wrist.rotateCenter();
//        //TODO prob need to change shoulders to a new preset because pivot is at an angle
//    }
//
//    public void hangSpecimenPrep() {
//        hand.close();
//        wrist.rotateCenter();
//        //make slbow or shoulders a bit up so it has room to go down and latch on
//    }
//
////
////    public void hangSpecimen() {
////        hand.close();
////        wrist.rotateCenter();
////        //make slbow or shoulders a bit up so it has room to go down and latch on
////        elbow.flexNeutral();
////        shoulders.back();
////    }
//
//    public void resetNeutral() {
//        hand.close();
//        wrist.rotateCenter();
//    }
//
//    public void hang() {
//        hand.close();
//        wrist.rotateCenter();
//    }
}
