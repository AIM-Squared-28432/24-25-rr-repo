package org.firstinspires.ftc.teamcode.subsystems.Arm;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.subsystems.sds.ServoState;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Hand extends Mechanism {
   //TODO basically copy this exact class for anyother servos in the arm
   // TODO take aims code on how the ccycle through the arm and the debuggin thing too
   public StateDrivenServo hand;
   //declare all servostates

   ServoState CLOSE = new ServoState(.8);
   ServoState OPEN = new ServoState(.2);

   @Override
   public void init (HardwareMap hwMap) {
      hand = new StateDrivenServo(new ServoState[]{CLOSE, OPEN}, CLOSE, ConfigInfo.hand.getDeviceName());
      hand.init(hwMap);
   }
   @Override
   public void loop(AIMPad aimpad) {
      hand.loop(aimpad);
   }

   @Override
   public void telemetry(Telemetry telemetry) {
      hand.telemetry(telemetry);
   }

   public void open() {
      hand.setActiveTargetState(OPEN);
   }

   public void close() {
      hand.setActiveTargetState(CLOSE);
   }

   public void custom(double position) {
      hand.setActiveStateCustom(position);
   }

   public void toggle() {
      if (hand.getActiveTargetState() == OPEN) {
         close();
      } else {
         open();
      }
   }
   public boolean isHandOpen() {
      if (hand.getActiveTargetState() == OPEN) {
         return true;
      } else {
         return false;
      }
   }

   //TODO get new statedriven servo stuff becau ei dont have get activetargetstate and just updat the sutff

//   public void toggle() {
//      if (hand.getActiveTargetState() == OPEN) {
//         close();
//      } else {
//         open();
//      }
//   }
}
