package frc.robot.lib

import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.units.Units
import edu.wpi.first.units.measure.AngularVelocity
import edu.wpi.first.units.measure.LinearVelocity
import edu.wpi.first.wpilibj.XboxController

private val upAdjust: Double = 0.5
private val downAdjust: Double = 0.25
private val maxSpeed: LinearVelocity = Units.MetersPerSecond.of(3.0)
private val maxRotSpeed: AngularVelocity = Units.RadiansPerSecond.of(2.0 * Math.PI)

// TW: I think we should investigate just how useful these adjustments are. They take up two useful
// TW: buttons on the controller.
// TW: This year, the frc.robot will be moving at a reduced speed, and the cycles are short, so I
// TW: suspect we won't have much need for faster and slower
fun XboxController.calculateSpeeds(): ChassisSpeeds {
    val y = -leftY
    val x = -leftX
    val rot = -rightX
    val triggerAdjust =
        (1 - upAdjust) + (rightTriggerAxis * upAdjust) - (leftTriggerAxis * downAdjust)
    return ChassisSpeeds(
        maxSpeed * x * triggerAdjust,
        maxSpeed * y * triggerAdjust,
        maxRotSpeed * rot * triggerAdjust,
    )
}
