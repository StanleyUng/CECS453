package accelerometer.example.com.accgame;

/**
 * Created by Stanley Ung on 3/15/2017.
 */

public class Particle {
    /* coefficient of restitution */
    private static final float COR = 0.7f;

    public float mPosX, mPosY;
    private float mVelX, mVelY;

    // use the acceleration values to calculate displacement of the particle along the X and Y axis.
    public void updatePosition(float sx, float sy, float sz, long timestamp) {
        float dt = (System.nanoTime() - timestamp) / 100000000000000.0f;
        mVelX += -sx * dt;
        mVelY += -sy * dt;

        mPosX += mVelX * dt;
        mPosY += mVelY * dt;
    }

    // add logic to create a bounce effect when it collides with the boundary.
    public void resolveCollisionWithBounds(float mHorizontalBound, float mVerticalBound) {
        if (mPosX > mHorizontalBound) {
            mPosX = mHorizontalBound;
            mVelX = -mVelX * COR;
        } else if (mPosX < -mHorizontalBound) {
            mPosX = -mHorizontalBound;
            mVelX = -mVelX * COR;
        }

        if (mPosY > mVerticalBound) {
            mPosY = mVerticalBound;
            mVelY = -mVelY * COR;
        } else if (mPosY < -mVerticalBound) {
            mPosY = -mVerticalBound;
            mVelY = -mVelY * COR;
        }
    }
}
