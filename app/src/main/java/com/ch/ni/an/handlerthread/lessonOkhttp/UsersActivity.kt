package com.ch.ni.an.handlerthread.lessonOkhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.ListUsersFragment
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.ListUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UsersActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ListUsersFragment>(R.id.fragmentContainer)
            }
        }



    }
}