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
        // Capturar el contenido del NestedScrollView como un bitmap
        Bitmap bitmap = captureNestedScrollView(nestedScrollView);

        // Crear un documento PDF
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);

        document.finishPage(page);

        // Guardar PDF en el almacenamiento específico de la aplicación
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        FileOutputStream fos = new FileOutputStream(file);
        document.writeTo(fos);

        // Limpiar
        document.close();
        fos.close();

        return file;
    }

    private static Bitmap captureNestedScrollView(NestedScrollView nestedScrollView) {
        // Medir el contenido total del NestedScrollView
        int height = 0;
        for (int i = 0; i < nestedScrollView.getChildCount(); i++) {
            height += nestedScrollView.getChildAt(i).getHeight();
        }

        // Crear un bitmap de tamaño suficiente para todo el contenido
        Bitmap bitmap = Bitmap.createBitmap(nestedScrollView.getWidth(), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        nestedScrollView.draw(canvas);

        return bitmap;
    }
}
