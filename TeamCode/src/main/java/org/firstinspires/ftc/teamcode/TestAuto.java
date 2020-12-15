package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// @TeleOp
public class TestAuto extends LinearOpMode {
    DcMotor left;
    DcMotor right;

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        left.setPower(0.5);
        right.setPower(0.5);
        sleep(1000);
        left.setPower(0);
        right.setPower(0);
    }
}
