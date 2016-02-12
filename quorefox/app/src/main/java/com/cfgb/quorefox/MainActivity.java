package com.cfgb.quorefox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    TextView messageText;
    TextView uploadButton;
    int serverResponseCode = 0;
    //ProgressDialog dialog = null;

    String upLoadServerUri = null;

    /**********
     * File Path
     *************/
    final String uploadFilePath = "/storage/emulated/0/downloaded_file.pdf";
    final String uploadFileName = "downloaded_file.pdf";
    private GoogleApiClient client;
    ProgressBar pb;
    Dialog dialog;
    int downloadedSize = 0;
    int totalSize = 0;
    TextView cur_val;
    String dwnload_file_path = "http://www.antennahouse.com/XSLsample/pdf/sample-link_1.pdf";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextView button = (TextView) findViewById(R.id.textView);
        button.setTypeface(font);
        TextView button2 = (TextView) findViewById(R.id.textView2);
        button2.setTypeface(font);
        TextView button8 = (TextView) findViewById(R.id.textView3);
        button8.setTypeface(font);
        TextView button4 = (TextView) findViewById(R.id.textView4);
        button4.setTypeface(font);
        TextView button5 = (TextView) findViewById(R.id.textView5);
        button5.setTypeface(font);
        TextView button6 = (TextView) findViewById(R.id.textView6);
        button6.setTypeface(font);
        TextView button7 = (TextView) findViewById(R.id.textView7);
        button7.setTypeface(font);
        TextView button9 = (TextView) findViewById(R.id.textView8);
        button9.setTypeface(font);
        TextView buttonb = (TextView) findViewById(R.id.textView9);
        buttonb.setTypeface(font);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
                TextView button10 = (TextView) deleteDialogView.findViewById(R.id.textView19);
                button10.setTypeface(font);
                TextView button11 = (TextView) deleteDialogView.findViewById(R.id.textView20);
                button11.setTypeface(font);
                TextView button12 = (TextView) deleteDialogView.findViewById(R.id.textView21);
                button12.setTypeface(font);

                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();

                button10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  showProgress(dwnload_file_path);

                        new Thread(new Runnable() {
                            public void run() {
                                downloadFile();
                            }
                        }).start();
                    }
                });

                button11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        messageText.setText("Uploading file path :- '/mnt/sdcard/" + uploadFileName + "'");

                        /************* Php script path ****************/
                        upLoadServerUri = "http://mobile.rshbeta.in/gauthambala/UploadToServer.php";
                        //  showProgress(dwnload_file_path);

                        dialog = ProgressDialog.show(MainActivity.this, "", "Uploading file...", true);

                        new Thread(new Runnable() {
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    public void run() {
//                                        messageText.setText("uploading started.....");
                                    }
                                });

                                uploadFile(uploadFilePath + "" + uploadFileName);

                            }
                        }).start();
                    }
                });
                button12.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        WebView webview = (WebView) deleteDialog.findViewById(R.id.webview);
                        //    webview.setLayoutParams(new ViewGroup.LayoutParams(
                        //          ViewGroup.LayoutParams.MATCH_PARENT,
                        //        ViewGroup.LayoutParams.MATCH_PARENT));
                        ViewGroup.LayoutParams params = webview.getLayoutParams();
                        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height = 600;
                        webview.requestLayout();
                        webview.getSettings().setJavaScriptEnabled(true);
                        String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
                        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=http://stackoverflow.com/questions/2655972/how-can-i-display-a-pdf-document-into-a-webview");


                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        buttonb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(
                        R.layout.mygrid, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                deleteDialog.show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cfgb.quorefox/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cfgb.quorefox/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    void downloadFile() {

        try {
            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();
            Log.e("1", "downloadFile: ");
            //set the path where we want to save the file
            File SDCardRoot = Environment.getExternalStorageDirectory();
            Log.e("2", "downloadFile: ");
            //create a new file, to save the downloaded file
            File file = new File(SDCardRoot, "downloaded_file.pdf");

            FileOutputStream fileOutput = new FileOutputStream(file);
            Log.e("3", "downloadFile: ");
            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file which we are downloading
            totalSize = urlConnection.getContentLength();

            runOnUiThread(new Runnable() {
                public void run() {
                    //    pb.setMax(totalSize);
                }
            });

            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            Intent intent = new Intent(this, MainActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
            PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);


            while ((bufferLength = inputStream.read(buffer)) > 0) {
                Log.e("in loop", "downloadFile: ");
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                // update the progressbar //
                runOnUiThread(new Runnable() {
                    public void run() {
//                        pb.setProgress(downloadedSize);
                        float per = ((float) downloadedSize / totalSize) * 100;
                        Notification n = new Notification.Builder(MainActivity.this)
                                .setContentTitle("download percentage")
                                .setContentText(+per + " percentage")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .build();


                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);
                        //PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

                        //notify.setLatestEventInfo(getApplicationContext(),per,pending);
                        //notif.notify(0, notify);

//                        cur_val.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int)per + "%)" );
                    }
                });
            }
            //close the output stream when complete //
            fileOutput.close();
            runOnUiThread(new Runnable() {
                public void run() {
                    // pb.dismiss(); // if you want close it..
                }
            });

        } catch (final MalformedURLException e) {
            showError("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            showError("Error : IOException " + e);
            e.printStackTrace();
        } catch (final Exception e) {
            showError("Error : Please check your internet connection " + e);
        }
    }

    void showError(final String err) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, err, Toast.LENGTH_LONG).show();
            }
        });
    }

    public int uploadFile(String sourceFileUri) {


        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

            dialog.dismiss();

            Log.e("uploadFile", "Source File not exist :"
                    + uploadFilePath + "" + uploadFileName);

            runOnUiThread(new Runnable() {
                public void run() {
//                    messageText.setText("Source File not exist :"
  //                          + uploadFilePath + "" + uploadFileName);
                }
            });

            return 0;

        } else {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=uploaded_file;filename=" + fileName + " + lineEnd");

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    + "mobile.rshbeta.in/gauthambala/resume"
                                    + uploadFileName;

         //                   messageText.setText(msg);
                            Toast.makeText(MainActivity.this, "File Upload Complete.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        messageText.setText("MalformedURLException Exception : check script url.");
                        Toast.makeText(MainActivity.this, "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        messageText.setText("Got Exception : see logcat ");
                        Toast.makeText(MainActivity.this, "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                //  Log.e("Upload file to server Exception", "Exception : "
                //        + e.getMessage(), e);
            }
            dialog.dismiss();

            return serverResponseCode;

        } // End else block
    }
}
    /*void showProgress(String file_path){
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.mygrid);
        dialog.setTitle("Download Progress");

      //  TextView text = (TextView) dialog.findViewById(R.id.tv1);
        //text.setText("<span id="IL_AD8" class="IL_AD">Downloading file</span> from ... " + file_path);
        //cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
        //cur_val.setText("Starting download...");
        dialog.show();

       // pb = (ProgressBar)dialog.findViewById(R.id.progress_bar);
       // pb.setProgress(0);
        //pb.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));
    }*/

