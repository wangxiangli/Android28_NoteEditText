package com.bush.android28_noteedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by 嘉华盛世 on 2016-08-01.
 */
public class NoteEditText extends EditText {
    private int padding= 50;
    private int lineColor=Color.BLUE;
    private float strokeWidth=1;
    public NoteEditText(Context context) {
        super(context);
    }

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //文本置顶
        setGravity(Gravity.TOP);
        //设置文本的左上右下填充
        setPadding(padding,0,padding,0);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteEditText);
        lineColor= typedArray.getColor(R.styleable.NoteEditText_lineColor,lineColor);
        strokeWidth=typedArray.getDimension(R.styleable.NoteEditText_strokeWidth, strokeWidth);
        padding= (int) typedArray.getDimension(R.styleable.NoteEditText_padding,padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算控件的宽高
        int viewHeight=getHeight();
        int viewWidth=getWidth();
        //急速行高
        int lineHeight=getLineHeight();
        //急速行数
        int lineCount =viewHeight/lineHeight;
        Paint paint=new Paint();
        //抗锯齿，
        paint.setAntiAlias(true);
        //画笔颜色
        paint.setColor(lineColor);
        //画笔宽度
        paint.setStrokeWidth(strokeWidth);
        for (int i = 0; i < lineCount; i++) {
            canvas.drawLine(padding,lineHeight*(i+1)
                    ,viewWidth-padding,lineHeight*(i+1),paint);

        }
        int textLineCounts=getLineCount();

        if (textLineCounts>lineCount){
            for (int i = lineCount; i < textLineCounts ; i++) {
                canvas.drawLine(padding,lineHeight*(i+1)
                        ,viewWidth-padding,lineHeight*(i+1),paint);

            }
        }

    }
}
