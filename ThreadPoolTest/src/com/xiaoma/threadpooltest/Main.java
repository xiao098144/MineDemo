/*
 * FileName:  Main.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-10-15
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.threadpooltest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-10-15]
 * @since [Product/module]
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Main extends Activity
{
    private static int order = 0;
    
    /** �ܹ��������񣨸��CPU�������������̵߳ĸ���,����ȡ�ĺô����ǿ������ֻ���ܵ�ס�� */
    // private static final int count = Runtime.getRuntime().availableProcessors() * 3 + 2;
    
    /** �ܹ���������������ģ���������ܵģ�Ϊ��Ч�����ԣ�����д����Ϊ10����������ֻ��ϵĻ����Ƽ�ʹ��������Ǹ�count�� */
    private static final int count = 10;
    
    /** ÿ��ִֻ��һ��������̳߳� */
    private static ExecutorService singleTaskExecutor = null;
    
    /** ÿ��ִ���޶������������̳߳� */
    private static ExecutorService limitedTaskExecutor = null;
    
    /** ��������һ���Կ�ʼ���̳߳� */
    private static ExecutorService allTaskExecutor = null;
    
    /** ����һ������ָ��ʱ����ִ��������̳߳أ�����ظ�ִ�� */
    private static ExecutorService scheduledTaskExecutor = null;
    
    /** ����һ������ָ��ʱ����ִ��������̳߳أ�����ظ�ִ�У���֮ͬ����ʹ�ù���ģʽ�� */
    private static ExecutorService scheduledTaskFactoryExecutor = null;
    
    private List<AsyncTaskTest> mTaskList = null;
    
    /** �����Ƿ�ȡ�� */
    private boolean isCancled = false;
    
    /** �Ƿ�����ȡ�������ʾ�� */
    private boolean isClick = false;
    
    /** �̹߳�����ʼ����ʽһ */
    ThreadFactory tf = Executors.defaultThreadFactory();
    
    /** �̹߳�����ʼ����ʽ�� */
    private static class ThreadFactoryTest implements ThreadFactory
    {
        
        @Override
        public Thread newThread(Runnable r)
        {
            Thread thread = new Thread(r);
            thread.setName("XiaoMaGuo_ThreadFactory");
            thread.setDaemon(true); // ���û��̱߳���ػ��߳�,Ĭ��false
            return thread;
        }
    }
    
    static
    {   
        singleTaskExecutor = Executors.newSingleThreadExecutor();// ÿ��ִֻ��һ���߳�������̳߳�
        limitedTaskExecutor = Executors.newFixedThreadPool(3);// �����̳߳ش�СΪ7���̳߳�
        allTaskExecutor = Executors.newCachedThreadPool(); // һ��û����������߳�����̳߳�
        scheduledTaskExecutor = Executors.newScheduledThreadPool(3);// һ�����԰�ָ��ʱ��������Ե�ִ�е��̳߳�
        scheduledTaskFactoryExecutor = Executors.newFixedThreadPool(3, new ThreadFactoryTest());// ��ָ������ģʽ��ִ�е��̳߳�
        scheduledTaskFactoryExecutor.submit(new Runnable()
        {
            
            @Override
            public void run()
            {
                Log.i("KKK", "This is the ThreadFactory Test  submit Run! ! ! ");
            }
        });
    };
    
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.demo);
        final ListView taskList = (ListView)findViewById(R.id.task_list);
        taskList.setAdapter(new AsyncTaskAdapter(getApplication(), count));
        taskList.setOnItemClickListener(new OnItemClickListener()
        {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0) // �Ե�һ��Ϊ�������Թر��̳߳�
                {
                    /**
                     * ��ر��̳߳ط�ʽһ�����������µ�Task,�رպ����ڵȴ� ִ�е��������κ�Ӱ�죬����ִ��,�޷���ֵ!
                     */
                    // allTaskExecutor.shutdown();
                    
                    /**
                     * ��ر��̳߳ط�ʽ����Ҳ�������µ�Task����ֹͣ��ȴ�ִ�е�Task��Ҳ����˵�� ִ�е�һ���������ִ����ȥ�������ջ�����㷵��һ�����ڵȴ�ִ�е��̳߳عر�ȴû�б�ִ�е�Task���ϣ�
                     */
                    List<Runnable> unExecRunn = allTaskExecutor.shutdownNow();
                    
                    for (Runnable r : unExecRunn)
                    {
                        Log.i("KKK", "δִ�е�������Ϣ��=" + unExecRunn.toString());
                    }
                    Log.i("KKK", "Is shutdown ? = " + String.valueOf(allTaskExecutor.isShutdown()));
                    allTaskExecutor = null;
                }
                
                // �Եڶ���Ϊ���������Ƿ�ȡ��ִ�е�����
                AsyncTaskTest sat = mTaskList.get(1);
                if (position == 1)
                {
                    if (!isClick)
                    {
                        sat.cancel(true);
                        isCancled = true;
                        isClick = !isClick;
                    }
                    else
                    {
                        sat.cancel(false);
                        isCancled = false;
                        // isClick = false;
                        isClick = !isClick;
                        if (null != sat && sat.getStatus() == AsyncTask.Status.RUNNING)
                        {
                            if (sat.isCancelled())
                            {
                                sat = new AsyncTaskTest(sat.mTaskItem);
                            }
                            else
                            {
                                Toast.makeText(Main.this, "A task is already running, try later", Toast.LENGTH_SHORT)
                                    .show();
                            }
                        }
                        
                        /**
                         * ����������Թرգ��ڲ��������allTaskExecutor��ͬʱ���ᱨ�쳣��û�п���ʹ�õ��̳߳أ��ʴ˴���������̳߳ض���
                         */
                        if (allTaskExecutor == null)
                        {
                            allTaskExecutor = Executors.newCachedThreadPool();
                        }
                        sat.executeOnExecutor(allTaskExecutor); // The task is already running(��Ҳ�Ǹ��쳣Ŷ��С��ʹ�ã� )
                    }
                }
                else
                {
                    sat.cancel(false);
                    isCancled = false;
                    // sat.execute(sat.mTaskItem);
                    // sat.executeOnExecutor(allTaskExecutor);
                }
                
            }
        });
    }
    
    /**
     * @TODO [ListView Item����Ŀ������]
     * @author XiaoMaGuo ^_^
     * @version [version-code, 2013-10-22]
     * @since [Product/module]
     */
    private class AsyncTaskAdapter extends BaseAdapter
    {
        private Context mContext;
        
        private LayoutInflater mFactory;
        
        private int mTaskCount;
        
        public AsyncTaskAdapter(Context context, int taskCount)
        {
            mContext = context;
            mFactory = LayoutInflater.from(mContext);
            mTaskCount = taskCount;
            mTaskList = new ArrayList<AsyncTaskTest>(taskCount);
        }
        
        @Override
        public int getCount()
        {
            return mTaskCount;
        }
        
        @Override
        public Object getItem(int position)
        {
            return mTaskList.get(position);
        }
        
        @Override
        public long getItemId(int position)
        {
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = mFactory.inflate(R.layout.list_view_item, null);
                AsyncTaskTest task = new AsyncTaskTest((MyListItem)convertView);
                
                /**
                 * ������������ִ��Ч��һ��,�α��ʲ���
                 * */
                // task.execute();
                // task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                
                /**
                 * ����ķ�ʽ��С��API 11��ʱЧ����һ��ģ����ڸ߰汾�е���΢�е㲻ͬ,���Կ�����AsyncTask���ı����Ķ����֪����ʹ������
                 * ��ʽʱ��ϵͳ��Ĭ�ϵĲ������һ�飬���һ��ķ�ʽ��ִ�����ǵ����񣬶����ڣ�AsyncTask.class�У�private static final int CORE_POOL_SIZE = 5;
                 * */
                // use AsyncTask#THREAD_POOL_EXECUTOR is the same to older version #execute() (less than API 11)
                // but different from newer version of #execute()
                // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                
                /**
                 * һ��һ��ִ�����ǵ�����,Ч���밴˳��ִ����һ���(AsyncTask.SERIAL_EXECUTOR)
                 * */
                // task.executeOnExecutor(singleTaskExecutor);
                
                /**
                 * ������ָ���ĸ�����ִ��������̳߳�
                 * */
                // task.executeOnExecutor(limitedTaskExecutor);
                
                /**
                 * ���޶�ָ��������̳߳أ�Ҳ����˵������������˼���������ȫ��ͬһʱ�俪ʼִ�У� �������ֻ��ܵ����ܲ���
                 * */
                task.executeOnExecutor(allTaskExecutor);
                
                /**
                 * ����һ������ָ��ʱ����ִ��������̳߳أ�����ظ�ִ��
                 * */
                // task.executeOnExecutor(scheduledTaskExecutor);
                
                /**
                 * ����һ����ָ������ģʽ��ִ��������̳߳�,���ܱȽ����,��Ҳ������
                 */
                // task.executeOnExecutor(scheduledTaskFactoryExecutor);
                mTaskList.add(task);
            }
            return convertView;
        }
    }
    
    class AsyncTaskTest extends AsyncTask<Void, Integer, Void>
    {
        private MyListItem mTaskItem;
        
        private String id;
        
        private AsyncTaskTest(MyListItem item)
        {
            mTaskItem = item;
            if (order < count || order == count)
            {
                id = "ִ��:" + String.valueOf(++order);
            }
            else
            {
                order = 0;
                id = "ִ��:" + String.valueOf(++order);
            }
        }
        
        @Override
        protected void onPreExecute()
        {
            mTaskItem.setTitle(id);
        }
        
        /**
         * Overriding methods
         */
        @Override
        protected void onCancelled()
        {
            super.onCancelled();
        }
        
        @Override
        protected Void doInBackground(Void... params)
        {
            if (!isCancelled() && isCancled == false) // ����ط��ܹؼ�������ñ�־λ�Ļ���ֱ��setCancel��true������Ч��
            {
                int prog = 0;
                
                /**
                 * �����while�У�С��д�˸���֧�������������������տ�ʼ���ص�ʱ���ٶȿ죬��������ɵ�ʱ���ͻȻ������������Ч�� ��ҿ�������һ�£�����
                 * ��PP�ֻ����֡�91�ֻ������л������ֻ�Ӧ���У���������������󣬿�ʼ�죬����ʱ�����ر����ˣ������� ���ǿ������˲������������ص�����һ���ʱ��Ҳ���ǿ��������ʱ��ȥ��ȡ��������ö��˷�
                 * ����������������㲻��ȥȡ����ѣ�
                 */
                while (prog < 101)
                {
                    
                    if ((prog > 0 || prog == 0) && prog < 70) // С��70%ʱ���ӿ���������
                    {
                        SystemClock.sleep(100);
                    }
                    else
                    // ����70%ʱ��������������
                    {
                        SystemClock.sleep(300);
                    }
                    
                    publishProgress(prog); // ���½����
                    prog++;
                }
            }
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result)
        {
        }
        
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            mTaskItem.setProgress(values[0]); // ���ý��
        }
    }
}

/**
 * @TODO [һ���򵥵��Զ��� ListView Item]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-10-22]
 * @since [Product/module]
 */
class MyListItem extends LinearLayout
{
    private TextView mTitle;
    
    private ProgressBar mProgress;
    
    public MyListItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    public MyListItem(Context context)
    {
        super(context);
    }
    
    public void setTitle(String title)
    {
        if (mTitle == null)
        {
            mTitle = (TextView)findViewById(R.id.task_name);
        }
        mTitle.setText(title);
    }
    
    public void setProgress(int prog)
    {
        if (mProgress == null)
        {
            mProgress = (ProgressBar)findViewById(R.id.task_progress);
        }
        mProgress.setProgress(prog);
    }
}
