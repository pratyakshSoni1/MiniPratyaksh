package com.example.minipratyaksh.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.minipratyaksh.DELAY_TIME_MILLIS
import java.text.SimpleDateFormat
import java.util.*

class RemaindWorker(ctx: Context, params:WorkerParameters): Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext :Context = applicationContext

        makeStatusNotification("Starting to count...",appContext)

        val hourStarted:Int = Calendar.getInstance().get(Calendar.HOUR)

        val minuteStarted:Int = Calendar.getInstance().get(Calendar.MINUTE)
        val secondStarted:Int = Calendar.getInstance().get(Calendar.SECOND)

        var currentHour = Calendar.getInstance().get(Calendar.HOUR)
        var currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
        var currentSecond = Calendar.getInstance().get(Calendar.SECOND)

        Log.d("RUNNING_STRAT","Time: $currentHour:$currentMinute:$currentSecond / $hourStarted:$minuteStarted:$secondStarted")

        while( (hourStarted) == currentHour && currentMinute <= minuteStarted ){

            Log.d("RUNNING_AGAIN_NO_ERR","Time: $currentMinute = $minuteStarted, $currentHour = $hourStarted")

            currentHour = Calendar.getInstance().get(Calendar.HOUR)
            currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
            currentSecond = Calendar.getInstance().get(Calendar.SECOND)

            makeStatusNotification("Running $currentHour:$currentMinute:$currentSecond / $hourStarted:$minuteStarted:$secondStarted", appContext)

            try {
                Thread.sleep(15000, 0)
            } catch (e: InterruptedException) {
                Log.e("WORKER_SLEEP_ERR", e.message.toString())
            }

        }

        return if( hourStarted == currentHour && currentMinute >= minuteStarted ){
            makeStatusNotification("Couter Time UP",appContext)
            Result.success()
        }else{
            makeStatusNotification("Counter result error",appContext)
            Log.d("Return_GOT_ERR","Time: $currentHour:$currentMinute:$currentSecond / $hourStarted:$minuteStarted:$secondStarted")
            Result.failure()
        }

    }

}