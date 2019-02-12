package com.example.rumpilstilstkin.lesson4;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsView;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class NetApiClientTest {
    private static MockWebServer mockWebServer;




    @BeforeClass
    public static void setupServer() throws IOException{
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterClass
    public static void shutdownServer() throws IOException{
        mockWebServer.shutdown();

    }

    @Test
    public void testWrongStatus() throws InterruptedException, IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody("{}"));

        Observer<GithubUser> observer = mock(observerCls.class);


        NetApiClient netApiClient = NetApiClient.getInstance();
        netApiClient.getUser("SergeyeeZes").subscribe(observer);
        verify(observer).onError(any(Throwable.class));
        //verify(observer).onNext(null);

        Thread.sleep(3000);

    }


    class observerCls implements Observer<GithubUser>{

        @Override
        public void onSubscribe(Disposable d) {

            Log.i("test7","In onSubscribe");
        }

        @Override
        public void onNext(GithubUser githubUser) {
            Log.i("test7","In onNext");

        }

        @Override
        public void onError(Throwable e) {
            Log.i("test7","In onError");

        }

        @Override
        public void onComplete() {
            Log.i("test7","In onComplete");

        }
    }

}
