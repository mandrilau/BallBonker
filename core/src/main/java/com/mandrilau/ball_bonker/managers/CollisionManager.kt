package com.mandrilau.ball_bonker.managers

import com.mandrilau.ball_bonker.entities.Ball
import com.mandrilau.ball_bonker.entities.Paddle


class CollisionManager {
    fun checkBallPaddleCollision(ball: Ball, paddle: Paddle): Boolean {
        if (ball.bounds.overlaps(paddle.bounds)) {
            ball.bounceY()
            return true
        }
        return false
    }
}
