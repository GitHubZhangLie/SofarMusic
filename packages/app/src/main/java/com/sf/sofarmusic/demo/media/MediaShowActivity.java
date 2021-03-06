package com.sf.sofarmusic.demo.media;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sf.libplayer.test.other.encode.EncodeActivity;
import com.sf.sofarmusic.R;
import com.sf.sofarmusic.adapter.DemoListAdapter;
import com.sf.sofarmusic.base.UIRootActivity;
import com.sf.sofarmusic.callback.PermissionsResultListener;
import com.sf.sofarmusic.demo.media.audio.AudioPCMActivity;
import com.sf.sofarmusic.demo.media.audio.AudioWavActivity;
import com.sf.sofarmusic.demo.media.recorder.MediaRecorderActivity;
import com.sf.sofarmusic.demo.media.track.MediaTrackActivity;
import com.sf.sofarmusic.demo.media.video.MediaCodecActivity;
import com.sf.sofarmusic.demo.media.video.VideoActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sufan on 2018/4/14.
 */

public class MediaShowActivity extends UIRootActivity implements DemoListAdapter.OnItemClickListener{

    private RecyclerView rv_media;
    private DemoListAdapter mAdapter;
    private List<String> mList;
    private String[] datas = {"原始音频数据pcm的录制和播放", "wav文件的存储和解析","视频的采集预览","mediarecorder录制音视频"
    ,"音视频的合成","mediacodec音视频的编解码"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_media_show;
    }

    @Override
    protected void initTitle() {
        head_title.setText("音视频系列");
    }

    @Override
    public void initView() {
        rv_media=findViewById(R.id.rv_media);
        rv_media.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {
        mList= Arrays.asList(datas);
        mAdapter=new DemoListAdapter(this,mList);
        rv_media.setAdapter(mAdapter);

        String des = "录音或摄像权限被禁止，我们需要打开权限";
        String[] permissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
        baseAt.requestPermissions(des, permissions, 100, new PermissionsResultListener() {
            @Override
            public void onPermissionGranted() {

            }
            @Override
            public void onPermissionDenied() {
                finish();
            }
        });
    }

    @Override
    public void initEvent() {
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, String name) {
        if(datas[0].equals(name)){
            Intent intent=new Intent(this, AudioPCMActivity.class);
            startActivity(intent);
        }else if(datas[1].equals(name)){
            Intent intent=new Intent(this, AudioWavActivity.class);
            startActivity(intent);
        }else if(datas[2].equals(name)){
            Intent intent=new Intent(this, VideoActivity.class);
            startActivity(intent);
        }else if(datas[3].equals(name)){
            Intent intent=new Intent(this, MediaRecorderActivity.class);
            startActivity(intent);
        }else if(datas[4].equals(name)){
            Intent intent=new Intent(this, MediaTrackActivity.class);
            startActivity(intent);
        }else if(datas[5].equals(name)){
            Intent intent=new Intent(this, MediaCodecActivity.class);
            startActivity(intent);
        }

    }
}
