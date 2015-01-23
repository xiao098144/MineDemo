package com.xiao.androiddemo.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.FloatMath;
import android.util.Log;


/**
 * 
 * @Description 异部下载图片
 * @author dsy
 * @date 2014-2-26 下午9:22:52
 */
public class AsyncImageLoader {
	public static LruCache<String, Drawable> lruCache = new LruCache<String, Drawable>(
			100) {
		@Override
		protected void entryRemoved(boolean evicted, String key,
				Drawable oldValue, Drawable newValue) {
		}
	};
	
	public static LruCache<String, Bitmap> lruCache1 = new LruCache<String, Bitmap>(
			100) {
		@Override
		protected void entryRemoved(boolean evicted, String key,
				Bitmap oldValue, Bitmap newValue) {
		}
	};
	

	private static final String EXT_FILE_NAME = ".cache";
	private final ExecutorService executorService = Executors
			.newFixedThreadPool(5); // 固定一个线程来执行任务

	private final Handler handler = new Handler();
	private final File cacheRootDir;

	public AsyncImageLoader(String path) {
		cacheRootDir = new File(path);
	}

	public Drawable loadDrawable(final String imageUrl,
			final boolean isSaveImage, final ImageCallback callback) {
		Drawable lruCacheDrawable = null;
		lruCacheDrawable = lruCache.get(imageUrl);
		if (lruCacheDrawable != null) {
			return lruCacheDrawable;
		}

		File image = new File(cacheRootDir, String.valueOf(imageUrl.hashCode())
				+ getLastFileName(imageUrl) + EXT_FILE_NAME);
		if (isSaveImage && image.exists() && image.isFile()) {
			Bitmap bitmap = BitmapFactory.decodeFile(image.toString());
			Drawable drawable = new BitmapDrawable(bitmap);
			lruCache.put(imageUrl, drawable);
			return drawable;
		}

		executorService.submit(new Runnable() {
			public void run() {
				try {
					Drawable result = null;
					boolean isFromNet = false;
					String format = null;
					if (result == null) {
						byte[] formats = new byte[11];
						result = loadImageFromUrl(imageUrl, formats);
						format = getRealExtName(formats, ".jpg");
						isFromNet = true;
					}

					if (result != null) {
						final Drawable drawable = result;
						handler.post(new Runnable() {
							public void run() {
								callback.imageLoaded(drawable);
							}
						});
						Drawable lruCacheDrawable = lruCache.get(imageUrl);
						if (lruCacheDrawable == null) {
							lruCache.put(imageUrl, drawable);
						}
						if (isSaveImage) {
							if (isFromNet
									&& Environment.getExternalStorageState()
											.equals(Environment.MEDIA_MOUNTED)) {
								saveImageToSDCard(result, format, imageUrl);
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		return null;
	}
	
	public Bitmap loadDrawable1(final String imageUrl,
			final boolean isSaveImage, final ImageCallback1 callback1) {

		Bitmap bitmapCache = null;
		bitmapCache = lruCache1.get(imageUrl);
		if (bitmapCache != null) {
			return bitmapCache;
		}

		File image = new File(cacheRootDir, String.valueOf(imageUrl.hashCode())
				+ getLastFileName(imageUrl) + EXT_FILE_NAME);
		if (isSaveImage && image.exists() && image.isFile()) {
			Bitmap bitmap = BitmapFactory.decodeFile(image.toString());
			lruCache1.put(imageUrl, bitmap);
			return bitmap;
		}

		executorService.submit(new Runnable() {
			public void run() {
				try {
					Bitmap result = null;
					boolean isFromNet = false;
					String format = null;
					if (result == null) {
						byte[] formats = new byte[11];
						result = loadImageFromUrl1(imageUrl, formats);
						format = getRealExtName(formats, ".jpg");
						isFromNet = true;
					}

					if (result != null) {
						final Bitmap drawable = result;
						handler.post(new Runnable() {
							public void run() {
								callback1.imageLoaded(drawable);
							}
						});
						Bitmap lruCacheDrawable = lruCache1.get(imageUrl);
						if (lruCacheDrawable == null) {
							lruCache1.put(imageUrl, drawable);
						}
						if (isSaveImage) {
							if (isFromNet
									&& Environment.getExternalStorageState()
											.equals(Environment.MEDIA_MOUNTED)) {
								saveImageToSDCard1(result, format, imageUrl);
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		return null;
	}

	protected Bitmap loadImageFromUrl1(String imageUrl, byte[] format) {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new URL(imageUrl).openStream());
			if (format != null && format.length == 11) {
				if (in.markSupported()) {
					in.mark(10);
					int realSize = in.read(format, 1, 10);
					format[0] = (byte) realSize;
					in.reset();
				}
			}
			return BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Drawable loadImageFromUrl(String imageUrl, byte[] format) {
		try {
			// SystemClock.sleep(2000);
			BufferedInputStream in = new BufferedInputStream(
					new URL(imageUrl).openStream());
			if (format != null && format.length == 11) {
				if (in.markSupported()) {
					in.mark(10);
					int realSize = in.read(format, 1, 10);
					format[0] = (byte) realSize;
					in.reset();
				}
			}
			Drawable drawable = Drawable.createFromStream(in, "image.jpg");
			return drawable;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void saveImageToSDCard(Drawable drawable, String format,
			String imageUrl) {
		if (drawable == null || imageUrl == null) {
			return;
		}
		if (!cacheRootDir.exists() || !cacheRootDir.isDirectory()) {
			cacheRootDir.mkdirs();
		}

		File nomediaFile = new File(cacheRootDir, ".nomedia");
		try {
			if (!nomediaFile.exists() || !cacheRootDir.isFile()) {
				nomediaFile.createNewFile();
			}
		} catch (IOException e1) {
		}

		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		String orignalExtName = getLastFileName(imageUrl);
		String finalFileName = String.valueOf(imageUrl.hashCode())
				+ orignalExtName + EXT_FILE_NAME;
		File image = new File(cacheRootDir, finalFileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(image);
			if (".png".equalsIgnoreCase(orignalExtName)
					|| ".png".equalsIgnoreCase(format)) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			} else {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out = null;
		}
		image.setLastModified(new Date().getTime());
	}
	
	protected void saveImageToSDCard1(Bitmap drawable, String format,
			String imageUrl) {
		if (drawable == null || imageUrl == null) {
			return;
		}
		if (!cacheRootDir.exists() || !cacheRootDir.isDirectory()) {
			cacheRootDir.mkdirs();
		}

		File nomediaFile = new File(cacheRootDir, ".nomedia");
		try {
			if (!nomediaFile.exists() || !cacheRootDir.isFile()) {
				nomediaFile.createNewFile();
			}
		} catch (IOException e1) {
		}

//		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		String orignalExtName = getLastFileName(imageUrl);
		String finalFileName = String.valueOf(imageUrl.hashCode())
				+ orignalExtName + EXT_FILE_NAME;
		File image = new File(cacheRootDir, finalFileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(image);
			if (".png".equalsIgnoreCase(orignalExtName)
					|| ".png".equalsIgnoreCase(format)) {
				drawable.compress(Bitmap.CompressFormat.PNG, 100, out);
			} else {
				drawable.compress(Bitmap.CompressFormat.JPEG, 100, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out = null;
		}
		image.setLastModified(new Date().getTime());
	}

	public static String getRealExtName(byte[] b, String defaultFormat) {
		String format = defaultFormat;
		if (b != null && b.length == 11 && (int) b[0] == 10) {
			// GIF
			if (b[1] == (byte) 'G' && b[2] == (byte) 'I' && b[3] == (byte) 'F') {
				format = ".gif";
				// PNG
			} else if (b[2] == (byte) 'P' && b[3] == (byte) 'N'
					&& b[4] == (byte) 'G') {
				format = ".png";
				// JPG(JFIF)
			} else if (b[7] == (byte) 'J' && b[8] == (byte) 'F'
					&& b[9] == (byte) 'I' && b[10] == (byte) 'F') {
				format = ".jpg";
			}
		}
		return format;
	}

	private String getLastFileName(String imageUrl) {
		if (imageUrl.lastIndexOf("/") != -1) {
			if (imageUrl.substring(imageUrl.lastIndexOf("/")).lastIndexOf(".") != -1) {
				return imageUrl.substring(imageUrl.lastIndexOf("/")).substring(
						imageUrl.substring(imageUrl.lastIndexOf("/"))
								.lastIndexOf("."));
			}
		}
		return ".jpg";
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable);
	}
	
	public interface ImageCallback1 {
		public void imageLoaded(Bitmap bitmap);
	}

	/**
	 * only used by homeActivity hotgallery
	 * 
	 * @param imageUrl
	 * @param isSaveImage
	 * @param destWidth
	 * @param destHeight
	 * @param callback
	 * @return
	 */
	protected Drawable loadCompressedDrawable(final String imageUrl,
			final boolean isSaveImage, final float destWidth,
			final float destHeight, final ImageCallback callback) {
		// 如果LruCache里面有就直接取出来
		Drawable lruCacheDrawable = null;
		lruCacheDrawable = lruCache.get(imageUrl);
		if (lruCacheDrawable != null) {
			return lruCacheDrawable;
		}

		executorService.submit(new Runnable() {
			public void run() {
				if (!cacheRootDir.exists() || !cacheRootDir.isDirectory()) {
					cacheRootDir.mkdirs();
				}
				final File image = new File(cacheRootDir, String
						.valueOf(imageUrl.hashCode())
						+ getLastFileName(imageUrl) + EXT_FILE_NAME);

				// from sdcard
				if (isSaveImage && image.exists() && image.isFile()) {
					// long start = SystemClock.elapsedRealtime();
					final BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = true;
					Bitmap bitmap = BitmapFactory.decodeFile(
							image.getAbsolutePath(), options);

					int heightRatio = (int) FloatMath.ceil(options.outHeight
							/ destHeight);
					int widthRatio = (int) FloatMath.ceil(options.outWidth
							/ destWidth);

					if (heightRatio > 2 || widthRatio > 2) {
						if (heightRatio > widthRatio) {
							options.inSampleSize = heightRatio;
						} else {
							options.inSampleSize = widthRatio;
						}
					} else {
						options.inSampleSize = 2;
					}

					options.inJustDecodeBounds = false;
					bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),
							options);
					if (bitmap != null) {
						// if (DBHelper.isChooseServer()) {
						Log.d("imgLoader",
								"use SDCard img cache,Width="
										+ bitmap.getWidth() + " " + "Height="
										+ bitmap.getHeight());
						// }
						final Drawable drawable = new BitmapDrawable(bitmap);
						handler.post(new Runnable() {
							public void run() {
								callback.imageLoaded(drawable);
							}
						});

						Drawable lruCacheDrawable = lruCache.get(imageUrl);
						if (lruCacheDrawable == null) {
							lruCache.put(imageUrl, drawable);
						}
					} else {
						// if (DBHelper.isChooseServer())
						Log.w("imgLoader",
								"SDCard img decode " + image.getAbsolutePath()
										+ " failed");
					}
				}
				// from net
				else {
					try {
						final Drawable drawable = loadImageFromUrl(imageUrl,
								null);
						if (drawable != null) {
							handler.post(new Runnable() {
								public void run() {
									callback.imageLoaded(drawable);
								}
							});
						}

						if (isSaveImage) {
							if (Environment.getExternalStorageState().equals(
									Environment.MEDIA_MOUNTED)) {
								saveImageToSDCard(drawable, null, imageUrl);
							} else {
								Drawable lruCacheDrawable = lruCache
										.get(imageUrl);
								if (lruCacheDrawable == null) {
									lruCache.put(imageUrl, drawable);
								}
							}
						}

					} catch (Exception e) {
						// if (DBHelper.isChooseServer())
						Log.w("imgLoader", "net img decode " + imageUrl
								+ " failed");
					}
				}
			}
		});
		return null;
	}
}