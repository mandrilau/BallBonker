package com.mandrilau.ball_bonker.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

class Paddle(
    private val texture: Texture,
    startX: Float,
    startY: Float,
    private val speed: Float = 400f
) {
    val position = com.badlogic.gdx.math.Vector2(startX, startY)
    val bounds = Rectangle(startX, startY, texture.width.toFloat(), texture.height.toFloat())

    fun update(delta: Float, screenWidth: Float) {
        // Handle input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta
        }

        // Keep paddle within screen bounds
        position.x = position.x.coerceIn(0f, screenWidth - texture.width)

        // Update bounds
        bounds.setPosition(position)
    }

    fun render(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y)
    }
}
