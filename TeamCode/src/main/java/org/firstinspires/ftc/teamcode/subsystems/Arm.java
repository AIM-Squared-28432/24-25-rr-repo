package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.control.FeedforwardController;
import com.aimrobotics.aimlib.control.LowPassFilter;
import com.aimrobotics.aimlib.control.PIDController;
import com.aimrobotics.aimlib.control.SimpleControlSystem;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class Arm extends Mechanism {
    public DcMotorEx rightArm;
    public DcMotorEx leftArm;
    //LEFT OFF HERE
//    private CRServo hand;

    //ku = .012
    //tu = 1.3

    private final double kP = 0.001;
    private final double kI = 0.0001;
    private final double kD = 0.0001;
    private final  double derivativeLowPassGain = 0;
    private final double integralSumMax = 0;
    private final double kV = 0;
    private final double kA = 0;
    private final double kStatic = 0;
    private final double kCos = 0;
    private final double kG = 0;
    private final double lowPassGain = 0;
    public double activeTargetPosition = 0;
    private DcMotorEx activeEncoderMotor;
    // set to a motor
    private double lastActiveEncoderPosition;
    private final SimpleControlSystem controlSystem;
    private final double startingOffset = 0;
// what is happening

    //    Servo wrist = null;
    @Override
    public void init (HardwareMap hwMap) {
        rightArm = hwMap.get(DcMotorEx.class, ConfigInfo.rightArm.getDeviceName());
        leftArm = hwMap.get(DcMotorEx.class, ConfigInfo.leftArm.getDeviceName());
        setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightArm.setDirection(DcMotorSimple.Direction.REVERSE);
        leftArm.setDirection(DcMotorSimple.Direction.REVERSE);
        activeEncoderMotor = rightArm;
    }

    public Arm() {
        PIDController pidController = new PIDController(kP, kI, kD, derivativeLowPassGain, integralSumMax);
        FeedforwardController feedforwardController = new FeedforwardController(kV, kA, kStatic, kCos, kG);
        LowPassFilter lowPassFilter = new LowPassFilter(lowPassGain);
        controlSystem = new SimpleControlSystem(pidController, feedforwardController, lowPassFilter);
    }
    public void loop (AIMPad gamepad) {
        update();
    }

    /** Set the zero power behavior of the slides
     * @param behavior the zero power behavior to set the slides to
     */
    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
        leftArm.setZeroPowerBehavior(behavior);
        rightArm.setZeroPowerBehavior(behavior);
    }

    /**
     * Set the mode of the slides
     * @param mode the mode to set the slides to
     */
    public void setMode(DcMotorEx.RunMode mode) {
        leftArm.setMode(mode);
        rightArm.setMode(mode);
    }



    /**
     * Set the power of the slides and update the last position
     * Only updates the last position when the slides are running vs. every loop
     * Slides will likely be running every loop
     * @param power the power to set the slides to
     */
    private void setPower(double power) {
        leftArm.setPower(power);
        rightArm.setPower(power);
        updateLastPosition();
    }

    /**
     * Update the power of the slides based on the control system's output
     */
    private void update() {
        double power = getTargetOutputPower();
        setPower(power);
    }
    /**
     * Get the output power of the slides based on the system update for the active target position
     * @return the output power for the slide motors
     */
    private double getTargetOutputPower() {
        return controlSystem.update(getCurrentPosition());
    }

    /**
     * Set the target position for the slides
     * @param targetPosition the target position for the slides
     */
    public void setTargetPosition(double targetPosition) {
//        double targetTicks = degToTicks(targetPosition) - degToTicks(startingOffset);
//        activeTargetPosition = targetTicks;
        activeTargetPosition = targetPosition;
        controlSystem.setTarget(activeTargetPosition);
    }

    private double degToTicks(double targetPosition) {
        return 1425.1/360 * targetPosition;
    }

    /**
     * Hold the position of the slides
     */
    private void holdPosition() {
        setTargetPosition(getLastPosition());
        update();
    }

    /**
     * Get the current position of the slides
     * @return the current position of the slides
     */
    public double getCurrentPosition() {
        return activeEncoderMotor.getCurrentPosition();
    }
    /**
     * Get the last active encoder position
     * @return the last active encoder position
     */
    private double getLastPosition() {
        return lastActiveEncoderPosition;
    }
    /**
     * Set the last active encoder position to the encoder motor's current position
     */
    private void updateLastPosition() {
        lastActiveEncoderPosition = activeEncoderMotor.getCurrentPosition();
    }

//    void spinOut() {
//        hand.setPower(1);
//    }
//    void spinIn() {
//        hand.setPower(-1);
//    }
//    void spinOff() {
//        hand.setPower(0);
//    }
//
//    void spinAtPower(double power) {
//        hand.setPower(power);
//    }
//
//    void updateArm() {
//        double error = targetPosition - arm.getCurrentPosition();
//
//        double power = error * kP;
//        arm.setPower(power);
////        wrist.setPosition(0);
//    }
//
//    void setPosition(double target) {
//        targetPosition = target;
//    }
}

