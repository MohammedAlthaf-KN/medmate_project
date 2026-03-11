package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class userprecriptionfullactivity extends AppCompatActivity {
    TextView username;
    ImageView pre;

    String u, i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userprecriptionfullactivity);
        username = findViewById(R.id.preuser);
        pre = findViewById(R.id.preimage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        u = intent.getStringExtra("username");
        username.setText("Patient name: " + u);

        i = intent.getStringExtra("prescription");
        Picasso.get().load(config.imgurl + i).into(pre);

        // Set onClickListener for the image to show download prompt
        pre.setOnClickListener(v -> showDownloadPrompt());
    }

    // Method to show the download prompt
    private void showDownloadPrompt() {
        new AlertDialog.Builder(this)
                .setTitle("Download Image")
                .setMessage("Do you want to download this prescription image?")
                .setPositiveButton("Download", (dialog, which) -> downloadImage())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // Method to handle the image download
    private void downloadImage() {
        // Assuming config.imgurl + i gives the correct image URL
        String imageUrl = config.imgurl + i;
        Uri imageUri = Uri.parse(imageUrl);

        // Create the download request using DownloadManager
        DownloadManager.Request request = new DownloadManager.Request(imageUri);
        request.setTitle("Prescription Image");
        request.setDescription("Downloading prescription image...");

        // Set destination to save the image in the gallery (Pictures directory)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "prescription_" + System.currentTimeMillis() + ".jpg");

        // Get system DownloadManager service
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            // Enqueue the download request
            long downloadId = downloadManager.enqueue(request);

            // Show a toast message to indicate download started
            Toast.makeText(this, "Prescription is downloading...", Toast.LENGTH_SHORT).show();

            // Broadcast to update the gallery with the new image
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri fileUri = Uri.fromFile(new java.io.File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "prescription_" + System.currentTimeMillis() + ".jpg"));
            mediaScanIntent.setData(fileUri);
            sendBroadcast(mediaScanIntent);
        } else {
            Toast.makeText(this, "Download Manager is not available.", Toast.LENGTH_SHORT).show();
        }
    }
}
