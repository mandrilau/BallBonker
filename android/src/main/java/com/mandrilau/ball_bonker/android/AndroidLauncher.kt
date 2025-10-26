import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.mandrilau.ball_bonker.BallBonkerGame


/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = AndroidApplicationConfiguration().apply {
            useImmersiveMode = true // Recommended, but not required.
        }
        initialize(BallBonkerGame(), configuration)
    }
}
