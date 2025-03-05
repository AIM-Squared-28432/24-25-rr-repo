//package org.firstinspires.ftc.teamcode.subsystems.Arm;
//
//import com.aimrobotics.aimlib.gamepad.AIMPad;
//import com.aimrobotics.aimlib.subsystems.sds.ServoState;
//import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
//import com.aimrobotics.aimlib.util.Mechanism;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;
//
//public class Shoulders extends Mechanism {
//   //TODO basically copy this exact class for anyother servos in the arm
//   //TODO talk to nate about two servos doing the same thing, do we need active encoder?
//   // maybe reverse a sero motor
//   public StateDrivenServo leftShoulder;
//   public StateDrivenServo rightShoulder;
//   //declare all servostates
//
//   ServoState MIDDLE = new ServoState(0);
//   ServoState BACK = new ServoState(0);
//   ServoState FRONT = new ServoState(0);
//   ServoState RIGHT = new ServoState(0);
//   ServoState SPEC_HANG = new ServoState(0);
//
//
//   @Override
//   public void init (HardwareMap hwMap) {
//      leftShoulder = new StateDrivenServo(new ServoState[]{MIDDLE, BACK, FRONT, RIGHT, SPEC_HANG}, FRONT, ConfigInfo.leftShoulder.getDeviceName());
//      rightShoulder = new StateDrivenServo(new ServoState[]{MIDDLE, BACK, FRONT, RIGHT, SPEC_HANG}, FRONT, ConfigInfo.rightShoulder.getDeviceName());
//      leftShoulder.init(hwMap);
//      rightShoulder.init(hwMap);
//   }
//   @Override
//   public void loop(AIMPad aimpad) {
//      leftShoulder.loop(aimpad);
//      rightShoulder.loop(aimpad);
//   }
//
//   @Override
//   public void telemetry(Telemetry telemetry) {
//      leftShoulder.telemetry(telemetry);
//      rightShoulder.telemetry(telemetry);
//   }
//
//   public void middle() {
//      leftShoulder.setActiveTargetState(MIDDLE);
//      rightShoulder.setActiveTargetState(MIDDLE);
//   }
//   public void right(){
//      leftShoulder.setActiveTargetState(RIGHT);
//      rightShoulder.setActiveTargetState(RIGHT);
//   }
//
//   public void back() {
//      leftShoulder.setActiveTargetState(BACK);
//      rightShoulder.setActiveTargetState(BACK);
//   }
//   public void front() {
//      leftShoulder.setActiveTargetState(FRONT);
//      rightShoulder.setActiveTargetState(FRONT);
//   }
//
//
//   public void SpecHang() {
//      leftShoulder.setActiveTargetState(SPEC_HANG);
//      rightShoulder.setActiveTargetState(SPEC_HANG);
//   }
//
//   public void toggle() {
//      if (leftShoulder.getActiveTargetState() == BACK) {
//         SpecHang();
//      } else {
//         back();
//      }
//   }
//
//   public void custom(double position) {
//      leftShoulder.setActiveStateCustom(position);
//      rightShoulder.setActiveStateCustom(position);
//   }
//
//   //TODO get new statedriven servo stuff becau ei dont have get activetargetstate and just updat the sutff
//
////   public void toggle() {
////      if (hand.getActiveTargetState() == OPEN) {
////         close();
////      } else {
////         open();
////      }
////   }
//}
