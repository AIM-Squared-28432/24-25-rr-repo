package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.control.FeedforwardController;
import com.aimrobotics.aimlib.control.LowPassFilter;
import com.aimrobotics.aimlib.control.PIDController;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.control.SimpleControlSystem;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;

public class LinLifts extends Mechanism {
    private final double kP = .001;
    private final double kI = 0;
    private final double kD = 0;
    private final double derivativeLowPassGain = 0;
    private final double integralSumMax = 0;
    private final double kV = 0;
    private final double kA = 0.0;
    private final double kStatic = 0.0;
    private final double kCos = 0.0;
    private final double kG = 0.0;
    private final double lowPassGain = 0;
    public DcMotorEx leftSlide;
    public DcMotorEx rightSlide;

    private DcMotorEx activeEncoderMotor;
    // set to a motor
    private double lastActiveEncoderPosition;

    private double activeTargetPosition = 0;
    private double manualPower = 0;

    private final SimpleControlSystem controlSystem;
    private static final double MINIMUM_POWER = 0.03;


    enum SlidePosition {
        FLOOR(0),
        MID(100),
        HIGH(300);

        private final int position;
        SlidePosition(int position) {
            this.position = position;
        }

    }

    public LinLifts() {
        PIDController pidController = new PIDController(kP, kI, kD, derivativeLowPassGain, integralSumMax);
        FeedforwardController feedforwardController = new FeedforwardController(kV, kA, kStatic, kCos, kG);
        LowPassFilter lowPassFilter = new LowPassFilter(lowPassGain);
        controlSystem = new SimpleControlSystem(pidController, feedforwardController, lowPassFilter);
        controlSystem.setTarget(SlidePosition.FLOOR.position);
    }
    @Override
    public void init(HardwareMap hwMap) {
        leftSlide = hwMap.get(DcMotorEx.class, ConfigInfo.leftSlide.getDeviceName());
        rightSlide = hwMap.get(DcMotorEx.class, ConfigInfo.rightSlide.getDeviceName());

        setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

//        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);


        activeEncoderMotor = leftSlide;
    }

    @Override
    public void loop (AIMPad gamepad) {
//        applyManualPower();
        update();
    }

    /**
     * Set the mode of the slides
     * @param mode the mode to set the slides to
     */
    public void setMode(DcMotorEx.RunMode mode) {
        leftSlide.setMode(mode);
        rightSlide.setMode(mode);
    }

    /**
     * Set the zero power behavior of the slides
     * @param behavior the zero power behavior to set the slides to
     */
    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
        leftSlide.setZeroPowerBehavior(behavior);
        rightSlide.setZeroPowerBehavior(behavior);
    }


    /**
     * Get the output power of the slides based on the system update for the active target position
     * @return the output power for the slide motors
     */
    private double getTargetOutputPower() {
        return controlSystem.update(getCurrentPosition());
    }


// not a way of setting power but updating the outb[ut according to how it was previously
    /**
     * Update the power of the slides based on the control system's output
     */
    private void update() {
        double power = getTargetOutputPower();
        setPower(power);
    }


    /**
     * Hold the position of the slides
     */
    private void holdPosition() {
        setTargetPosition(getLastPosition());
        update();
    }



    /**
     * Set the target position for the slides
     * @param targetPosition the target position for the slides
     */
    public void setTargetPosition(double targetPosition) {
        activeTargetPosition = targetPosition;
        controlSystem.setTarget(activeTargetPosition);
    }

    /**
     * Set the power of the slides and update the last position
     * Only updates the last position when the slides are running vs. every loop
     * Slides will likely be running every loop
     * @param power the power to set the slides to
     */
    private void setPower(double power) {
        leftSlide.setPower(power);
        rightSlide.setPower(power);
        updateLastPosition();
    }
    /**
     * Update the manual power of the slides
     * @param power the power to set the slides to
     */
    public void updateManualPower(double power) {
        manualPower = power;
    }
    /**
     * Apply a preset manual power to the slides. If the power is below the minimum power threshold, hold the position.
     * Use with updateManualPower() to set the manual power
     */
    private void applyManualPower() {
        if (Math.abs(manualPower) > MINIMUM_POWER) {
            setPower(manualPower);
        } else {
            holdPosition();
        }
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


//    public void updateLift () {
//        double error = targetPosition - lift.getCurrentPosition();
//
//        double power = error * kP;
//        lift.setPower(power);
//    }
//    void setLift (double target) {
//        targetPosition = target;
//    }
}
