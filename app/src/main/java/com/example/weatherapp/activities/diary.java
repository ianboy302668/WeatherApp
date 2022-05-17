package com.example.weatherapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.adapters.NotesAdapter;
import com.example.weatherapp.database.NotesDatabase;
import com.example.weatherapp.entities.Note;
import com.example.weatherapp.listeners.NotesListener;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class diary extends AppCompatActivity implements NotesListener {
    public static final int REQUEST_CODE_ADD_NOTE=1;
    public static final int REQUEST_CODE_UPDATE_NOTE=2;
    public static final int REQUEST_CODE_SHOW_NOTE=3;
    public static final int REQUEST_CODE_SELECT_IMAGE=4;
    public static final int REQUEST_CODE_STORAGE_PERMISSION=5;

    private int noteClickedPosition = -1;
    private RecyclerView noteRecyclerView;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;

    ImageButton home;
    ImageButton add,last;
    Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //home=findViewById(R.id.tohomepage);
        last=findViewById(R.id.tolastpage);
        add=findViewById(R.id.addphoto);

        /*home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(diary.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(diary.this,MainActivity.class);
                startActivity(intent);
            }
        });


        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent.setClass(diary.this,MainActivity.class);
                //startActivity(intent);
                startActivityForResult(
                        new Intent(getApplicationContext(), create_diary.class),
                        REQUEST_CODE_ADD_NOTE
                );
            }
        });*/

        ///////設定recyclerviwe顯示note
        noteRecyclerView = findViewById(R.id.recyclerView);
        noteRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList,this);
        noteRecyclerView.setAdapter(notesAdapter);

        ///////設定recyclerviwe顯示note

        getNotes(REQUEST_CODE_SHOW_NOTE,false);


        findViewById(R.id.addphoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                )!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            diary.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                }else{
                    selectImage();
                }
            }
        });


    }

    @Override
    public void onNoteClicked(Note note, int position) {
        noteClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(), create_diary.class);
        intent.putExtra("isViewOrUpdate",true);
        intent.putExtra("note",note);
        startActivityForResult(intent,REQUEST_CODE_UPDATE_NOTE);
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length>0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else {
                Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getPathFromUri(Uri contentUri){
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri,null,null,null,null);
        if (cursor==null){
            filePath = contentUri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(filePathColumn[0]);
            //int index = cursor.getColumnIndex("data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }


    private void getNotes(final int requestCode, final boolean isNoteDeleted){

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>>{

            @Override
            protected List<Note> doInBackground(Void... voids){
                return NotesDatabase
                        .getNotesDatabase(getApplicationContext())
                        .noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes){
                super.onPostExecute(notes);
                Log.d("My_notes",notes.toString());

                /*
                if(noteList.size()==0){
                    noteList.addAll(notes);
                    notesAdapter.notifyDataSetChanged();
                } else {
                    noteList.add(0,notes.get(0));
                    notesAdapter.notifyItemInserted(0);
                }
                noteRecyclerView.smoothScrollToPosition(0);*/


               if (requestCode == REQUEST_CODE_SHOW_NOTE){
                   noteList.addAll(notes);
                   notesAdapter.notifyDataSetChanged();
                   Log.d("My_notes","第一分類");
               } else if (requestCode == REQUEST_CODE_ADD_NOTE){
                   noteList.add(0,notes.get(0));
                   notesAdapter.notifyItemInserted(0);
                   noteRecyclerView.smoothScrollToPosition(0);
                   Log.d("My_notes","第二分類");
               } else if (requestCode == REQUEST_CODE_UPDATE_NOTE){
                   noteList.remove(noteClickedPosition);
                   if (isNoteDeleted){
                       notesAdapter.notifyItemRemoved(noteClickedPosition);
                       Log.d("My_notes","第三分類");
                   }else {
                       noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                       notesAdapter.notifyItemChanged(noteClickedPosition);
                       Log.d("My_notes","第四分類");
                   }
               }
            }
        }

        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK){
            getNotes(REQUEST_CODE_ADD_NOTE,false);
        }else if (requestCode==REQUEST_CODE_UPDATE_NOTE && resultCode ==RESULT_OK){
            if (data!=null){
                //getNotes(REQUEST_CODE_UPDATE_NOTE);
                getNotes(REQUEST_CODE_UPDATE_NOTE,data.getBooleanExtra("isNoteDeleted",false));
            }
        }else if (requestCode== REQUEST_CODE_SELECT_IMAGE && resultCode ==RESULT_OK){
            if (data!=null){
                Uri selectedImageUri = data.getData();
                if (selectedImageUri!=null){
                    try {
                        String selectedImagePath = getPathFromUri(selectedImageUri);
                        Intent intent = new Intent(getApplicationContext(),create_diary.class);
                        intent.putExtra("isFromAddPhotoImageView",true);
                        intent.putExtra("AddPhotoImageViewType","Image");
                        intent.putExtra("imagePath",selectedImagePath);
                        startActivityForResult(intent,REQUEST_CODE_ADD_NOTE);
                    }catch (Exception exception){
                        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}