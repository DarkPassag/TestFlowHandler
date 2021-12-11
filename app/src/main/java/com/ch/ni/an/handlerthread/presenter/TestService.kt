package com.ch.ni.an.handlerthread.presenter

/**
class TestService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var retrofitService :RetrofitService

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(
            this, "Service created", Toast.LENGTH_SHORT
        ).apply {
            show()
            setGravity(50,50,50)
        }
        retrofitService = Common.retrofit
        mediaPlayer = MediaPlayer.create(this, R.raw.file)
        mediaPlayer.isLooping = false
    }

    override fun onStartCommand(intent :Intent?, flags :Int, startId :Int) :Int {
        Toast.makeText(this, "Media player started", Toast.LENGTH_SHORT).show()

        Thread {
            Thread.sleep(2000)
            retrofitService.fetchAny().enqueue(object : Callback<String>{
                override fun onResponse(call :Call<String>, response :Response<String>) {

                    Toast.makeText(this@TestService, "${response.body()}", Toast.LENGTH_LONG)
                        .apply {
                            show()
                            setGravity(1, 200, 200)
                        }
                }

                override fun onFailure(call :Call<String>, t :Throwable) {
                }

            })
        }.start()



        mediaPlayer.start()
        return START_STICKY
        }



    override fun onDestroy() {
        super.onDestroy()

    }



    fun stopPlayer(){
        mediaPlayer.stop()
    }

}

 */