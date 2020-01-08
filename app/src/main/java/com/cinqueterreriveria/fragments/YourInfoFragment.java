package com.cinqueterreriveria.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.common.Utility;
import com.cinqueterreriveria.models.EditProfileModel;
import com.cinqueterreriveria.models.ProfileModel;
import com.cinqueterreriveria.models.UpdatePhotoModel;
import com.google.android.material.textfield.TextInputEditText;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourInfoFragment extends Fragment implements View.OnClickListener {
    AlertDialog dialog1, dialog;
    Button bt_edit_card;
    TextInputEditText et_first_name, et_last_name, et_mobile, et_email, et_address, et_city, et_country;
    PrefStore prefStore;
    TransparentDialog transparentDialog = new TransparentDialog();
    Button bt_save, bt_edit_profile;
    CircleImageView iv_profile;
    RelativeLayout rl_photo;
    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 777;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private int GALLERY = 1, CAMERA = 2;
    String path = "", userChoosenTask = "", filepath, image_url = "";
    Bitmap compressedBitmap;

    public YourInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_info, container, false);
        prefStore = new PrefStore(getActivity());

        bt_edit_card = view.findViewById(R.id.bt_edit_card);
        et_first_name = view.findViewById(R.id.et_first_name);
        et_last_name = view.findViewById(R.id.et_last_name);
        et_mobile = view.findViewById(R.id.et_mobile);
        et_email = view.findViewById(R.id.et_email);
        et_address = view.findViewById(R.id.et_address);
        et_city = view.findViewById(R.id.et_city);
        et_country = view.findViewById(R.id.et_country);
        bt_save = view.findViewById(R.id.bt_save);
        bt_edit_profile = view.findViewById(R.id.bt_edit_profile);
        iv_profile = view.findViewById(R.id.iv_profile);
        rl_photo = view.findViewById(R.id.rl_photo);

        bt_edit_card.setOnClickListener(this);
        bt_save.setOnClickListener(this);
        bt_edit_profile.setOnClickListener(this);
        rl_photo.setOnClickListener(this);

        enableFields(true);
        requestPermission();
        profileApi();

        return view;

    }

    private void enableFields(boolean b) {
        et_first_name.setEnabled(b);
        et_last_name.setEnabled(b);
        et_mobile.setEnabled(b);
        et_email.setEnabled(b);
        et_address.setEnabled(b);
        et_city.setEnabled(b);
        et_country.setEnabled(b);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_photo:
                showPictureDialog();
                break;
            case R.id.bt_edit_card:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_editcard, null);
                ImageView iv_cancel_card = view2.findViewById(R.id.iv_cancel_card);
                TextView tv_add_new = view2.findViewById(R.id.tv_add_new);
                builder2.setView(view2);

                iv_cancel_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                tv_add_new.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_add_card, null);
                        ImageView iv_cancel_car = view2.findViewById(R.id.iv_cancel_car);
                        builder2.setView(view2);

                        iv_cancel_car.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog = builder2.create();
                        dialog.setCancelable(true);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                dialog1 = builder2.create();
                dialog1.setCancelable(true);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();
                break;
            case R.id.bt_save:
                validations();
                break;
            case R.id.bt_edit_profile:
                et_first_name.setActivated(true);
                et_first_name.setPressed(true);
                et_first_name.requestFocus();
                // et_first_name.setCursorVisible(false);
                //et_first_name.setFocusable(true);
                enableFields(true);
                //et_first_name.setCursorVisible(true);

                break;
        }
    }

    // profile Api
    private void profileApi() {
        Log.d("TAG", "user_id " + prefStore.getString("user_id"));
        Call<ProfileModel> call = Rest.getRetrofit().profile(ApiConstents.SECRET_KEY, prefStore.getString("user_id"));
        transparentDialog.progressDialog(getActivity());
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                transparentDialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {

                        Glide.with(getActivity()).load(response.body().getDetail().getImage()).into(iv_profile);
                        et_first_name.setText(response.body().getDetail().getFirstname());
                        et_last_name.setText(response.body().getDetail().getLastname());
                        et_mobile.setText(response.body().getDetail().getPhonenumber());
                        et_email.setText(response.body().getDetail().getEmail());
                        et_address.setText(response.body().getDetail().getAddress());
                        et_city.setText(response.body().getDetail().getCity());
                        et_country.setText(response.body().getDetail().getCountry());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                transparentDialog.stopProgressDialog();

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // editProfile api
    private void editProfileApi() {
        transparentDialog.progressDialog(getActivity());
        Call<EditProfileModel> call = Rest.getRetrofit().editProfile(ApiConstents.SECRET_KEY,
                et_first_name.getText().toString(),
                et_last_name.getText().toString(),
                et_email.getText().toString(),
                et_mobile.getText().toString(),
                et_address.getText().toString(),
                et_city.getText().toString(),
                et_country.getText().toString(),
                prefStore.getString("user_id"));

        call.enqueue(new Callback<EditProfileModel>() {
            @Override
            public void onResponse(Call<EditProfileModel> call, Response<EditProfileModel> response) {
                transparentDialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {
                        Toast.makeText(getActivity(), "Profile Edit Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfileModel> call, Throwable t) {
                transparentDialog.stopProgressDialog();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // validation
    private void validations() {
        if (et_first_name.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter First name", Toast.LENGTH_SHORT).show();
        }
        if (et_last_name.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Last name", Toast.LENGTH_SHORT).show();
        }
        if (et_mobile.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Contact no.", Toast.LENGTH_SHORT).show();
        }
        if (et_address.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Address", Toast.LENGTH_SHORT).show();
        }
        if (et_city.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter City", Toast.LENGTH_SHORT).show();
        }
        if (et_country.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter Country", Toast.LENGTH_SHORT).show();
        } else {
            editProfileApi();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                showPictureDialog();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);
            }
        }
    }

    //************************* Select Image From Camera And Gallery ************************************
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    //*************************** Open Camera/Gallery Dialog ************************
    private void showPictureDialog() {
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_camera, null);
        Button bt_gallery = view2.findViewById(R.id.bt_gallery);
        Button bt_camera = view2.findViewById(R.id.bt_camera);
        Button bt_cancel = view2.findViewById(R.id.bt_cancel);
        builder2.setView(view2);
        final AlertDialog dialog2 = builder2.create();
        dialog2.setCancelable(true);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        bt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Choose from Gallery";
                boolean result = Utility.checkPermission(getActivity());
                if (result) {
                    galleryIntent();
                }
                dialog2.dismiss();
            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                    }
                } else {
                    userChoosenTask = "Take Photo";
                    boolean result = Utility.checkPermission(getActivity());
                    if (result) {
                        takePhotoFromCamera();
                    }
                }
                dialog2.dismiss();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }

    private void galleryIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Uri contentURI;
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        } else if (requestCode == GALLERY) {
            if (data != null) {
                try {
                    contentURI = data.getData();
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    byte[] byteArray = stream.toByteArray();

                    compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    filepath = getRealPathFromURI(contentURI);
                    Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();

                    Tiny.getInstance().source(contentURI).asFile().withOptions(options).compress(new FileCallback() {
                        @Override
                        public void callback(boolean isSuccess, String outfile, Throwable t) {
                            path = outfile;

                            uploadPhoto();
                            //file = new TypedFile("image/png", new File(outfile));
                           /* civ_productImage.setImageBitmap(compressedBitmap);
                            rl_product.setVisibility(View.VISIBLE);
                            iv_upload_product.setVisibility(View.GONE);
                            tv_upload_text.setVisibility(View.GONE);*/
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA) {
            if (resultCode == getActivity().RESULT_OK) {

                final Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byteArray = stream.toByteArray();
                compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();

                Tiny.getInstance().source(imageBitmap).asFile().withOptions(options).compress(new FileCallback() {
                    @Override
                    public void callback(boolean isSuccess, String outfile, Throwable t) {
                        path = outfile;
                        uploadPhoto();
                        //file = new TypedFile("image/png", new File(outfile));
                       /* civ_productImage.setImageBitmap(compressedBitmap);
                        rl_product.setVisibility(View.VISIBLE);
                        iv_upload_product.setVisibility(View.GONE);
                        tv_upload_text.setVisibility(View.GONE);*/
                    }
                });
            }
        }
    }


    private void uploadPhoto() {

        transparentDialog.progressDialog(getActivity());
        File file = new File(path);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);

        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), prefStore.getString("user_id"));


        Call<UpdatePhotoModel> call = Rest.getRetrofit().userPicture(ApiConstents.SECRET_KEY, id, part);

        call.enqueue(new Callback<UpdatePhotoModel>() {
            @Override
            public void onResponse(Call<UpdatePhotoModel> call, Response<UpdatePhotoModel> response) {
                transparentDialog.stopProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        iv_profile.setImageBitmap(compressedBitmap);
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdatePhotoModel> call, Throwable t) {
                transparentDialog.stopProgressDialog();

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }





}
