package example.myben;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xuqianqian on 2016/11/3.
 */

public class ViewPath {

    private ArrayList<ViewPoint> mPoints;

    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int QUAD = 2;
    public static final int CURVE = 3;

    public ViewPath() {
        mPoints = new ArrayList<>();
    }

    public void moveTo(float x,float y){
        mPoints.add(ViewPoint.moveTo(x,y,MOVE));
   }

    public void lineTo(float x,float y){
        mPoints.add(ViewPoint.lineTo(x,y,LINE));
    }

    public void curveTo(float x,float y,float x1,float y1,float x2,float y2){
        mPoints.add(ViewPoint.curveTo(x,y,x1,y1,x2,y2,CURVE));
    }

    public void quadTo(float x,float y,float x1,float y1){
        mPoints.add(ViewPoint.quadTo(x,y,x1,y1,QUAD));
    }

    public Collection<ViewPoint> getPoints(){
        return mPoints;
    }
}
