package example.myben;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPath redPath1;
    //控制点的坐标
    float mEndX = 0;
    float mEndY = 0;

    TextView num;
    ImageView iv;
    int mWidth;
    float startX = 50;
    float startY = 0;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {

        //相当于购物车
        iv = (ImageView) findViewById(R.id.iv);
        num = (TextView) findViewById(R.id.num);

        WindowManager wm = this.getWindowManager();
        mWidth = wm.getDefaultDisplay().getWidth();

        iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mEndX = iv.getX();
                mEndY = iv.getY();
                redPath1.moveTo(startX, startY);//重置起点的路径
                redPath1.quadTo(mWidth / 2 + 200, -120, mEndX, mEndY);
            }
        });

        redPath1 = new ViewPath();

        imageView = new ImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setImageResource(R.mipmap.flowers);
        addContentView(imageView, params);
        imageView.setVisibility(View.INVISIBLE);

    }

    public void send(View view) {
        if(imageView.getVisibility() == View.INVISIBLE){
            imageView.setVisibility(View.VISIBLE);
        }

        ObjectAnimator animator = ObjectAnimator.ofObject(new ViewObj(imageView), "viewLoc", new ViewPathEvaluator(), redPath1.getPoints().toArray());
        animator.setDuration(1000);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束的时候增加view
                num.setText((Integer.parseInt(num.getText().toString()) + 1) + "");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    class ViewObj {
        private ImageView target;

        public ViewObj(ImageView target) {
            this.target = target;

        }
        public void setViewLoc(ViewPoint newLoc) {
            target.setTranslationX(newLoc.x);
            target.setTranslationY(newLoc.y);
        }
    }
}
