package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class TestOpDrive extends OpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor intake;
    DcMotor arm;
    private Servo latch;
    Servo gripper;
    boolean oldLatch = false;
    boolean savedLatchPos = true;
    boolean oldGrippper = false;
    boolean savedGripperPos = false;
    boolean oldBumper = false;

//    boolean oldB = false;
//    boolean latchPos = false;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        arm = hardwareMap.get(DcMotor.class, "arm");
        latch = hardwareMap.get(Servo.class, "latch");
        gripper = hardwareMap.get(Servo.class, "gripper");
        right.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        double speed = (gamepad1.right_trigger - gamepad1.left_trigger) + ((gamepad1.right_bumper?1:0) + (gamepad1.left_bumper?-1:0));
        double turn = (-1 * gamepad1.left_stick_x) + (-0.5*gamepad1.right_stick_x);

        intake.setPower((gamepad2.right_trigger) - (gamepad2.left_trigger));
        arm.setPower(-1*gamepad2.left_stick_y);
        //latch.setPosition(gamepad2.x ? 0.5:0);
        //gripper.setPosition(gamepad2.y ? 0.3:0);
        // latch.setPosition(gamepad1.b ? 0:1);
        if(gamepad2.a && !oldLatch) {
            latch.setPosition(!savedLatchPos ? 0.5:0);
            savedLatchPos = !savedLatchPos;
        }
        if(gamepad2.b && !oldGrippper) {
            gripper.setPosition(!savedGripperPos ? 0.3:0);
            savedGripperPos = !savedGripperPos;
        }
        if(gamepad2.right_bumper && !oldBumper) {
            savedLatchPos = false;
        }

//        if(gamepad1.b && !oldB) {
//            latch.setPosition(latchPos ? 0.5:0);
//        }

        left.setPower(speed + turn);
        right.setPower(speed - turn);

//        oldB = gamepad1.b;

        oldLatch = gamepad2.a;
        oldGrippper = gamepad2.b;
        oldBumper = gamepad2.right_bumper;

        telemetry.addData("latch", latch.getPosition());
        telemetry.addData("gripper", gripper.getPosition());
        telemetry.update();
    }
}
