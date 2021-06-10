package com.example.edibleflowers.fragment.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.edibleflowers.R;
import com.example.edibleflowers.utils.CameraUtils;
import com.example.edibleflowers.utils.ImageUtils;
import com.example.edibleflowers.view.MySurfaceView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MySurfaceView mySurfaceView;
    private Button btTake;
    private int mOrientation;
    private Camera.AutoFocusCallback myAutoFocusCallback = null;
    private View root;


    public HomeScanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeScanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeScanFragment newInstance(String param1, String param2) {
        HomeScanFragment fragment = new HomeScanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home_scan, container, false);
        initView();
        return root;
    }

    private void initView() {
        mOrientation = CameraUtils.calculateCameraPreviewOrientation(getActivity());
        mySurfaceView = root.findViewById(R.id.mSurfaceView);
        btTake = root.findViewById(R.id.bt_take_pic);
        CameraUtils.startPreview();
        btTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        mySurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtils.startPreview();
                try {
                    myAutoFocusCallback = (success, camera) -> {
                        // TODO Auto-generated method stub
                        if (success)//success表示对焦成功
                        {
                            Log.i("autofocus", "myAutoFocusCallback: success...");
                            //myCamera.setOneShotPreviewCallback(null);

                        } else {
                            //未对焦成功
                            Log.i("autofocus", "myAutoFocusCallback: 失败了...");
                        }
                    };
                    CameraUtils.getmCamera().autoFocus(myAutoFocusCallback);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    private void takePicture() {

        Log.e("123", "take_picture");

        CameraUtils.takePicture(new Camera.ShutterCallback() {
            @Override
            public void onShutter() {

            }
        }, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {


                CameraUtils.stopPreview();

                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap != null) {
                    bitmap = ImageUtils.getRotatedBitmap(bitmap, mOrientation);
                    String path = Environment.getExternalStorageDirectory() + "/DCIM/Camera/"
                            + System.currentTimeMillis() + ".jpg";

                    try {
                        FileOutputStream fout = new FileOutputStream(path);
                        BufferedOutputStream bos = new BufferedOutputStream(fout);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

                        bos.flush();
                        bos.close();
                        fout.close();

                        Luban.with(getContext())
                                .load(new File(path))
                                .ignoreBy(100)
                                .setFocusAlpha(false)
                                .setTargetDir(getPath())
                                .filter(path1 -> true)
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {

                                    }

                                    @Override
                                    public void onSuccess(File file) {
//                                        HWPusher hw = new HWPusher();
//                                        hw.push(file, CamActivity.this);

                                    }
                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                }).launch();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}