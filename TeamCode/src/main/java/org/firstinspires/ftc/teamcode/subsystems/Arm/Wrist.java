package org.firstinspires.ftc.teamcode.subsystems.Arm;

import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.subsystems.sds.ServoState;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Wrist extends Mechanism {
   //TODO basically copy this exact class for anyother servos in the arm
   public StateDrivenServo wrist;
   //declare all servostates

   ServoState LEFT = new ServoState(0.25);
   ServoState CENTER = new ServoState(0.5);
   ServoState RIGHT = new ServoState(0.75);


   @Override
   public void init (HardwareMap hwMap) {
      wrist = new StateDrivenServo(new ServoState[]{CENTER, LEFT, RIGHT}, CENTER, ConfigInfo.wrist.getDeviceName());
      wrist.init(hwMap);
   }
   @Override
   public void loop(AIMPad aimpad) {
      wrist.loop(aimpad);
   }

   @Override
   public void telemetry(Telemetry telemetry) {
      wrist.telemetry(telemetry);
   }

   public void rotateCenter() {
      wrist.setActiveTargetState(CENTER);
   }

   public void rotateLeft() {
      wrist.setActiveTargetState(LEFT);
   }
   public void rotateRight() {
      wrist.setActiveTargetState(RIGHT);
   }

   public void toggleRight() {
      if (wrist.getActiveTargetState() == LEFT) {
         wrist.setActiveTargetState(CENTER);
      } else if (wrist.getActiveTargetState() == CENTER) {
         wrist.setActiveTargetState(RIGHT);
      }
   }
   public void toggleLeft() {
      if (wrist.getActiveTargetState() == RIGHT) {
         wrist.setActiveTargetState(CENTER);
      } else if (wrist.getActiveTargetState() == CENTER) {
         wrist.setActiveTargetState(LEFT);
      }
   }

   public void custom(double position) {
      wrist.setActiveStateCustom(position);
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
