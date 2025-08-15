package com.bachld.arsyntask

import android.app.Activity
import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MyAsyncTask(var activityCha: Activity): AsyncTask<Void, Int, Void>() {


    override fun doInBackground(vararg params: Void?): Void? {
        for(i in 0..100) {
            Thread.sleep(100)
            publishProgress(i)
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val progress = activityCha.findViewById<ProgressBar>(R.id.progressBar1)
        val giaTri = values[0] ?: 0
        val textView: TextView = activityCha.findViewById(R.id.textView1)
        textView.text = "$giaTri%"
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Toast.makeText(activityCha, "onPreExecute", Toast.LENGTH_SHORT).show()
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Toast.makeText(activityCha, "onPostExecute", Toast.LENGTH_SHORT).show()
    }
}