package com.xiao.androiddemo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapHandlerUtil {
	public static final CompressFormat FORMAT_JPEG = Bitmap.CompressFormat.JPEG;
	public static final CompressFormat FORMAT_PNG = Bitmap.CompressFormat.PNG;
	public static final int QUALITY = 100;
	
	private static  int sQuality = QUALITY;
	private static  CompressFormat sFormat = Bitmap.CompressFormat.PNG;
	
	public static void setQuality(int quality)
	{
		BitmapHandlerUtil.sQuality = quality;
	}
	
	public static void setFormat(CompressFormat format)
	{
		BitmapHandlerUtil.sFormat = format;
	}
	
	// Drawable → Bitmap
	public static Bitmap drawable2Bitmap(Drawable drawable)
	{
		// 按指定参数创建一个空的Bitmap对象
		final Bitmap bitmap = Bitmap.createBitmap(
					drawable.getIntrinsicWidth(),  
					drawable.getIntrinsicHeight(),  
					drawable.getOpacity() != PixelFormat.OPAQUE ? 
							Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		
		final Canvas canvas = new Canvas(bitmap); 
		//canvas.setBitmap(bitmap);  
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());  
        drawable.draw(canvas);  
        
        return bitmap;
	}
	
	// Bitmap→Drawable
	public static Drawable bitmap2Drawable(Bitmap bitmap)
	{
		final Drawable drawable = new BitmapDrawable(bitmap);
		
		return drawable;
	}
	
	/**
	 * Bitmap 2 InputStream
	 * @param bmp
	 * @return
	 */
	public static InputStream bmp2IS(Bitmap bmp){
		return new ByteArrayInputStream(bitmap2Bytes(bmp));
	}
	
	// Bitmap → Byte[]
	public static byte[] bitmap2Bytes(Bitmap bm)
	{  
	    final ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    bm.compress(sFormat, sQuality, baos);
	    
	    return baos.toByteArray();  
	}  
	
	// byte[] → Bitmap
	public static Bitmap bytes2Bimap(byte[] b)
	{  
        if(b.length!=0)
        {  
            return BitmapFactory.decodeByteArray(b, 0, b.length);  
        }  
        else 
        {  
            return null;  
        }  
	}
	
	// 从资源中获取Bitmap
	public static Bitmap getBitmapFromResource(Context context, int resId)
	{
		final Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(), resId);
		
		//Bitmap bitmap = BitmapFactory.decodeStream(context.getResources().openRawResource(resId);
		
		return bitmap;
	}
	
	// 从文件中获取Bitmap
	public static Bitmap getBitmapFromFile(String fileFullName)
	{
		final Bitmap bitmap = BitmapFactory.decodeFile(fileFullName);  
		
		return bitmap;
	}
	
	// 保存Bmp文件
	public static boolean saveBitmap2file(Bitmap bmp, String fileFullname)
	{
		if (null == bmp)
		{
			return false;
		}
		
		OutputStream stream = null;
		try {
		       stream = new FileOutputStream(fileFullname);
		} catch (FileNotFoundException e) {
		       e.printStackTrace();
		}
		return bmp.compress(sFormat, sQuality, stream);
	}
	
	// 图片缩放
	public static Bitmap zoomBitmap(Bitmap bitmap, int newWidth, int newHeight)
	{
		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();
		
		final float scaleWidth = ((float) newWidth) / width;
		final float scaleHeight = ((float) newHeight) / height;
		
		final Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		final Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		
		return newBitmap;
	}
	
	// 获得圆角图片
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
	{
		final Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		final Canvas canvas = new Canvas(newBitmap);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		
		return newBitmap;
	}
	
	// 获得带倒影的图片方法
	public static Bitmap createReflectedImage(Bitmap bitmap, int w, int h) 
    {
        final int reflectionGap = -1;
        final int width = bitmap.getWidth(); 
        final int height = bitmap.getHeight();
        final Matrix matrix = new Matrix(); 
        matrix.preScale(1, -1);
//        final Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
        final Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height - h, w, h, matrix, false);
        final Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + h), Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
//        final Paint defaultPaint = new Paint(); 
//        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
        final Paint paint = new Paint(); 
        final LinearGradient shader = new LinearGradient(0, 
        		bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0xA0ffffff, 0x00ffffff, TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
        return bitmapWithReflection; 
    }
	
	public static Bitmap translateBitmap(Bitmap bitmap, int x, int y)
    {
    	final Matrix mMatrix = new Matrix();
    	
    	// 平移
    	mMatrix.postTranslate(x, y);
    	
    	final Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
    	return bm;
    }
	
	public static Bitmap rotateBitmap(Bitmap bitmap, int value)
    {
    	final Matrix mMatrix = new Matrix();
    	
    	// 旋转
    	mMatrix.setRotate(value);
    	
    	final Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
    	return bm;
    }

	public static Bitmap skewBitmap(Bitmap bitmap, int kx, int ky)
    {
    	final Matrix mMatrix = new Matrix();
    	
    	// 倾斜
    	mMatrix.postSkew(kx, ky);
    	
    	final Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
    	return bm;
    }
	
	public static Bitmap scaleBitmap(Bitmap bitmap, int sx, int sy)
    {
    	final Matrix mMatrix = new Matrix();
    	
    	mMatrix.setScale(sx, sy);
    	
    	final Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
    	return bm;
    }
	
	// 图片水印的生成方法
	private static Bitmap createBitmap(Bitmap src, Bitmap watermark)  
	{   
		final int w = src.getWidth();  
		final int h = src.getHeight();  
		final int ww = watermark.getWidth();  
		final int wh = watermark.getHeight();  
		
		//create the new blank bitmap  
		final Bitmap newBitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		final Canvas cv = new Canvas(newBitmap);  
	   
		//draw src into  
		cv.drawBitmap(src, 0, 0, null);
	   
		//draw watermark into  
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);
	   
		//save all clip  
		cv.save(Canvas.ALL_SAVE_FLAG);
		//act_askdoctor
		cv.restore();
	   
		return newBitmap;  
	} 
	
	// 图片变透明
	public static Bitmap bitmap2Transparent(Bitmap bitmap, int alpha) 
	{
		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();
		
		final Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
		final Canvas cv = new Canvas(newBitmap);  
		final Paint paint = new Paint();
		paint.setAlpha(alpha);
		cv.drawBitmap(bitmap, 0, 0, paint);
		//save all clip  
		cv.save(Canvas.ALL_SAVE_FLAG);
		cv.restore();
		
		return newBitmap;
	}
}
