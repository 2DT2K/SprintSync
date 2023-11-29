package com.sprintsync.utils

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream

object FileUtility {
    private fun getMimeType(file: File): String? {
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    fun getExtension(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

    fun downloadFile(byteArrayString: String, fileName: String, context: Context) {
        val byteArray = byteArrayString.toByteArray()
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(byteArray)
        fileOutputStream.close()

        val fileUrl = FileProvider.getUriForFile(
            context,
            "com.sprintsync.provider",
            file
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(fileUrl, getMimeType(file))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }
}