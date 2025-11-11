package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Drive {
    private DcMotor leftDrive, rightDrive;

    public Drive(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "leftMotor");
        rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void mover(double drive, double turn) {
        double leftPower = Range.clip(drive + turn, -1.0, 1.0);
        double rightPower = Range.clip(drive - turn, -1.0, 1.0);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
    }

    public double getLeftPower() {
        return leftDrive.getPower();
    }

    public double getRightPower() {
        return rightDrive.getPower();
    }
}
