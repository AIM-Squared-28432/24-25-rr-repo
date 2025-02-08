package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.subsystems.sds.ServoState;
import com.aimrobotics.aimlib.subsystems.sds.StateDrivenServo;
import com.aimrobotics.aimlib.util.Mechanism;
import com.aimrobotics.aimlib.control.FeedforwardController;
import com.aimrobotics.aimlib.control.LowPassFilter;
import com.aimrobotics.aimlib.control.PIDController;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.control.SimpleControlSystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.hardware.android.GpioPin;
import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Intake extends Mechanism {

    public CRServo hand;
    public StateDrivenServo wrist;
    String WristActiveTargetState;

    ServoState STRAIGHT = new ServoState(.1);
    ServoState SIDE = new ServoState(.4);
//hand is not a state driven servo it is a crservo
    @Override
    public void init(HardwareMap hwMap) {
        hand = hwMap.get(CRServo.class, ConfigInfo.hand.getDeviceName());
        wrist = new StateDrivenServo(new ServoState[]{STRAIGHT, SIDE}, SIDE, ConfigInfo.wrist.getDeviceName());

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

    public void spinIn() {
        hand.setPower(-1);
    }
    public void spinOut() {
        hand.setPower(1);
    }

    public void spinOff() {
        hand.setPower(0);
    }

    public void straight() {
        wrist.setActiveTargetState(STRAIGHT);
        WristActiveTargetState = "Straight" ;
    }
    public void side() {
        wrist.setActiveTargetState(SIDE);
        WristActiveTargetState = "Side" ;
    }
    public void wristcustom(double position) {
        wrist.setActiveStateCustom(position);
    }
//prob wont use this because i can just pull the variable
    public String getWristActiveTargetState () {
        return WristActiveTargetState;
    }

}
