package org.firstinspires.ftc.teamcode.Settings;

import com.aimrobotics.aimlib.util.HardwareInterface;

public class ConfigInfo {
    //Slides
    public static HardwareInterface leftSlide = new HardwareInterface ("LL", false,0);
    public static HardwareInterface rightSlide = new HardwareInterface ("RL", false,0);

    //Pivot (rename all stuff later)
    public static HardwareInterface leftArm = new HardwareInterface ("LR", false, 0);
    public static HardwareInterface rightArm = new HardwareInterface ("RR", false, 0);

    //INTAKE
    public static HardwareInterface hand = new HardwareInterface ("HA", false, 0);
    public static HardwareInterface wrist = new HardwareInterface ("WR", false, 0);

    //DRIVEBASE
    public static HardwareInterface frontleftDrive = new HardwareInterface("leftFront", false,0);
    public static HardwareInterface frontrightDrive = new HardwareInterface("rightFront", false,0);
    public static HardwareInterface backleftDrive = new HardwareInterface("leftBack", false,0);
    public static HardwareInterface backrightDrive = new HardwareInterface("rightBack", false,0);
}
