package com.mandrilau.ball_bonker.entities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

/** Class responsible for all Ball behaviors:
- Movement and velocity
- Wall bouncing
- Boundary checking
- Reset functionality */

class Ball(
    private val texture: Texture,
    startX: Float,
    startY: Float,
    private val speed: Float = 300f
) {
    val position = Vector2(startX, startY)
    val velocity = Vector2(speed, speed)
    val bounds = Rectangle(startX, startY, texture.width.toFloat(), texture.height.toFloat())

    fun update(delta: Float, screenWidth: Float, screenHeight: Float) {
        position.x += velocity.x * delta
        position.y += velocity.y * delta

        // Update bounds
        bounds.setPosition(position)

        // Bounce off walls
        if (position.x <= 0 || position.x + texture.width >= screenWidth) {
            velocity.x *= -1
            position.x = position.x.coerceIn(0f, screenWidth - texture.width)
        }

        // Bounce off ceiling
        if (position.y + texture.height >= screenHeight) {
            velocity.y *= -1
            position.y = screenHeight - texture.height
        }
    }

    fun render(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y)
    }

    fun bounceY() {
        velocity.y *= -1
    }

    fun reset(x: Float, y: Float) {
        position.set(x, y)
        velocity.set(speed, speed)
    }

    fun isOutOfBounds(screenHeight: Float): Boolean {
        return position.y < 0
    }
}
