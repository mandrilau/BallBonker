package com.mandrilau.ball_bonker.managers

class GameStateManager {
    var score: Int = 0
        private set
    var lives: Int = 3
        private set

    fun addScore(points: Int) {
        score += points
    }

    fun loseLife() {
        lives--
    }

    fun reset() {
        score = 0
        lives = 3
    }

    fun isGameOver(): Boolean = lives <= 0
}
