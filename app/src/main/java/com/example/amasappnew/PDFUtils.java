package com.example.amasappnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.View;

import androidx.core.widget.NestedScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFUtils {
    public static File createPdfFromNestedScrollView(Context context, NestedScrollView nestedScrollView, String fileName) throws IOException {
        Bitmap bitmap = captureNestedScrollView(nestedScrollView);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);

        document.finishPage(page);


        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        FileOutputStream fos = new FileOutputStream(file);
        document.writeTo(fos);


        document.close();
        fos.close();

        return file;
    }

    private static Bitmap captureNestedScrollView(NestedScrollView nestedScrollView) {

        int height = 0;
        for (int i = 0; i < nestedScrollView.getChildCount(); i++) {
            height += nestedScrollView.getChildAt(i).getHeight();
        }


        Bitmap bitmap = Bitmap.createBitmap(nestedScrollView.getWidth(), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        nestedScrollView.draw(canvas);

        return bitmap;
    }
}
