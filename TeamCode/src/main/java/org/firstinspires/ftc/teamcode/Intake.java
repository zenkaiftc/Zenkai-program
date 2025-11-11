package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor Intake;

    public Intake(HardwareMap hardwareMap) {
        Intake = hardwareMap.get(DcMotor.class, "inTake");
        Intake.setDirection(DcMotor.Direction.FORWARD);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void coletar() {
        Intake.setPower(1.0);
    }

    public void ejetar() {
        Intake.setPower(-1.0);
    }

    public void parar() {
        Intake.setPower(0.0);
    }

    public boolean isAtivo() {
        return Intake.getPower() != 0;
    }
}
