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
//public class Elbow extends Mechanism {
//   //TODO basically copy this exact class for anyother servos in the arm
//   public StateDrivenServo elbow;
//   //declare all servostates
//
//   ServoState UP = new ServoState(0);
//   ServoState SPEC_HANG = new ServoState(0);
//   ServoState NEUTRAL = new ServoState(0);
//   ServoState DOWN = new ServoState(0);
//
//
//   @Override
//   public void init (HardwareMap hwMap) {
//      elbow = new StateDrivenServo(new ServoState[]{UP, NEUTRAL, DOWN, SPEC_HANG}, NEUTRAL, ConfigInfo.elbow.getDeviceName());
//      elbow.init(hwMap);
//   }
//   @Override
//   public void loop(AIMPad aimpad) {
//      elbow.loop(aimpad);
//   }
//
//   @Override
//   public void telemetry(Telemetry telemetry) {
//      elbow.telemetry(telemetry);
//   }
//
//   public void flexNeutral() {
//      elbow.setActiveTargetState(NEUTRAL);
//   }
//
//   public void flexUp() {
//      elbow.setActiveTargetState(UP);
//   }
//   public void flexDown() {elbow.setActiveTargetState(DOWN);}
//
//   public void SpecHang() {
//      elbow.setActiveTargetState(SPEC_HANG);
//   }
//
//   public void toggle() {
//      if (elbow.getActiveTargetState() == NEUTRAL) {
//         SpecHang();
//      } else {
//         flexNeutral();
//      }
//   }
//
//   public void custom(double position) {
//      elbow.setActiveStateCustom(position);
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
