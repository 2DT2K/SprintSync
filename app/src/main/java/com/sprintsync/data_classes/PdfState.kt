package com.sprintsync.data_classes

import android.net.Uri
import com.pspdfkit.document.PdfDocument

data class PdfState(
    val loading: Boolean = false,
    val documents: Map<Uri, PdfDocument> = emptyMap(),
    val selectedDocumentUri: Uri? = null
)