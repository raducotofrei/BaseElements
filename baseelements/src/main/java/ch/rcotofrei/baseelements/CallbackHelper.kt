package ch.rcotofrei.baseelements

import android.os.Bundle
import android.os.Handler
import android.os.Message

class CallbackHelper(val callback:Handler.Callback){
    var message:Message = Message()
    var bundle:Bundle = Bundle()

    fun send() {
        message.data = bundle
        callback.handleMessage(message)
    }
}
