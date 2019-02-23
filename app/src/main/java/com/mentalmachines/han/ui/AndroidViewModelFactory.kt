package com.mentalmachines.han.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.NonNull
import java.lang.reflect.InvocationTargetException


/**
 * [Factory] which may create [AndroidViewModel] and
 * [ViewModel], which have an empty constructor.
 */
class AndroidViewModelFactory
/**
 * Creates a `AndroidViewModelFactory`
 *
 * @param application an application to pass in [AndroidViewModel]
 */
    (@param:NonNull private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {

            try {
                return modelClass.getConstructor(Application::class.java).newInstance(mApplication)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }

        }
        return super.create(modelClass)
    }

    companion object {

        private var sInstance: AndroidViewModelFactory? = null

        /**
         * Retrieve a singleton instance of AndroidViewModelFactory.
         *
         * @param application an application to pass in [AndroidViewModel]
         * @return A valid [AndroidViewModelFactory]
         */
        fun getInstance(@NonNull application: Application): AndroidViewModelFactory {
            if (sInstance == null) {
                sInstance = AndroidViewModelFactory(application)
            }
            return sInstance
        }
    }
}