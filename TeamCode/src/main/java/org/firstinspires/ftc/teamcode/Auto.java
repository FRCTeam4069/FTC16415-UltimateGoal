package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Auto extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor intake;
    private DcMotor arm;
    private Servo latch;
    private Servo gripper;

    double kP = 0.0006;
    double errorLeft;
    double errorRight;

    double DIAMETER = 4; // inches
    int TICKS_PER_REV = 560;
    double INCHES_PER_TICK = (Math.PI * DIAMETER) / (double)TICKS_PER_REV;

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        arm = hardwareMap.get(DcMotor.class, "arm");
        latch = hardwareMap.get(Servo.class, "latch");
        gripper = hardwareMap.get(Servo.class, "gripper");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
//        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        while(left.getCurrentPosition() != 0 && right.getCurrentPosition() != 0) {
//            sleep(1);
//        }
//        sleep(5000);
        waitForStart();

//        left.setPower(1);
//        right.setPower(1);
//        sleep(1000);
//        left.setPower(0);
//        right.setPower(0);

        drive(left, right, 12);

    }
    public void drive(DcMotor left, DcMotor right, int inches) {
        double distance = 2900; //inches * INCHES_PER_TICK;
        errorLeft = distance - left.getCurrentPosition();
        errorRight = distance - right.getCurrentPosition();
        while((errorLeft + errorRight) / 2 > 50 && opModeIsActive()){
            errorLeft = distance - left.getCurrentPosition();
            errorRight = distance - right.getCurrentPosition();
            left.setPower(errorLeft * kP);
            right.setPower(errorRight * kP);
            telemetry.addData("distance", distance);
            telemetry.addData("errorLeft", errorLeft);
            telemetry.addData("errorRight", errorRight);
            telemetry.addData("powah", left.getPower());
            telemetry.update();
            idle();
        }
    }
}
