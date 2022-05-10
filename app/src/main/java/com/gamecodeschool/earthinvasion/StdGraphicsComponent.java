package com.gamecodeschool.earthinvasion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

class StdGraphicsComponent implements GraphicsComponent {

    private Bitmap mBitmap;
    private Bitmap mBitmapRotated;

    @Override
    public void initialize(Context context,
                           ObjectSpec spec,
                           PointF objectSize){

        // Make a resource id out of the string of the file name
        int resID = context.getResources()
                .getIdentifier(spec.getBitmapName(),
                        "drawable",
                        context.getPackageName());

        // Load the bitmap using the id
        mBitmap = BitmapFactory.decodeResource(
                context.getResources(), resID);

        // Resize the bitmap
        mBitmap = Bitmap
                .createScaledBitmap(mBitmap,
                        (int)objectSize.x,
                        (int)objectSize.y,
                        false);


    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t) {
        Matrix matrix = new Matrix();
        if(true){
            matrix.postRotate((float) Math.toDegrees(t.getRotation()));
            mBitmapRotated = Bitmap.createBitmap(mBitmap,
                    0, 0,
                    mBitmap.getWidth(),
                    mBitmap.getHeight(),
                    matrix, true);

            canvas.drawBitmap(mBitmapRotated,
                    t.getLocation().x,
                    t.getLocation().y,
                    paint);

        }
    }
}