package com.example.chenhuayu.test.view;

/**
 * Created by chenhuayu on 2018/2/8.
 */
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
public class ZoomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {


    private boolean mIsFirst = true;

    private float mInitScale;//��ʼ��������ֵ
    private float mMidScale;//˫�����������ֵ
    private float mMaxScale;//��������ֵ

    private Matrix mScaleMatrix;

    private ScaleGestureDetector mScaleGestureDetector;

    private int mLastPointCount;//��һ�ζ�㴥�ص�����
    private float mLastX, mLastY;//��һ�����д���������ĵ�
    private int mTouchSlop;//��Ϊ�Ƚ��ж��Ƿ�Ϊmove��һ��ϵͳ�Ĳ���ֵ
    private boolean canDrag;//��ʶ�Ƿ�����ƶ�
    //�Ƿ���Ҫ�������ҡ����±߽�ļ��
    private boolean shouldCheckTopAndBottom;
    private boolean shouldCheckLeftAndRight;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScaleMatrix = new Matrix();

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * ��ȡImageView������ɵ�ͼƬ
     * ��ʼ��λ�úʹ�С
     */
    @Override
    public void onGlobalLayout() {
        if (mIsFirst) {


            //�õ��ؼ��Ŀ�͸�
            int width = getWidth();
            int height = getHeight();

            //��ȡͼƬ���Լ����Ŀ�͸�
//            Drawable d = getDrawable();
//            if (d == null) {
//                return;
//            }
//            int dw = d.getIntrinsicWidth();
//            int dh = d.getIntrinsicHeight();
//
            float scale = 1.0f;
//            if (dw > width && dh < height) {
//                scale = width * 1.0f / dw;
//            }
//            if (dw < width && dh > height) {
//                scale = height * 1.0f / dh;
//            }
//            if ((dw > width && dh > height) || (dw > width && dh > height)) {
//                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
//            }

            //�õ���ʼ�����ű���
            mInitScale = scale;
            mMaxScale = mInitScale * 4;
            mMidScale = mInitScale * 2;

            //��ͼƬ�ƶ�����Ļ����
//            int dx = width / 2 - dw / 2;
//            int dy = height / 2 - dh / 2;
//
//            mScaleMatrix.postTranslate(dx, dy);
//            mScaleMatrix.postScale(mInitScale, mInitScale, width / 2, height / 2);
//            setImageMatrix(mScaleMatrix);

            mIsFirst = false;
        }

    }

    /**
     * ��ȡ��ǰ������ֵ
     *
     * @return
     */
    public float getScale() {
        float[] values = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    /**
     * �������Ǽ��ͼƬ���ұ߽��Ƿ�����Ļ�߽�
     */
    private void checkBorderAndCenter() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        //����ʱ���б߽��⣬��ֹ���ְױ�
        if (rectF.width() > width) {
            if (rectF.left > 0) {
                deltaX -= rectF.left;
            }
            if (rectF.right < width) {
                deltaX += width - rectF.right;
            }
        }

        if (rectF.height() > height) {
            if (rectF.top > 0) {
                deltaY -= rectF.top;
            }
            if (rectF.bottom < height) {
                deltaY += height - rectF.bottom;
            }
        }

        //���ͼƬ�ĸ߶Ȼ��߿��С�ڿؼ��ĸ߻��߿����������
        if (rectF.width() < width) {
            deltaX = width / 2 - rectF.right + rectF.width() / 2f;
        }
        if (rectF.height() < height) {
            deltaY = height / 2 - rectF.bottom + rectF.height() / 2f;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * ��ȡͼƬ��l,r,t,b���Լ����
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if (d != null) {
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();

//        if (getDrawable() == null) {
//            return true;
//        }
        //���ŷ�Χ�Ŀ���
        if ((scale < mMaxScale && scaleFactor > 1.0f) || (scale > mInitScale && scaleFactor < 1.0f)) {
            //��С��Ҳ����С����Сֵ
            if (scale * scaleFactor < mInitScale) {
                scaleFactor = mInitScale / scale;
            }
            if (scale * scaleFactor > mMaxScale) {
                scaleFactor = mMaxScale / scale;
            }
            //����
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            checkBorderAndCenter();
            setImageMatrix(mScaleMatrix);

        }

        return true;//��֤�¼��ܹ�����
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //��detector������
        mScaleGestureDetector.onTouchEvent(event);

        float x = 0;
        float y = 0;

        int pointCount = event.getPointerCount();
        for (int i = 0; i < pointCount; i++) {
            x += event.getX(i);
            y += event.getY(i);

        }
        x /= pointCount;
        y /= pointCount;

        if (mLastPointCount != pointCount) {
            canDrag = false;
            mLastX = x;
            mLastY = y;
        }
        mLastPointCount = pointCount;

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //����һ�����ĵ��λ�Ʋ�
                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!canDrag) {
                    canDrag = isMoveAction(dx, dy);
                }
                if (canDrag) {
                    RectF rectF = getMatrixRectF();
                    if (getDrawable() != null) {
                        shouldCheckTopAndBottom = shouldCheckLeftAndRight = true;
                        //���С�ڿؼ���ȣ�����������ƶ�
                        if (rectF.width() < getWidth()) {
                            shouldCheckLeftAndRight = false;
                            dx = 0;
                        }
                        if (rectF.height() < getHeight()) {
                            shouldCheckTopAndBottom = false;
                            dy = 0;
                        }

                        mScaleMatrix.postTranslate(dx, dy);
                        checkBorderWhenTranslate();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastX = x;
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:
                mLastPointCount = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                mLastPointCount = 0;
                break;
        }
        return true;
    }

    /**
     * ���ƶ�ʱ���б߽���
     */
    private void checkBorderWhenTranslate() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        if (shouldCheckTopAndBottom) {
            if (rectF.top > 0) {
                deltaY = -rectF.top;
            }

            if (rectF.bottom < getHeight()) {
                deltaY = getHeight() - rectF.bottom;

            }
        }
        if (shouldCheckLeftAndRight) {
            if (rectF.left > 0) {
                Log.d("TAG", "left = " + getLeft());
                deltaX = -rectF.left;
            }
            if (rectF.right < getWidth()) {
                deltaX = getWidth() - rectF.right;
            }
        }

        mScaleMatrix.postTranslate(deltaX, deltaY);

    }

    /**
     * �ж��Ƿ����Գ���move
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {
        //�ж�ǰ�����εĴ��������ĵľ����Ƿ�ﵽmove��׼����Сֵ
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

}
