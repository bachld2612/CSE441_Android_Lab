package com.bachld.intentbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast


class MySmsReceive : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        processReceive(context, intent)
    }

    public fun processReceive(context: Context, intent: Intent){
        val extras: Bundle? = intent.extras
        var message: String? = ""
        var body: String? = ""
        var address: String? = ""
        if(extras != null) {
            val smsExtras = extras.get("pdus") as Array<*>
            smsExtras.forEach { pdu ->
                val format = extras.getString("format")
                val sms = SmsMessage.createFromPdu(pdu as ByteArray, format)
                body = sms.messageBody
                address = sms.originatingAddress
                message += "Có 1 tin nhắn từ $address\n$body vừa gởi đến\n"
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}