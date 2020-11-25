package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class OperatorDrive extends OpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor intake;
    private Servo latch;
    Telemetry telemetry;

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
        // double axleDifference = left.getCurrentPosition() - right.getCurrentPosition();

        intake.setPower(gamepad1.a ? -1:0);
        latch.setPosition(gamepad1.b ? 0:1);

        left.setPower(speed + turn);
        right.setPower(speed - turn);
        // left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // right.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        // left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // telemetry.addData("left", left.getCurrentPosition());
        // telemetry.addData("right", right.getCurrentPosition());

    }
}
