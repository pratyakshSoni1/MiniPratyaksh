package com.example.minipratyaksh

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.*
import com.example.minipratyaksh.worker.RemaindWorker

class MainActivityViewModel(application:Application): ViewModel() {

    private var _isLaptopChargingSwitchedOn = false
    val isLaptopChargingSwitchedOn get() = _isLaptopChargingSwitchedOn

    val workManager = WorkManager.getInstance(application)

    internal fun startTimer(){

        var continuation = workManager.beginUniqueWork(
            "laptopChargingTimer_UNIQUENAME",
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from( RemaindWorker::class.java )
        )

        continuation.enqueue()

//        val constraints = Constraints.Builder().setRequiresCharging(true).build()


    }

    internal fun cancelTimer(){
        workManager.cancelUniqueWork("laptopChargingTimer_UNIQUENAME")
    }


}

class MainActivityViewModelFactory(private val application:Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            MainActivityViewModel(application = application) as T
        }else{
            throw java.lang.IllegalArgumentException("Unknow ViewModel class")
        }
    }

}