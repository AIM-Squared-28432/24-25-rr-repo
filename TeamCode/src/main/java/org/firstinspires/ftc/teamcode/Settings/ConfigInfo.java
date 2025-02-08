package org.firstinspires.ftc.teamcode.Settings;

import com.aimrobotics.aimlib.util.HardwareInterface;

public class ConfigInfo {
    //Slides
    public static HardwareInterface leftSlide = new HardwareInterface ("LS", false,0);
    public static HardwareInterface rightSlide = new HardwareInterface ("RS", false,0);

    //Arm
    public static HardwareInterface leftArm = new HardwareInterface ("LA", false, 0);
    public static HardwareInterface rightArm = new HardwareInterface ("RA", false, 0);

    //INTAKE
    public static HardwareInterface hand = new HardwareInterface ("HA", false, 0);
    public static HardwareInterface wrist = new HardwareInterface ("WR", false, 0);

    //ODOMETRY
    public static HardwareInterface odo = new HardwareInterface("ODO", false, 0);

    //DRIVEBASE

    public static HardwareInterface frontleftDrive = new HardwareInterface("leftFront", false,0);
    public static HardwareInterface frontrightDrive = new HardwareInterface("rightFront", false,0);
    public static HardwareInterface backleftDrive = new HardwareInterface("leftBack", false,0);
    public static HardwareInterface backrightDrive = new HardwareInterface("rightBack", false,0);
}
