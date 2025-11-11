package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Shooter;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.IMUZenkai;


@Autonomous(name="Autonomo Blue Gol", group="FTC")
public class autonomousbluegol extends LinearOpMode {

    private DcMotor leftMotor, rightMotor;
    private Shooter shooter;
    private Intake Intake;

    // private RevHubOrientationOnRobot.LogoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
    // priv

    private ElapsedTime runtime = new ElapsedTime();
    private IMUZenkai imu;

    // Velocidades pré-definidas
    static final double FRONT = 0.20;
    static final double BACK = -0.20;

    @Override
    public void runOpMode() {

        // Inicialização do hardware
        leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        imu = new IMUZenkai(hardwareMap, "imu",
                RevHubOrientationOnRobot.UsbFacingDirection.UP, RevHubOrientationOnRobot.LogoFacingDirection.LEFT
        );
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);


        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Hardware Inicializado. Pronto para iniciar.");
        telemetry.update();
        imu.resetYaw();

        // Espera o start
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Yaw", (imu.getYaw() * -1));
            telemetry.update();
        }
        runtime.reset();


        telemetry.addData("Etapa", "1: Movendo para frente e atirando");
        telemetry.update();

        shooter.atirar();
        moveRobot(BACK, BACK, 0.9);
        stopDrive();
        sleep(7500);
        Intake.coletar();



        sleep(700);
        stopDrive();
        sleep(200);
        shooter.parar();
        Intake.parar();

        moveRobot(BACK, BACK, 0.5);
        imu.resetYaw();
        if (imu.getYaw() > -30) {
            leftMotor.setPower(-0.3);
            rightMotor.setPower(0.3);
        }

        stopRobot();
    }

    private void moveRobot(double leftPower, double rightPower, double durationSeconds) {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
        sleep((long)(durationSeconds * 1000));
    }

    private void stopDrive() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    private void stopRobot() {
        stopDrive();
        Intake.parar();
        shooter.parar();
    }


}
