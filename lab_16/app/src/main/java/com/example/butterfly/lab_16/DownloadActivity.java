package com.example.butterfly.lab_16;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;

public class DownloadActivity extends AppCompatActivity {

    EditText link;
    Button downloadWithManager;

    String myHTTPUrl = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        link = (EditText) findViewById(R.id.link);
        downloadWithManager = (Button) findViewById(R.id.downloadWithManager);

        downloadWithManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHTTPUrl = link.getText().toString();

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(myHTTPUrl));
                request.setTitle("File download");
                request.setDescription("File is being downloading...");

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                String nameOfFile = URLUtil.guessFileName(myHTTPUrl, null,
                        MimeTypeMap.getFileExtensionFromUrl(myHTTPUrl));
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameOfFile);

                DownloadManager manager = (DownloadManager) getApplicationContext()
                        .getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);

                link.setText("");
            }
        });
    }
}
