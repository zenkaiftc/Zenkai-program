package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Shooter;
import org.firstinspires.ftc.teamcode.IMUZenkai;

@TeleOp(name = "TeleOp ZENKAI", group = "FTC")
public class ZENKAI_2025_DECODE extends LinearOpMode {

    private Drive drive;
    private Intake intake;
    private Shooter shooter;
    private IMUZenkai imu;

    @Override
    public void runOpMode() {
        drive = new Drive(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        imu = new IMUZenkai(hardwareMap, "imu", RevHubOrientationOnRobot.UsbFacingDirection.UP, RevHubOrientationOnRobot.LogoFacingDirection.FORWARD);

        telemetry.addLine("Pronto para iniciar");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            controlarDrive();
            controlarIntake();
            controlarShooter();
            atualizarTelemetria();
        }
    }

    private void controlarDrive() {
        double driveInput = gamepad1.left_stick_y;
        double turnInput = -gamepad1.right_stick_x;
        drive.mover(driveInput, turnInput);
    }

    private void controlarIntake() {
        if (gamepad2.right_trigger > 0) {
            intake.coletar();
        } else if (gamepad2.left_trigger > 0) {
            intake.ejetar();
        } else {
            intake.parar();
        }
    }

    private void controlarShooter() {
        if (gamepad2.right_bumper) {
            shooter.atirar();
        } else if (gamepad2.left_bumper) {
            shooter.coletar();
        } else {
            shooter.parar();
        }
    }

    private void atualizarTelemetria() {
        telemetry.addData("Left Power", drive.getLeftPower());
        telemetry.addData("Right Power", drive.getRightPower());
        telemetry.addData("Intake", intake.isAtivo() ? "Ativo" : "Parado");
        telemetry.addData("Shooter", shooter.isAtivo() ? "Ativo" : "Parado");
        telemetry.update();
    }
}
