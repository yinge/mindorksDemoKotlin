package com.seven.mindorks.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Patterns
import com.seven.mindorks.R
import java.io.IOException

/**
 * at 2019/12/3
 * at 10:16
 * summary:
 */
class CommonUtils {
    companion object {

        fun showLoadingDialog(context: Context): ProgressDialog = ProgressDialog(context).also {
            it.show()
            it.window?.let { window ->
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
        }

        @Throws(IOException::class)
        fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
            val manager = context.assets
            val inputStream = manager.open(jsonFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charsets.UTF_8)
        }

        fun isEmailValid(email: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}