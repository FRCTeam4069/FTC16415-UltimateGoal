package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class TestOpDrive extends OpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor intake;
    private Servo latch;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        latch = hardwareMap.get(Servo.class, "latch");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
        double turn = gamepad1.left_stick_x;

        intake.setPower(gamepad1.a ? -1:0);
        latch.setPosition(gamepad1.b ? 0:1);

        left.setPower(speed + turn);
        right.setPower(speed - turn);
    }
}
