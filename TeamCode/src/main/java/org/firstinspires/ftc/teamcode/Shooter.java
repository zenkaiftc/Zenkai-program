package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotor shooter;

    public Shooter(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotor.Direction.REVERSE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void atirar() {
        shooter.setPower(1.0);
    }

    public void coletar() {
        shooter.setPower(-1.0);
    }

    public void parar() {
        shooter.setPower(0.0);
    }

    public boolean isAtivo() {
        return shooter.getPower() != 0;
    }
}
