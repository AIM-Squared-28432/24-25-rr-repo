package org.firstinspires.ftc.teamcode.subsystems;

import com.aimrobotics.aimlib.control.FeedforwardController;
import com.aimrobotics.aimlib.control.LowPassFilter;
import com.aimrobotics.aimlib.control.PIDController;
import com.aimrobotics.aimlib.control.SimpleControlSystem;
import com.aimrobotics.aimlib.gamepad.AIMPad;
import com.aimrobotics.aimlib.util.Mechanism;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Settings.ConfigInfo;
import org.firstinspires.ftc.teamcode.Settings.GamepadSettings;

public class Pivot extends Mechanism {
    public DcMotorEx rightPivot;
    public DcMotorEx leftPivot;

    // CONTROLLER CONSTANTS
    //TODO tune pivot and slides and other stuff
    private final double kP = 0.00045;
    private final double kI = 0.0000082;
    private final double kD = 0.00007;
    private final  double derivativeLowPassGain = 0;
    private final double integralSumMax = 36585.3658537;
    private final double kV = 0;
    private final double kA = 0;
    private final double kStatic = 0;
    private final double kCos = 0;
    private final double kG = 0;
    private final double lowPassGain = 0;
    private final SimpleControlSystem controlSystem;
    //==========================================================

    public double activeTargetPosition = 0;
    private DcMotorEx activeEncoderMotor;
    private double lastActiveEncoderPosition;
    private double MINIMUM_POWER = .3;
    private double manualPower = 0;

    private final double startingOffset = 0;

    // PIVOT TARGET POS ENUMS
    public enum pivotPosition {
        PERP(0),
        ABOVE_PAR(220),
        PAR(320),
        HANG_SPECIMEN(100),
        LOW_BUCKET(100),
        HANG(150);

        public final double position;
        pivotPosition(double position) {this.position = position;}

    }
    public pivotPosition activePivotTarget = pivotPosition.PERP;

    @Override
    public void init (HardwareMap hwMap) {
        rightPivot = hwMap.get(DcMotorEx.class, ConfigInfo.rightArm.getDeviceName());
        leftPivot = hwMap.get(DcMotorEx.class, ConfigInfo.leftArm.getDeviceName());
        setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);
        leftPivot.setDirection(DcMotorSimple.Direction.REVERSE);
        activeEncoderMotor = rightPivot;
    }

    public Pivot() {
        PIDController pidController = new PIDController(kP, kI, kD, derivativeLowPassGain, integralSumMax);
        FeedforwardController feedforwardController = new FeedforwardController(kV, kA, kStatic, kCos, kG);
        LowPassFilter lowPassFilter = new LowPassFilter(lowPassGain);
        controlSystem = new SimpleControlSystem(pidController, feedforwardController, lowPassFilter);
    }
    public void loop (AIMPad gamepad) {
        update();
        applyManualPower();
    }

    /** Set the zero power behavior of the slides
     * @param behavior the zero power behavior to set the slides to
     */
    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior) {
        leftPivot.setZeroPowerBehavior(behavior);
        rightPivot.setZeroPowerBehavior(behavior);
    }

    /**
     * Set the mode of the slides
     * @param mode the mode to set the slides to
     */
    public void setMode(DcMotorEx.RunMode mode) {
        leftPivot.setMode(mode);
        rightPivot.setMode(mode);
    }

    /**
     * Set the power of the slides and update the last position
     * Only updates the last position when the slides are running vs. every loop
     * Slides will likely be running every loop
     * @param power the power to set the slides to
     */
    public void setPower(double power) {
        leftPivot.setPower(power);
        rightPivot.setPower(power);
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
//     * @param targetPreset the target position for the slides
     */
    public void setTargetPosition(Pivot.pivotPosition targetPreset) {
        activeTargetPosition = targetPreset.position;
        controlSystem.setTarget(activeTargetPosition);
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

    public boolean isAtTargetPosition () {
        return Math.abs(getCurrentPosition() - activeTargetPosition) < GamepadSettings.PROXIMITY_THRESHOLD;
    }

    public void updateManualPower(double power) {
        manualPower = power;
    }
    public void applyManualPower() {
        if (Math.abs(manualPower) > MINIMUM_POWER) {
            setPower(manualPower);
        }
    }
}

