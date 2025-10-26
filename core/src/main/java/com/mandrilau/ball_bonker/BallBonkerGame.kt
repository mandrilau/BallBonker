package com.mandrilau.ball_bonker

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mandrilau.ball_bonker.entities.Ball
import com.mandrilau.ball_bonker.entities.Paddle
import com.mandrilau.ball_bonker.managers.CollisionManager
import com.mandrilau.ball_bonker.managers.GameStateManager

class BallBonkerGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont

    private lateinit var ball: Ball
    private lateinit var paddle: Paddle

    private lateinit var collisionManager: CollisionManager
    private lateinit var gameStateManager: GameStateManager

    private lateinit var ballTexture: Texture
    private lateinit var paddleTexture: Texture

    private val screenWidth = 640f
    private val screenHeight = 480f

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        font.color = Color.WHITE

        // Load textures
        ballTexture = Texture("ball.png")
        paddleTexture = Texture("paddle.png")

        // Initialize entities
        ball = Ball(
            texture = ballTexture,
            startX = screenWidth / 2 - ballTexture.width / 2,
            startY = screenHeight / 2
        )

        paddle = Paddle(
            texture = paddleTexture,
            startX = screenWidth / 2 - paddleTexture.width / 2,
            startY = 50f
        )

        // Initialize managers
        collisionManager = CollisionManager()
        gameStateManager = GameStateManager()
    }

    override fun render() {
        val delta = Gdx.graphics.deltaTime

        // Clear screen
        ScreenUtils.clear(0.1f, 0.1f, 0.2f, 1f)

        if (!gameStateManager.isGameOver()) {
            // Update game objects
            ball.update(delta, screenWidth, screenHeight)
            paddle.update(delta, screenWidth)

            // Check collisions
            if (collisionManager.checkBallPaddleCollision(ball, paddle)) {
                gameStateManager.addScore(10)
            }

            // Check if ball fell off screen
            if (ball.isOutOfBounds(screenHeight)) {
                gameStateManager.loseLife()
                if (!gameStateManager.isGameOver()) {
                    ball.reset(screenWidth / 2, screenHeight / 2)
                }
            }
        }

        // Render everything
        batch.begin()

        if (!gameStateManager.isGameOver()) {
            ball.render(batch)
            paddle.render(batch)
        }

        // Render UI
        font.draw(batch, "Score: ${gameStateManager.score}", 10f, screenHeight - 10f)
        font.draw(batch, "Lives: ${gameStateManager.lives}", 10f, screenHeight - 30f)

        if (gameStateManager.isGameOver()) {
            font.draw(batch, "GAME OVER! Final Score: ${gameStateManager.score}",
                screenWidth / 2 - 100f, screenHeight / 2)
        }

        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        ballTexture.dispose()
        paddleTexture.dispose()
    }
}
