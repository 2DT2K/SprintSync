package com.sprintsync.ui.components.backlog

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.MediaStore.Video
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.R
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.view_models.AttachmentViewModel
import com.sprintsync.ui.components.CustomModalBottomSheet
import com.sprintsync.ui.theme.spacing
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskComposer(
    projectID: String?,
    showBottomSheet: Boolean,
    changeVisibility: (Boolean) -> Unit
) {
    val attachmentVM = hiltViewModel<AttachmentViewModel>()
    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        val item = it?.let { it1 -> context.contentResolver.openInputStream(it1) }
        val bytes = item?.readBytes()

        if (it != null && bytes != null) {
            val fileData = getFileInfo(it, context)
            val attachment = AttachmentDto(
                id = null,
                name = fileData.name ?: "",
                fileSize = fileData.size,
                createdAt = LocalDateTime.now().toString(),
                content = bytes,
                project = projectID ?: "",
                fileType = fileData.type ?: ""
            )
            Log.d("log-bug-file", attachment.toString())
            attachmentVM.addAttachment(attachment)
        }
        println(bytes)
        bytes?.let { byteArr -> println(String(byteArr)) }
        item?.close()
    }

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult =
        {
            Log.d("log-bug-camera", it.toString())
            bitmap = it
            val stream = ByteArrayOutputStream()
            it?.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            Log.d("log-bug",stream.toByteArray().toString() )

            val attachment = AttachmentDto(
                id = null,
                name = "project screenshot",
                fileSize = stream.size().toLong(),
                createdAt = LocalDateTime.now().toString(),
                content = stream.toByteArray(),
                project = projectID ?: "",
                fileType = "image/jpeg"
            )
//
//            attachmentVM.addAttachment(attachment)

            stream.close()
        }
    )

//    val videoUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val videoLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.CaptureVideo(),
//        onResult =
//        {
//            if (it) {
//                val item = videoUri?.let { it1 -> context.contentResolver.openInputStream(it1) }
//                val bytes = item?.readBytes()
//                println(bytes)
//                bytes?.let { byteArr -> println(String(byteArr)) }
//                item?.close()
//            }
//        }
//    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        }
    }

    Column {
        Text(text = "fasdfasd")
        bitmap?.let { Image(bitmap= it.asImageBitmap(), contentDescription = "fads" ) }
    }
    Box(modifier = Modifier) {
        CustomModalBottomSheet(
            isSheetShown = showBottomSheet,
            changeVisibility = { changeVisibility(it) }) {
            Column(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
            ) {
                Text(
                    text = "Create issue with attachment",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .clickable {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(
                                    context,
                                    android.Manifest.permission.CAMERA
                                )

                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch()
                            } else {
                                permissionLauncher.launch(android.Manifest.permission.CAMERA)
                            }
                        }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "take photo",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Take photo")
                }

                Row(
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "this function is currently disabled please wait to next update",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
//                            val permissionCheckResult =
//                                ContextCompat.checkSelfPermission(
//                                    context,
//                                    android.Manifest.permission.CAMERA
//                                )
//
//                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//                                videoLauncher.launch(videoUri)
//                            } else {
//                                permissionLauncher.launch(android.Manifest.permission.CAMERA)
//                            }
                        }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.videocam),
                        contentDescription = "Record video",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Record video")
                }

                Row(
                    modifier = Modifier
                        .clickable { filePickerLauncher.launch("*/*") }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(0.dp)
                            .width(24.dp)
                            .scale(1.5f),
                        painter = painterResource(id = R.drawable.attach_file_icon),
                        contentDescription = "Choose file",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Choose file")
                }
            }
        }
    }
}

data class FileInfo(val name: String?, val size: Long, val type: String?)

fun getFileInfo(uri: Uri, context: Context): FileInfo {
    var fileName: String? = null
    var fileSize: Long = 0
    var fileType: String? = null

    when {
        uri.scheme == "content" -> {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        fileName = cursor.getString(displayNameIndex)

                        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                        fileSize = cursor.getLong(sizeIndex)

                        val typeIndex =
                            cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)
                        fileType = cursor.getString(typeIndex)
                    }
                }
            } else {
                fileName = uri.lastPathSegment

                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                        fileSize = cursor.getLong(sizeIndex)

                        val typeIndex =
                            cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)
                        fileType = cursor.getString(typeIndex)
                    }
                }
            }
        }

        uri.scheme == "file" -> {
            fileName = File(uri.path).name
            fileSize = File(uri.path).length()
            val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            fileType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
    }

    return FileInfo(fileName, fileSize, fileType)
}

@Preview
@Composable
fun PreviewTest() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "take photo"
            )

            Text(text = "Take photo")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.videocam),
                contentDescription = "Record video"
            )

            Text(text = "Record video")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(0.dp)
                    .width(24.dp)
                    .scale(1.5f),
                painter = painterResource(id = R.drawable.attach_file_icon),
                contentDescription = "Choose file"
            )

            Text(text = "Choose file")
        }

        Spacer(
            Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )

        Spacer(
            Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}